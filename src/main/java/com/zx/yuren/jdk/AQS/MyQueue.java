package com.zx.yuren.jdk.AQS;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xu.qiang
 * @date 18/12/12
 */
public class MyQueue<T> {


    private ReentrantLock reentrantLock;
    private Condition notEmpty;//不为空 可以take走
    private Condition notFull;//没有满 还可以put

    private int takeIndex = 0;
    private int putIndex = 0;
    private int count = 0;

    private Object[] items;

    public MyQueue(int capacity) {
        items = new Object[capacity];
        reentrantLock = new ReentrantLock();
        notEmpty = reentrantLock.newCondition();
        notFull = reentrantLock.newCondition();
    }


    public T take() throws InterruptedException {
        reentrantLock.lockInterruptibly();
        try {
            if (count == 0) {
                notEmpty.await();
            }
            T item = (T)items[takeIndex];
            items[takeIndex] = null;
            takeIndex++;
            if (takeIndex == items.length) {
                takeIndex = 0;
            }
            count--;
            return item;
        } finally {
            notFull.signal();
            reentrantLock.unlock();
        }
    }


    public void put(T object) throws InterruptedException {
        if (object == null) {
            throw new NullPointerException();
        }

        reentrantLock.lockInterruptibly();
        try {
            if (count == items.length) {
                notFull.await();
            }

            items[putIndex] = object;
            putIndex++;
            if (putIndex == items.length) {
                putIndex = 0;
            }
            count++;
        } finally {
            notEmpty.signal();
            reentrantLock.unlock();
        }
    }


    public static void main(String[] args) {

        final MyQueue<Integer> myQueue = new MyQueue<Integer>(10);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(10);
                        System.out.println(Thread.currentThread().getName() + "消费：" + myQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, "Consumer");
        thread.start();


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
