package com.tongbanjie.yuren.jdk.reflection.cglib;

import com.tongbanjie.yuren.jdk.reflection.UserService;
import com.tongbanjie.yuren.jdk.reflection.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author xu.qiang
 * @date 18/9/4
 */
public class CglibProxy {


    public static <T> T getProxy(MethodInterceptor methodInterceptor, Class<T> target) {


        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target);
        enhancer.setCallback(methodInterceptor);

        return (T) enhancer.create();
    }


    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        MyCglibInterceptor myCglibInterceptor = new MyCglibInterceptor();
        UserService proxy = CglibProxy.getProxy(myCglibInterceptor, userService.getClass());
        proxy.add();
    }
}
