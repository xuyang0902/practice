package com.zx.yuren.spring.aop.proxyfactorybean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author xu.qiang
 * @date 19/1/3
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("------before-----");
        Object proceed = methodInvocation.proceed();
        System.out.println("------after-----");


        return proceed;

    }
}
