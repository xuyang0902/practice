package com.zx.yuren.spring.aop.config;


import org.aspectj.lang.JoinPoint;

/**
 * @author xu.qiang
 * @date 19/1/2
 */
public class PersonAdvices {


    public void before(){
        System.out.println("---before");
    }

    public void after(JoinPoint joinPoint){
        System.out.println("---after");
    }
}
