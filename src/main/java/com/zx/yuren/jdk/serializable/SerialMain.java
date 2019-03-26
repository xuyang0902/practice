package com.zx.yuren.jdk.serializable;

import java.io.*;

/**
 * @author xu.qiang
 * @date 19/2/11
 */
public class SerialMain {


    public static void main(String[] args) throws Exception {


        //序列化  对象-->> 字节数组 （包含类信息，属性值信息）
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/usr/local/tmp/person.txt"));

        Person person = new Person();
        person.setName("xuqiang");
        person.setAge(18);
        oos.writeObject(person);
        oos.flush();
        oos.close();


        /**
         * Exception in thread "main" java.io.InvalidClassException: com.zx.yuren.jdk.serializable.Person;
         * local class incompatible: stream classdesc serialVersionUID = 9165284443684839337, local class serialVersionUID = 1
         *
         * jdk的序列化和反序列化  主要serialVersionID
         */

        //反序列化  字节数组--> 对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/usr/local/tmp/person.txt"));
        Person object = (Person)ois.readObject();

        System.out.println(object);
    }


}
