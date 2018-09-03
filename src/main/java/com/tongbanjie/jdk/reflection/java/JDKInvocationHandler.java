package com.tongbanjie.jdk.reflection.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xu.qiang
 * @date 18/9/3
 */
public class JDKInvocationHandler<T> implements InvocationHandler {


    // 目标对象
    private T target;

    /**
     * 构造方法
     *
     * @param target 目标对象
     */
    public JDKInvocationHandler(T target) {
        super();
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("------------------before------------------");

        // 执行目标对象的方法
        Object result = method.invoke(target, args);

        // 在目标对象的方法执行之后简单的打印一下
        System.out.println("-------------------after------------------");

        return result;
    }

    /**
     * 获取目标对象的代理对象
     *
     * @return 代理对象
     */
    public T getProxy() {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }


    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        JDKInvocationHandler<UserService> jdkInvocationHandler = new JDKInvocationHandler<>(userService);

        UserService proxy = jdkInvocationHandler.getProxy();

        //com.tongbanjie.jdk.reflection.java.UserServiceImpl@3fe329eb
        proxy.add();
    }

}
