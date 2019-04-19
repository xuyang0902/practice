

#### Disruptor

##### What? 是什么？
    高性能的无锁队列。
    
    大学在学习到队列的时候，老师是不是让我们课下自己去实现阻塞队列，大家还有印象么？没有印象建议读一读java.util.concurrent.ArrayBlockingQueue
    

##### Why？ 为什么这么快

    伪共享问题处理，缓冲行填充，大量CAS操作，去锁
    无锁环形队列设计，位运算锁定坑位等

    
[CPU缓存行认知](https://blog.csdn.net/qq_27680317/article/details/78486220) 
    

##### 个人图解disruptor   

![ringbuffer](https://github.com/xuyang0902/practice/blob/master/src/main/java/com/zx/yuren/disruptor/ringbuffer.jpg?raw=true "ringbuffer")

    so 到目前为止，如果让你自己实现一个Disruptor 你会怎么做呢？
    
    
##### Disruptor HelloWorld 
    
    知其然，再知其所以然。so 先知其然

(```)
    
    //环形数组 位置的数据对象
    public final class ValueEvent {
        private String value;
    
        public String getValue() {
            return value;
        }
    
        public void setValue(String value) {
            this.value = value;
        }
    
        /**
         * 数据对象数据工厂
         */
        public final static EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory<ValueEvent>() {
            @Override
            public ValueEvent newInstance() {
                return new ValueEvent();
            }
        };
    
    
    }
    
    //demo main
    public static void main(String[] args) throws InterruptedException {

        //环形大小
        int ringBufferSize = 2 << 2;

        //disruptor
        Disruptor<ValueEvent> disruptor = new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY, ringBufferSize,
                Executors.defaultThreadFactory(), ProducerType.MULTI, new BlockingWaitStrategy());

        //消费端事件处理器
        EventHandler<ValueEvent> eventHandler = new EventHandler<ValueEvent>() {

            @Override
            public void onEvent(final ValueEvent event, final long sequence, final boolean endOfBatch) throws Exception {
                System.out.println("Sequence: " + sequence);
                System.out.println("ValueEvent: " + event.getValue());
            }
        };

        disruptor.handleEventsWith(eventHandler);

        //初始化disruptor
        RingBuffer<ValueEvent> ringBuffer = disruptor.start();

        Thread.sleep(3000L);
        //生产端事件
        for (long i = 0; i < 11; i++) {
            String uuid = UUID.randomUUID().toString();
            // Two phase commit. Grab one of the 1024 slots
            long seq = ringBuffer.next();
            ValueEvent valueEvent = ringBuffer.get(seq);
            valueEvent.setValue(uuid);
            ringBuffer.publish(seq);
        }


        disruptor.shutdown();

    }


(```)
    
    
##### 关键类个人理解
    Disruptor:包装了ringbuffer,消费组
    
    RingBuffer：
    数据结构：环形数组 2的n次阶
    核心属性：sequencer发布坑位神器
    next()方法：获取下一个可以发布的坑位
    publish（index）方法：发布坑位，告诉消费者，这个坑位可以消费了
    
    Sequence：可以理解为一个增强的AtomicLong，解决了缓存行失效的问题，性能更好，代码就不分析了，大家有兴趣自己读取（主要是解决了伪共享的问题）
    
    Sequencer：维护了当前发布的坑位（单生成器，多生成器）
    
    SequenceBarrier：跟踪 生产者当前发布坑位，消费者消费当前坑位，依赖坑位等
    
    WaitStrategy：等待策略确定消费者如何等待生产者将事件放入Disruptor
    
    Event：从生产者传递给消费者的数据单位。事件没有特定的代码表示，因为它完全由用户定义。
    
    EventProcessor：
    用于处理来自Disruptor的事件的主事件循环，消费坑位。
    单线程：BatchEventProcessor
    多线程  WorkProcessor
    
    EventHandler:用户自己实现，拿到数据了如何消费
    
    Producer：用户自己实现

    
##### 核心类解读 
    师傅领进门，修行靠自己。
    
    建议看完上面的图，跑跑helloworld

###### 生产者#SingleProducerSequencer


(```)

    //核心属性 维护了生产者当前生产的下标 和 缓存门卫下标
    long nextValue = -1; 
    long cachedValue = -1;


    public long next(int n)
    {
        if (n < 1)
        {
            throw new IllegalArgumentException("n must be > 0");
        }

        //初始值为-1，最后申请的生产者坑位
        long nextValue = this.nextValue;

        //本次申请的坑位，单个发生n=1 【nextSequence&（bufferSize-1）即为在数组中的具体位置】
        long nextSequence = nextValue + n;

        //本次申请的坑位减一圈？ 让一圈还比你跑的快的意思
        long wrapPoint = nextSequence - bufferSize;

        //初始值为-1 最小的消费者坑位
        long cachedGatingSequence = this.cachedValue;

        /*
         * 生产者追尾 消费者 ||  消费者追尾生产者？？ 这种情况什么时候会发生
         */
        if (wrapPoint > cachedGatingSequence || cachedGatingSequence > nextValue)
        {
            cursor.setVolatile(nextValue);

            long minSequence;

            //当发生生产者 追尾消费者的时候
            while (wrapPoint > (minSequence = Util.getMinimumSequence(gatingSequences, nextValue)))
            {
                //生产者等1ns 让消费者先泡一会
                LockSupport.parkNanos(1L); // TODO: Use waitStrategy to spin?
            }

            /*
             * 消费端让出坑位，记下消费者占用的最小的那个坑位
             */
            this.cachedValue = minSequence;
        }

        //成功申请到坑位
        this.nextValue = nextSequence;

        return nextSequence;
    }

    
    
    public void publish(long sequence)
    {
        //设置当前生产的下标位置
        cursor.set(sequence);
        //唤醒消费线程->获取可消费的下标
        waitStrategy.signalAllWhenBlocking();
    }
    
    
    
(```)


###### 生产者#MultiProducerSequencer


(```)

    public long next(int n)
    {
        if (n < 1)
        {
            throw new IllegalArgumentException("n must be > 0");
        }

        long current;
        long next;

        do
        {
            //当前坑位
            current = cursor.get();
            //下一个坑位
            next = current + n;

            //让一圈后的坑位
            long wrapPoint = next - bufferSize;
            //守门员坑位  最小消费者坑位
            long cachedGatingSequence = gatingSequenceCache.get();

            //不能超车，等待后 自旋重试
            if (wrapPoint > cachedGatingSequence || cachedGatingSequence > current)
            {
                long gatingSequence = Util.getMinimumSequence(gatingSequences, current);

                if (wrapPoint > gatingSequence)
                {
                    LockSupport.parkNanos(1); // TODO, should we spin based on the wait strategy?
                    continue;
                }

                gatingSequenceCache.set(gatingSequence);
            }
            //多生产者 用cas的方式获取坑位
            else if (cursor.compareAndSet(current, next))
            {
                break;
            }
        }
        while (true);

        return next;
    }
    
    public void publish(final long sequence)
    {
        //当前坑位   圈数量？
        setAvailable(sequence);
        //通知阻塞的消费者消费
        waitStrategy.signalAllWhenBlocking();
    }


(```)

    

###### 环形数组#RingBuffer

(```)

    //环形最大的index
    private final long indexMask;

    /**
     * 环形数组元素
     *null,null,null,null [ 中间这一块是环形数组,两边对称的数组位置暂时没有放任何东西，做填充] null,null,null,null
     */
    private final Object[] entries;
    protected final int bufferSize;

    /**
     * 生产者对象的引用
     * @see MultiProducerSequencer
     * @see SingleProducerSequencer
     */
    protected final Sequencer sequencer;

    RingBufferFields(
            EventFactory<E> eventFactory,
            Sequencer sequencer) {
        //生产者对象
        this.sequencer = sequencer;
        this.bufferSize = sequencer.getBufferSize();

        if (bufferSize < 1) {
            throw new IllegalArgumentException("bufferSize must not be less than 1");
        }
        if (Integer.bitCount(bufferSize) != 1) {
            throw new IllegalArgumentException("bufferSize must be a power of 2");
        }

        //indexMask = 2^n次-1  用来做与运算 相当快，计算机非常喜欢010101
        this.indexMask = bufferSize - 1;

        /**
         * 其实是多创建了一些数组，填充了一些数组，保证ringbuffer的数组不和别人共享缓存行，
         * 因为ringbuffer的数组对象一开始就创建好了，保持具体的应用就好了，不会变，提高了性能
         */
        this.entries = new Object[sequencer.getBufferSize() + 2 * BUFFER_PAD];
        fill(eventFactory);
    }

    private void fill(EventFactory<E> eventFactory) {
        for (int i = 0; i < bufferSize; i++) {
            //初始化坑位
            entries[BUFFER_PAD + i] = eventFactory.newInstance();
        }
    }

    @SuppressWarnings("unchecked")
    protected final E elementAt(long sequence) {
        //根据下标获取entry
        return (E) UNSAFE.getObject(entries, REF_ARRAY_BASE + ((sequence & indexMask) << REF_ELEMENT_SHIFT));
    }

(```)

###### Disruptor

(```)

    EventHandlerGroup<T> createEventProcessors(
            /*  new Sequence[0]  参数传进
            来的*/
        final Sequence[] barrierSequences,
        final EventHandler<? super T>[] eventHandlers)
    {
        checkNotStarted();

        /*
         * 有几个消费者 就有几个sequence
         */
        final Sequence[] processorSequences = new Sequence[eventHandlers.length];


        /**
         * 追踪 生产者的生产下标 和 依赖消费端的的Sequence
         */
        final SequenceBarrier barrier = ringBuffer.newBarrier(barrierSequences);

        for (int i = 0, eventHandlersLength = eventHandlers.length; i < eventHandlersLength; i++)
        {
            final EventHandler<? super T> eventHandler = eventHandlers[i];

            /**
             * 批次事件处理器
             */
            final BatchEventProcessor<T> batchEventProcessor =
                new BatchEventProcessor<>(ringBuffer, barrier, eventHandler);

            if (exceptionHandler != null)
            {
                batchEventProcessor.setExceptionHandler(exceptionHandler);
            }

            /**
             * 消费端仓库 加入事件处理器
             */
            consumerRepository.add(batchEventProcessor, eventHandler, barrier);

            /**
             * 每个hanlder都有一个sequence
             */
            processorSequences[i] = batchEventProcessor.getSequence();
        }

        /**
         * 消费端的Sequence 需要加到sequencer中。
         */
        updateGatingSequencesForNextInChain(barrierSequences, processorSequences);

        return new EventHandlerGroup<>(this, consumerRepository, processorSequences);
    }

(```)


###### 事件处理器 BatchEventProcessor

  
(```)

    private void processEvents()
    {

        /**
         * 每个消费事件是一个线程。
         *
         * sequence 最开始是-1
         *
         * 从0开始消费
         */

        T event = null;
        long nextSequence = sequence.get() + 1L;

        while (true)
        {
            try
            {

                /**
                 * 检查生产者生产的位置，消费端需要消费，消费者追尾生产者的时候，阻塞在这里
                 */
                final long availableSequence = sequenceBarrier.waitFor(nextSequence);


                /**
                 * 消费端拿到可用的消费下标
                 */
                while (nextSequence <= availableSequence)
                {
                    //获取数据
                    event = dataProvider.get(nextSequence);
                    //事件处理
                    eventHandler.onEvent(event, nextSequence, nextSequence == availableSequence);

                    //消费坑位+1
                    nextSequence++;
                }

                //消费坑位为availableSequence
                sequence.set(availableSequence);
            }
            catch (final TimeoutException e)
            {
                notifyTimeout(sequence.get());
            }
            catch (final AlertException ex)
            {
                if (running.get() != RUNNING)
                {
                    break;
                }
            }
            catch (final Throwable ex)
            {
                exceptionHandler.handleEventException(ex, nextSequence, event);
                sequence.set(nextSequence);
                nextSequence++;
            }
        }
    }

(```)

###### disruptor 一个实现处理器 多线程消费 disruptor.handleEventsWithWorkerPool

        换汤不换药的存在，多线程用cas拿到当前坑位消费。留给大家自行解读。
        
        

###### 思考：
        
        1、必须A处理器处理完 才能处理B和C处理器的场景，disrupptor如何实现的？
        SequenceBarrier的实现类ProcessingSequenceBarrier构造器public ProcessingSequenceBarrier(
        final Sequencer sequencer,
        final WaitStrategy waitStrategy,
        final Sequence cursorSequence,
        final Sequence[] dependentSequences /***哇哦 这是什么东西？**/)
        
        创建来源
        Disruptor.createEventProcessors(
        final Sequence[] barrierSequences/**哇哦，这个参数原来是要传依赖的那个消费组的下标，一开始我还不知道有什么用呢 哈哈哈 读源码真实件有趣的事情**/,
        final EventHandler<? super T>[] eventHandlers)
        
        
        SequenceBarrier维护了一个依赖的坑位序列组。具体代码也留给大家自行解读
        
        2、为什么Disruptor快？？
          现在你有答案了么
        
        
        

###### 参考

[disruptor一些不错的文章](http://ifeve.com/disruptor/)  

[Disruptor github](https://github.com/LMAX-Exchange/disruptor/wiki/Introduction)


        
###### about ME        
[雨人](https://github.com/xuyang0902)
    
    