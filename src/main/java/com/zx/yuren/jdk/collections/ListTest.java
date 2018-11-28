package com.zx.yuren.jdk.collections;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 不要小看每一个单元test，debug 读完背后的逻辑
 * @author xu.qiang
 * @date 18/10/11
 */
public class ListTest {

    public static void main(String[] args) {


        System.out.println("-----------------ArrayList-------------------");
        ArrayList<Integer> arrayList = new ArrayList<Integer>(16);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(1);
        for (Integer integer : arrayList) {
            System.out.println(integer);
        }

        System.out.println("-----------------Vector 同步-------------------");

        Vector<Integer> vector = new Vector<Integer>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(1);
        for (Integer integer : vector) {
            System.out.println(integer);
        }




    }


}
