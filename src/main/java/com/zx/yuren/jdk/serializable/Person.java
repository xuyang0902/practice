package com.zx.yuren.jdk.serializable;

import java.io.*;

/**
 * @author xu.qiang
 * @date 19/2/11
 */
public class Person implements Serializable {


    private static final long serialVersionUID = 1L;


    private String name;

    /**
     * transient 不需要序列化
     */
    private /*transient*/ Integer age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
