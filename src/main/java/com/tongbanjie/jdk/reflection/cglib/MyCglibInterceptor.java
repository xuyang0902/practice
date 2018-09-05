package com.tongbanjie.jdk.reflection.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCglibInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("------------------before------------------");

        // 执行目标对象的方法
        Object intercept = methodProxy.invokeSuper(object, args);

        // 在目标对象的方法执行之后简单的打印一下
        System.out.println("-------------------after------------------");
        return intercept;
    }
}
