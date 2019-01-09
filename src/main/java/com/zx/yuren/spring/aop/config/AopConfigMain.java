package com.zx.yuren.spring.aop.config;

import com.zx.yuren.spring.aop.IPerson;
import com.zx.yuren.spring.bean.MySpringBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xu.qiang
 * @date 19/1/2
 */
public class AopConfigMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/aop-config.xml");
        IPerson iPerson = applicationContext.getBean("personImpl", IPerson.class);

        iPerson.say("hello");

    }
}
