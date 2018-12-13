package com.zx.yuren.jdk.thread.mutilthread;


import java.util.concurrent.*;

/**
 * 多线程创建
 *
 * 线程是稀缺资源，不能频繁的创建。
 * 解耦作用；线程的创建于执行完全分开，方便维护。
 * 应当将其放入一个池子中，可以给其他任务进行复用。
 *
 * 线程池变化顺序
 *  01.是否到达核心线程数
 *  02.队列是否满了
 *  03.是否到达最大线程数
 *  04.执行拒绝策略
 *
 * 【空闲 回收 】
 *
 * IO 密集型任务：由于线程并不是一直在运行，所以可以尽可能的多配置线程，比如 CPU 个数 * 2
 * CPU 密集型任务（大量复杂的运算）应当分配较少的线程，比如 CPU 个数相当的大小。
 *
 * @see http://ifeve.com/%e5%a6%82%e4%bd%95%e4%bc%98%e9%9b%85%e7%9a%84%e4%bd%bf%e7%94%a8%e5%92%8c%e7%90%86%e8%a7%a3%e7%ba%bf%e7%a8%8b%e6%b1%a0/#more-39888
 * @author xu.qiang
 * @date 18/9/10
 */
public class MutiThread {

    /**
     * 多线程的不能用单元测试，junit框架没有仔细研究，总之有问题
     */

    public static void main(String[] args) {
        //MutiThread.testMutiThreadByJdk();
        MutiThread.testMutiThreadByMydefine();
    }


    public static void testMutiThreadByJdk() {

        /*
         * 多线程创建类型
         *
         *  Executors.newFixedThreadPool(4);//固定线程数
         *  Executors.newCachedThreadPool();//可缓存的线程池
         *  Executors.newSingleThreadExecutor();//单线程化的线程池
         *  Executors.newScheduledThreadPool(1);//定长线程池，支持定时及周期性任务执行。
         *
         *  shutdown() 执行后停止接受新任务，会把队列的任务执行完毕。
         *  shutdownNow() 也是停止接受新任务，但会中断所有的任务，将线程池状态变为 stop。
         *
         *  BUT 我们一般都不会像上面这种方式来创建线程池
         */
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        int loop = 10;
        while ((loop--) > 0) {

            final int num = loop;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "__ print:" + num);
                }
            });
        }
    }


    public static void testMutiThreadByMydefine() {


        /*
         * 一般自定义线程池
         */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1,/*核心线程数*/
                4,/*最大线程数*/
                5,/*线程在终止前可以保持空闲的时间*/
                TimeUnit.SECONDS,/* keepAliveTime 参数的时间单位。*/
                new LinkedBlockingDeque<Runnable>(10),/*执行前用于保持任务的队列*/
                new RejectedExecutionHandler() {
                    /*由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序*/
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        try {
                            System.out.println("直接到这里了？？？？");
                            executor.getQueue().put(r);
                        } catch (InterruptedException e) {

                        }
                    }
                });
        /*
         * 如果在保持活动时间内没有任务到达，新任务到达时正在替换（如果需要），
         * 则设置控制核心线程是超时还是终止的策略。当为 false（默认值）时，由于没有传入任务，核心线程将永远不会中止。
         * 当为 true 时，适用于非核心线程的相同的保持活动策略也同样适用于核心线程。为了避免连续线程替换，
         * 保持活动时间在设置为 true 时必须大于 0。通常应该在主动使用该池前调用此方法。
         */
        threadPool.allowCoreThreadTimeOut(true);

        int loop = 15;
        while ((loop--) > 0) {

            final int num = loop;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "__ print:" + num);
                }
            });
        }
    }

}
