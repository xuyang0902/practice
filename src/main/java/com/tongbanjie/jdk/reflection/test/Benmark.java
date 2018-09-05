package com.tongbanjie.jdk.reflection.test;

import com.tongbanjie.jdk.reflection.UserService;
import com.tongbanjie.jdk.reflection.UserServiceImpl;
import com.tongbanjie.jdk.reflection.cglib.CglibProxy;
import com.tongbanjie.jdk.reflection.cglib.MyCglibInterceptor;
import com.tongbanjie.jdk.reflection.java.JDKInvocationHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author xu.qiang
 * @date 18/9/4
 */
public class Benmark {

    public static void main(String[] args) {

        //remotingCall(100000);
        jdkProxcy(1);
        //cglibProxy(100000);

    }

    /**
     * 执行100000次调用，共耗时：1822 微秒
     * 执行100000次调用，共耗时：1667 微秒
     * 执行100000次调用，共耗时：1686 微秒
     */
    public static void remotingCall(long loop) {

        UserService userService = new UserServiceImpl();

        long count = loop;
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
     */
    public static void jdkProxcy(long loop) {
        UserService userService = new UserServiceImpl();
        JDKInvocationHandler<UserService> jdkInvocationHandler = new JDKInvocationHandler<>(userService);

        long count = loop;
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
     * @param loop
     */
    public static void cglibProxy(int loop) {
        UserService userService = new UserServiceImpl();
        MyCglibInterceptor myCglibInterceptor = new MyCglibInterceptor();

        long count = loop;
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
     * 试试cglib的代理
     *
     *
     */
}
