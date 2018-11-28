package com.zx.yuren.jdk.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * hashMap单元测试
 *
 * @author xu.qiang
 * @date 18/9/10   http://ifeve.com/hashmap-concurrenthashmap
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("key1", "value1");

        System.out.println(map.get("key1"));

        /**
         *jdk1.7：
         *
         * 0.Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;// 真正存放数据的数组  数组 + 链表。  Value是一个链表结构
         * 1、当元素大于 总容量*因子数的时候就需要扩容；
         * 2、 key计算出hashcode。然后根据hashCode定位到table的位置。
         *     put:遍历V，如果key一样就直接覆盖，否则添加
         *     get:遍历V，如果key一样直接取出，没取到则返回null
         *
         * 当 Hash 冲突严重时，在桶上形成的链表会变的越来越长，这样在查询时的效率就会越来越低；时间复杂度为 O(N)。
         *
         *
         * jdk1.8:引入红黑树
         *
         *
         * hashmap不支持并发。。有可能会死循环，因为在resize  @see ConcurrentHashMap
         */

        Iterator<Map.Entry<String, Object>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Object> next = entryIterator.next();
            System.out.println("key=" + next.getKey() + " value=" + next.getValue());
        }

    }

}
