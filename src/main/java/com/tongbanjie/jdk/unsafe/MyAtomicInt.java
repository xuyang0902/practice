package com.tongbanjie.jdk.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xu.qiang
 * @date 18/8/29
 */
public class MyAtomicInt {


    private static final long serialVersionUID = 6214790243416807050L;

    private static Unsafe unsafe = null;
    // setup to use Unsafe.compareAndSwapInt for updates
    private static long valueOffset;

    static {

        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            valueOffset = unsafe.objectFieldOffset(MyAtomicInt.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private volatile int value;

    public MyAtomicInt() {
    }

    public MyAtomicInt(int value) {
        this.value = value;
    }

    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    public final int getAndIncrement() {
        for (; ; ) {
            int current = get();
            int next = current + 1;
            if (compareAndSet(current, next)) {
                return current;
            }
        }
    }

    /**
     * Atomically decrements by one the current value.
     *
     * @return the previous value
     */
    public final int getAndDecrement() {
        for (; ; ) {
            int current = get();
            int next = current - 1;
            if (compareAndSet(current, next)) {
                return current;
            }
        }
    }

    public final int get() {
        return value;
    }


    public static void main(String[] args) {

        final MyAtomicInt count = new MyAtomicInt(0);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int index = 0; index < 60; index++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    count.getAndIncrement();
                }
            });
        }


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count.get());
    }

}
