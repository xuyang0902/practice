package com.tongbanjie.yuren.spring.beanInit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xu.qiang
 * @date 18/5/15
 */
public class SpringStart {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        App apps = applicationContext.getBean("app", App.class);

        System.out.println(apps.getAppName());

    }
}
