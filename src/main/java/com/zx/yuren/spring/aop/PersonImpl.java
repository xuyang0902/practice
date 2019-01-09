package com.zx.yuren.spring.aop;

/**
 * @author xu.qiang
 * @date 19/1/2
 */
public class PersonImpl implements IPerson {

    @Override
    public void say(String text) {
        System.out.println(text);
    }

}
