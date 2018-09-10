package com.tongbanjie.yuren.jdk.reflection.clazz;

import com.tongbanjie.yuren.jdk.reflection.Demo;

import java.lang.reflect.Method;

/**
 * @author xu.qiang
 * @date 18/9/3
 */
public class ClassHelloWorld {

    public static void main(String[] args) {

        try {
            /*
             * 反射加载到clazz对象
             */
            Class<Demo> clazz = (Class<Demo>) Class.forName(Demo.class.getName());

            //创建一个实例
            Demo demo = clazz.newInstance();

            //clazz拿到setName方法
            Method setName = clazz.getMethod("setName", String.class);

            //利用反射调用setName方法
            Object haha = setName.invoke(demo, "haha");

            System.out.println(demo.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
