package com.tongbanjie.test.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Thread
 * interrupt()  中断线程，只是打一个中断标志
 * isInterrupted() 判断该线程是否被中断
 * Thread.interrupted()  判断是否被中断，且把这个标志清空
 *
 *
 * join(),sleep(),wait() 当抛出该异常时，当前线程的 中断状态 被清除。
 * @author xu.qiang
 * @date 18/5/10
 */
public class Thread中断 {

    @Test
    public void 正确写法() {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                AtomicLong num = new AtomicLong(0);
                while (!Thread.currentThread().isInterrupted()) {

                    //业务代码。。
                    if (num.incrementAndGet() % 100000 == 0) {
                        System.out.println("-->>" + num.get());
                    }
                }

                System.out.println("done");
            }
        });
        myThread.start();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //尝试中断线程
        myThread.interrupt();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
