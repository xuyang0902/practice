package com.tongbanjie.yuren.jdk.map;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xu.qiang
 * @date 18/9/10
 */
public class ConcurrentHashMapTest {

    public void testConcurrentHashMap() {
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("key1", "value1");

        System.out.println(String.valueOf(concurrentHashMap.get("key1")));


        /**
         * ConcurrentHashMap也是一个数组+链表的结构
         *
         * jdk1.7:
         * final Segment<K,V>[] segments; //核心数据结构
         * 可以简单理解key定位到segment的位置，put前 会lock住
         *
         * 这样的话，每个segment是可以支持并发写和读的，但是读取效率依旧比较低
         *
         *
         * jdk1.8抛弃了原有的 Segment 分段锁，而采用了 CAS + synchronized 来保证并发安全性。
         */

        Iterator<Map.Entry<String, Object>> entryIterator = concurrentHashMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Object> next = entryIterator.next();
            System.out.println("key=" + next.getKey() + " value=" + next.getValue());
        }


    }
}
