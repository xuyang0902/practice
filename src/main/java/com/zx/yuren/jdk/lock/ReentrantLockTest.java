package com.zx.yuren.jdk.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 完全是为了看源码写一个main方法
 *
 * @author xu.qiang
 * @date 18/7/5
 *
 * https://javadoop.com/post/AbstractQueuedSynchronizer
 * ps:一个写AQS很不错的博客
 */
public class ReentrantLockTest {

    /**
     * 还是要读读源码的咯
     *
     * @see ReentrantLock.FairSync
     * @see ReentrantLock.Sync
     * @see AbstractQueuedSynchronizer   【核心类】
     * @see AbstractQueuedSynchronizer.Node
     * @see AbstractOwnableSynchronizer
     */
    private static ReentrantLock reentrantLock = new ReentrantLock(true/*是否为公平锁*/);

    private static Integer tickets = 100;

    public static void main(String[] args) {
        reentrantLock.lock();

//        System.out.println(reentrantLock.tryLock());
//        System.out.println(reentrantLock.tryLock());
//        System.out.println(reentrantLock.tryLock());
//        System.out.println(reentrantLock.tryLock());
//        reentrantLock.unlock();
//        reentrantLock.unlock();
//        reentrantLock.unlock();
//        reentrantLock.unlock();

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 10; i++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //支持并发的出票
                    test01();
                }
            });
        }


        executorService.shutdown();

    }


    private static void test01() {
        // 比如我们同一时间，只允许一个线程创建订单
        reentrantLock.lock();
        // 通常，lock 之后紧跟着 try 语句
        try {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tickets = tickets - 1;
            System.out.println(Thread.currentThread().getName() + "--->剩余票数" + tickets);
        } finally {
            // 释放锁
            reentrantLock.unlock();
        }
    }

}
