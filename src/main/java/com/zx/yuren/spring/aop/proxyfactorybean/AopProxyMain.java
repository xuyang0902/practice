package com.zx.yuren.spring.aop.proxyfactorybean;

import com.zx.yuren.spring.aop.IPerson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xu.qiang
 * @date 19/1/3
 */
public class AopProxyMain {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/aop-proxyfactorybean.xml");
        IPerson iPerson = applicationContext.getBean("person", IPerson.class);

        iPerson.say("hello");

    }


}
