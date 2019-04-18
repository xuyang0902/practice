package com.zx.yuren.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.Executors;


/**
 * disruptor单元测试
 *
 * @author xu.qiang
 * @date 18/7/5
 */
public class DisruptorStart {

    public static void main(String[] args) throws InterruptedException {

        //环形大小
        int ringBufferSize = 2 << 0;

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

        disruptor.handleEventsWithWorkerPool(new WorkHandler<ValueEvent>() {
            @Override
            public void onEvent(ValueEvent event) throws Exception {

            }
        });

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
}