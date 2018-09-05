package com.tongbanjie.jdk.reflection.cglib;

import com.tongbanjie.jdk.reflection.UserService;
import com.tongbanjie.jdk.reflection.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xu.qiang
 * @date 18/9/4
 */
public class CglibProxy {

    public static ConcurrentHashMap<Class, Object> cacheProxy = new ConcurrentHashMap<>();

    public static <T> T getProxy(MethodInterceptor methodInterceptor, Class<T> target) {

        Object object = cacheProxy.get(target);
        if (object != null) {
            return (T) object;
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target);
        enhancer.setCallback(methodInterceptor);

        T t = (T) enhancer.create();
        cacheProxy.put(target, t);
        return t;
    }


    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        MyCglibInterceptor myCglibInterceptor = new MyCglibInterceptor();
        UserService proxy = CglibProxy.getProxy(myCglibInterceptor, userService.getClass());
        proxy.add();
    }
}
