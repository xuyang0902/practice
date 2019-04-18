package com.zx.yuren.disruptor.cacheline;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * jdk1.8
 * 使用缓存行 duration = 8.9s
 * 没有使用缓存行 duration = 20.06s
 *
 * 当多个线程同时对共享的缓存行进行写操作的时候，因为缓存系统自身的缓存一致性原则，会引发伪共享问题，
 * 解决的常用办法是将共享变量根据缓存行大小进行补充对齐，使其加载到缓存时能够独享缓存行，
 * 避免与其他共享变量存储在同一个缓存行。
 *
 * @author xu.qiang
 * @date 19/4/17
 */
public class RunTest {

    public static long MODIFY_COUNT = 10000 * 10000 * 5;//修改次数 100亿次

    public static int ThreadCount = 4;

    public static void main(String[] args) throws InterruptedException {

        testCacheLine();
        testNoCacheLine();

    }


    public static void testNoCacheLine() throws InterruptedException {
        DataValue[] dataValue = new DataValue[ThreadCount];
        for(int index = 0;index < dataValue.length;index++){
            dataValue[index] = new DataValue();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(ThreadCount);
        CountDownLatch cdl = new CountDownLatch(ThreadCount);

        final long start = System.nanoTime();
        for (DataValue value : dataValue) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    long count = MODIFY_COUNT;
                    while(count-- > 0){
                        value.setValue(count);
                    }
                    cdl.countDown();
                }
            });
        }
        cdl.await();

        System.out.println("没有使用缓存行 duration = " + (System.nanoTime() - start));

        executorService.shutdown();
    }


    public static void testCacheLine() throws InterruptedException {
        DataValueCahe[] dataValue = new DataValueCahe[ThreadCount];

        for(int index = 0;index < dataValue.length;index++){
            dataValue[index] = new DataValueCahe();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(ThreadCount);

        CountDownLatch cdl = new CountDownLatch(ThreadCount);

        final long start = System.nanoTime();
        for (DataValueCahe value : dataValue) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    long count = MODIFY_COUNT;
                    while(count-- > 0){
                        value.setValue(count);
                    }
                    cdl.countDown();
                }
            });
        }

        cdl.await();

        System.out.println("使用缓存行 duration = " + (System.nanoTime() - start));

        executorService.shutdown();
    }





}
