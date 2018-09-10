package com.tongbanjie.yuren.jdk.reflection.aopframework;

import java.lang.reflect.Method;

public class MyAdvice implements Advice {
	long beginTime = 0;
	public void afterMethod(Method method) {
		// TODO Auto-generated method stub
		System.out.println("afterMethod");
		long endTime = System.currentTimeMillis();
		System.out.println(method.getName() + " running time of " + (endTime - beginTime));

	}

	public void beforeMethod(Method method) {
		// TODO Auto-generated method stub
		System.out.println("beforeMethod");
		beginTime = System.currentTimeMillis();
	}

}
