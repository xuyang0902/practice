package com.zx.yuren.distribute.lock;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xu.qiang
 * @date 18/9/12
 */
public class RedisLockTest {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("redis.xml");
        final RedisLockComponent redisLockUtil = applicationContext.getBean("redisLockUtil", RedisLockComponent.class);


        ExecutorService executorService = Executors.newFixedThreadPool(16);

        int loop = 16;
        while(loop-- > 0){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(redisLockUtil.tryLock("R",100)){
                            System.out.println(Thread.currentThread().getName() + "========>>>>>抢到锁了");
                            return;
                        }
                        System.out.println(Thread.currentThread().getName() + "没抢到锁了");

                    }finally {
                        redisLockUtil.release("R");
                    }
                }
            });
        }




    }

}
