package com.zx.yuren.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * Hello world!
 */
public class MySpringBean implements BeanDefinitionRegistryPostProcessor, BeanPostProcessor,
        BeanFactoryAware, InitializingBean, DisposableBean, BeanFactoryPostProcessor, BeanNameAware {


    public MySpringBean() {
        System.out.println("构造器 MySpringBean");
    }

    public void init() {

        System.out.println("执行MySpringBean.init");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("执行 BeanFactoryAware.setBeanFactory");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("执行 BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("执行 BeanFactoryPostProcessor.postProcessBeanFactory");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行 BeanPostProcessor.postProcessBeforeInitialization beanName:" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行 BeanPostProcessor.postProcessAfterInitialization  beanName:" + beanName);

        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行 InitializingBean.afterPropertiesSet");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("执行 BeanNameAware.setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行 DisposableBean.destroy");
    }
}
