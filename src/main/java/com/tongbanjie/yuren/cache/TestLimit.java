package com.tongbanjie.yuren.cache;

import com.tongbanjie.yuren.cache.单机.CallBack;
import com.tongbanjie.yuren.cache.单机.LimitSupport;
import com.tongbanjie.yuren.cache.单机.RateLimitSupport;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xu.qiang
 * @date 18/9/11
 */
public class TestLimit {

    public static void main(String[] args) {
        testGoogleLimit();
    }


    public static void testjdkLimit(){
        final LimitSupport limitSupport = new LimitSupport(8);

        ExecutorService executorService = Executors.newFixedThreadPool(16);

        int loop = 20;
        while (loop-- > 0) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    limitSupport.lock(new CallBack<Boolean>() {
                        @Override
                        public Boolean runRefuse(Exception e) {

                            System.out.println(Thread.currentThread().getName() + "  超过并发限制了，拒绝");
                            return false;
                        }

                        @Override
                        public Boolean run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName() + "  通过限制，执行业务逻辑");
                            return true;
                        }

                        @Override
                        public Boolean runException(Exception e) {
                            System.out.println(Thread.currentThread().getName() + "  正常业务逻辑执行异常");
                            return false;
                        }
                    });
                }
            });
        }
    }

    public static void testGoogleLimit(){
        final RateLimitSupport limitSupport = new RateLimitSupport(10);

        ExecutorService executorService = Executors.newFixedThreadPool(16);

        int loop = 20;
        while (loop-- > 0) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    limitSupport.lock(new CallBack<Boolean>() {
                        @Override
                        public Boolean runRefuse(Exception e) {

                            System.out.println(Thread.currentThread().getName() + "  超过并发限制了，拒绝");
                            return false;
                        }

                        @Override
                        public Boolean run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName() + "  通过限制，执行业务逻辑");
                            return true;
                        }

                        @Override
                        public Boolean runException(Exception e) {
                            System.out.println(Thread.currentThread().getName() + "  正常业务逻辑执行异常");
                            return false;
                        }
                    });
                }
            });
        }
    }
}
