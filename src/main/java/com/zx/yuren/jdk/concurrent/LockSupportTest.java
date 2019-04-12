package com.zx.yuren.jdk.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 总结一下，LockSupport比Object的wait/notify有两大优势：
 * ①LockSupport不需要在同步代码块里 。所以线程间也不需要维护一个共享的同步对象了，实现了线程间的解耦。
 * ②unpark函数可以先于park调用，所以不需要担心线程间的执行的先后顺序。
 *
 *
 * 通过阅读LockSupport的本地实现，我们不难发现这么个问题：多次调用unpark方法和调用一次unpark方法效果一样，
 * 因为都是直接将_counter赋值为1，而不是加1。简单说就是：线程A连续调用两次LockSupport.unpark(B)方法唤醒线程B，
 * 然后线程B调用两次LockSupport.park()方法， 线程B依旧会被阻塞。因为两次unpark调用效果跟一次调用一样，
 * 只能让线程B的第一次调用park方法不被阻塞，第二次调用依旧会阻塞。
 */
public class LockSupportTest {


    public static void main(String[] args) throws Exception {
        //test01();
        test02();
    }

    public static void test01() throws Exception {
        final Object obj = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum += i;
                }
                try {
                    synchronized (obj) {
                        //只能携带同步代码块里面
                        obj.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(sum);
            }
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法   不好控制呀，万一没执行完就通知了呢
        //Thread.sleep(1000);
        synchronized (obj) {
            obj.notify();
        }
    }


    public static void test02() throws Exception {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum += i;
                }
                LockSupport.park();
                System.out.println(sum);
            }
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        //Thread.sleep(1000);
        LockSupport.unpark(A);
    }


}