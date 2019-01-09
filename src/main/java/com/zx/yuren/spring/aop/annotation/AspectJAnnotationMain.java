package com.zx.yuren.spring.aop.annotation;

import com.zx.yuren.spring.aop.IPerson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xu.qiang
 * @date 19/1/2
 */
public class AspectJAnnotationMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:**/spring/aop-annotation.xml");
        IPerson iPerson = applicationContext.getBean("personImpl", IPerson.class);

        iPerson.say("hello");

    }
}
