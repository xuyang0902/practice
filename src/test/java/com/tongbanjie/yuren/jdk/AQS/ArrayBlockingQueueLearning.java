package com.tongbanjie.yuren.jdk.AQS;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author xu.qiang
 * @date 18/7/18
 */
public class ArrayBlockingQueueLearning {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<Object>(10);
        arrayBlockingQueue.put(new Integer(1));


        /**
         * arrayBlockingQueue的源码了解一下
         */


    }
}
