package com.zx.yuren.jdk.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xu.qiang
 * @date 18/7/18
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<Object>(10);
        arrayBlockingQueue.put(new Integer(1));
        arrayBlockingQueue.put(new Integer(2));
        arrayBlockingQueue.put(new Integer(3));
        arrayBlockingQueue.put(new Integer(4));
        arrayBlockingQueue.put(new Integer(5));

        Object take;
        take = arrayBlockingQueue.take();

        System.out.println(Integer.valueOf(take.toString()));

        /**
         * arrayBlockingQueue的源码了解一下
         */


        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(10);
        linkedBlockingQueue.take();
    }
}
