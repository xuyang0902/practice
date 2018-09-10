package com.tongbanjie.yuren.jdk.reflection.test;

import com.tongbanjie.yuren.jdk.reflection.UserService;
import com.tongbanjie.yuren.jdk.reflection.UserServiceImpl;
import com.tongbanjie.yuren.jdk.reflection.cglib.CglibProxy;
import com.tongbanjie.yuren.jdk.reflection.cglib.MyCglibInterceptor;
import com.tongbanjie.yuren.jdk.reflection.java.JDKInvocationHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author xu.qiang
 * @date 18/9/4
 */
public class Benmark {

    public static void main(String[] args) {
        Benmark.remotingCall();
        //Benmark.jdkProxcy();
        //Benmark.cglibProxy();
    }

    /**
     * 直接调用执行100000次调用，共耗时：2506 微秒
     */
    public static void remotingCall() {

        UserService userService = new UserServiceImpl();

        long count = 100000;
        long loopCount = count;

        long begin = System.nanoTime();
        while (loopCount-- > 0) {
            /*
             * 打印也要时间的，做性能测试的时候加上
             */
            System.out.println("------------------before------------------");
            userService.add();
            System.out.println("-------------------after------------------");
        }
        long end = System.nanoTime();

        System.out.println(String.format("执行%s次调用，共耗时：%s 微秒", count, TimeUnit.NANOSECONDS.toMillis((end - begin))));


    }

    /**
     * jdk动态代理执行100000次调用，共耗时：2771 微秒
     */
    public static void jdkProxcy() {
        UserService userService = new UserServiceImpl();
        JDKInvocationHandler<UserService> jdkInvocationHandler = new JDKInvocationHandler<>(userService);

        long count = 100000;
        long loopCount = count;

        long begin = System.nanoTime();
        while (loopCount-- > 0) {
            UserService proxy = jdkInvocationHandler.getProxy();
            proxy.add();
        }
        long end = System.nanoTime();

        System.out.println(String.format("执行%s次调用，共耗时：%s 微秒", count, TimeUnit.NANOSECONDS.toMillis((end - begin))));


    }


    /**
     * cglib动态代理执行100000次调用，共耗时：5489 微秒
     */
    public static void cglibProxy() {
        UserService userService = new UserServiceImpl();
        MyCglibInterceptor myCglibInterceptor = new MyCglibInterceptor();

        long count = 100000;
        long loopCount = count;

        long begin = System.nanoTime();
        while (loopCount-- > 0) {
            UserService proxy = CglibProxy.getProxy(myCglibInterceptor, userService.getClass());
            proxy.add();
        }
        long end = System.nanoTime();

        System.out.println(String.format("执行%s次调用，共耗时：%s 微秒", count, TimeUnit.NANOSECONDS.toMillis((end - begin))));


    }

    /**
     *
     * jdk动态代理测试 10万次调用  平均耗时2500微秒。
     * java直接调用 10万次调用 平均耗时1600微妙。
     *
     * 从数据来看，还是微乎其微，不过讲道理jdk动态代理也是生成字节码文件的clazz，应该性能还可以。
     *
     *
     * 试试cglib的代理
     * jdk1.7下面 cglib性能跑不过jdk的动态代理？？？？
     *
     */
}
