package com.tongbanjie.yuren.jdk.reflection.aopframework;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class AopFrameworkTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Object bean = new BeanFactory().getBean(ArrayList.class.getName());
		System.out.println(bean.getClass().getName());
		((Collection)bean).clear();
	}

}
