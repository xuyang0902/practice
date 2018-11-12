package com.tongbanjie.yuren.jdk.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * compare方法单元测试
 * @author xu.qiang
 * @date 18/10/22
 */
public class CompareTest {


    /**
     * Comparable 内比较器
     *
     * Comparator 外比较器
     * @param args
     */
    public static void main(String[] args) {

        Integer one = 1;
        Integer two = 2;

        System.out.println(one.compareTo(two));

        Person women18 = new Person(18,1,"yz18");
        Person women19 = new Person(19,1,"yz19");
        Person women20 = new Person(20,1,"yz20");
        System.out.println(women18.compareTo(women19));


        Person man18 = new Person(18,0,"xuyang18");
        Person man19 = new Person(19,0,"xuyang19");
        Person man20 = new Person(20,0,"xuyang20");
        System.out.println(women18.compareTo(man18));

        ArrayList<Person> list = new ArrayList<Person>(2<<8);
        list.add(women18);
        list.add(women19);
        list.add(women20);
        list.add(man18);
        list.add(man19);
        list.add(man20);

        Collections.sort(list);

        System.out.println("内排序：>>>>年龄从小到大 性别从男到女");
        for (Person person : list) {
            System.out.println(person.toString());
        }

    }


}
