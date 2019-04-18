package com.zx.yuren;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xu.qiang
 * @date 19/2/13
 */
public class Looper{

    public static void main(String[] args) throws InterruptedException {

        new Looper().test();

    }

    AtomicInteger atomicInteger = new AtomicInteger(100);

    public synchronized void test(){

        System.out.println(atomicInteger.get());

        if(atomicInteger.decrementAndGet() == 0){
            return;
        }

        test();

    }
}
