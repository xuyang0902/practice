package com.zx.yuren.spring.beanInit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * spring最早执行的最开始执行
 *
 * @author xu.qiang
 * @date 18/10/16
 */
public class BeanDefinitionRegistryPostProcessor1 implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("BeanDefinitionRegistryPostProcessor1 - 》 postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanDefinitionRegistryPostProcessor1 - 》 postProcessBeanFactory");
    }
}
