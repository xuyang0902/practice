package com.zx.yuren.jdk.collections.生产者消费者;

/**
 * synchronized+wait+notifyAll来实现
 *
 * @author xu.qiang
 * @date 18/12/12
 */
public class MyQueue2<T> {

    private int takeIndex = 0;
    private int putIndex = 0;
    private int count = 0;

    private Object[] items;

    public MyQueue2(int capacity) {
        items = new Object[capacity];
    }


    public T take() throws InterruptedException {
        synchronized (this) {
            T item = null;
            /**
             * 这是因为，如果采用if判断，当线程从wait中唤醒时，那么将直接执行处理其他业务逻辑的代码，但这时候可能出现另外一种可能，
             * 条件谓词已经不满足处理业务逻辑的条件了，从而出现错误的结果，于是有必要进行再一次判断，如下：
             */
            while (count == 0) {
                System.out.println("------->>>>>"+ Thread.currentThread().getName() + "===>>>> this.wait()" );
                this.wait();
            }
            item = (T) items[takeIndex];
            items[takeIndex] = null;
            takeIndex++;
            if (takeIndex == items.length) {
                takeIndex = 0;
            }
            count--;


            this.notifyAll();
            return item;
        }
    }


    public void put(T object) throws InterruptedException {
        synchronized (this) {
            if (object == null) {
                throw new NullPointerException();
            }

            while (count == items.length) {
                System.out.println("------->>>>>"+ Thread.currentThread().getName() + "===>>>> this.wait()" );
                this.wait();
            }

            items[putIndex] = object;
            putIndex++;
            if (putIndex == items.length) {
                putIndex = 0;
            }
            count++;

            this.notifyAll();
        }


    }


    public static void main(String[] args) {

        final MyQueue2<Integer> myQueue = new MyQueue2<Integer>(10);

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "消费：" + myQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "Consumer-1");
        consumer.start();


        Thread consumer2 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "消费：" + myQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "Consumer-2");
        consumer2.start();


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                int index = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        int i = index++;
                        myQueue.put(i);
                        System.out.println(Thread.currentThread().getName() + "生产：" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Producer");
        thread1.start();


    }
}
