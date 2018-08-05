package com.tongbanjie.jdk.thread;

/**
 * @author xu.qiang
 * @date 18/7/20
 */
public class ThreadInterrupt {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while(!Thread.currentThread().isInterrupted()){
                    System.out.println(">>> thread run");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                        /**
                         * 当线程被中断过一次了之后，中断表示会重置
                         *
                         *  if any thread has interrupted the current thread. The
                         *  <i>interrupted status</i> of the current thread is
                         *  cleared when this exception is thrown.
                         */
                        System.out.println(">> thread intruppt  because sleep");
                        e.printStackTrace();
                    }
                }

                System.out.println(">> thread interrupt>>>> finllay");
            }
        });


        thread.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
