package com.tongbanjie.yuren.jdk.unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static final AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        atomicIntegerTest();

        Thread.sleep(3000);
        System.out.println("最终结果是" + atomicInteger.get());
    }

    private  static void atomicIntegerTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < 60; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(atomicInteger.getAndIncrement());
                }
            });
        }
        executorService.shutdown();
    }
}