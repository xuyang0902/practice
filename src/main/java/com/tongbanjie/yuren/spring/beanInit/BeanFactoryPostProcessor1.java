package com.tongbanjie.yuren.spring.beanInit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 *
 *
 * BeanFactory 处理器  加强下beanFactory 都可以
 *
 *
 * @author xu.qiang
 * @date 18/10/16
 */
public class BeanFactoryPostProcessor1 implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor1 -- 》postProcessBeanFactory");
    }
}
