package com.tongbanjie.yuren.spring.beanInit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
 * bean加强器  在初始化bean前 | 后执行
 *
 * @author xu.qiang
 * @date 18/10/16
 */
public class BeanPostProcessor1 implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("BeanPostProcessor1--》postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor1--》postProcessAfterInitialization");

        return bean;

    }
}
