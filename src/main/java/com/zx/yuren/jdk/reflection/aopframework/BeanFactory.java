package com.zx.yuren.jdk.reflection.aopframework;

public class BeanFactory {
    public BeanFactory() {
    }

    public Object getBean(String targetClass) {
        Object bean = null;
        try {
            Class clazz = Class.forName("com.zx.yuren.jdk.reflection.aopframework.ProxyFactoryBean");
            bean = clazz.newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (bean instanceof ProxyFactoryBean) {
            Object proxy = null;
            ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) bean;
            try {
                Advice advice = (Advice) Class.forName("com.zx.yuren.jdk.reflection.aopframework.MyAdvice").newInstance();
                Object target = Class.forName(targetClass).newInstance();
                proxyFactoryBean.setAdvice(advice);
                proxyFactoryBean.setTarget(target);
                proxy = proxyFactoryBean.getProxy();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return proxy;
        }
        return bean;
    }
}
