package com.zx.yuren.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xu.qiang
 * @date 18/5/15
 */
public class SpringMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/bean.xml");
        MySpringBean mySpringBean = applicationContext.getBean("mySpringBean", MySpringBean.class);


        System.out.println(mySpringBean);

    }
}
