package com.zx.yuren.jdk.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized
 * @author xu.qiang
 * @date 18/12/13
 */
public class SynchronizedTest {

    private static Integer tickets = 100;

    public static void main(String[] args) {

        synchronized (SynchronizedTest.class){
            System.out.println(">>>>>");
        }


        final SynchronizedTest test = new SynchronizedTest();

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 100; i++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //支持并发的出票
                    test.test01();
                }
            });
        }


        executorService.shutdown();

    }


    private synchronized void test01() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tickets = tickets - 1;
        System.out.println(Thread.currentThread().getName() + "--->剩余票数" + tickets);
    }


}
