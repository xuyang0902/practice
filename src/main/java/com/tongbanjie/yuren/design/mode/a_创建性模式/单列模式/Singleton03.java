package com.tongbanjie.yuren.design.mode.a_创建性模式.单列模式;

/**
 * 枚举实现单列
 * @author xu.qiang
 * @date 2018/9/10
 */
public enum  Singleton03 {
    INSTANCE;

    private Person person;

    Singleton03() {
        this.person = new Person();
    }

}
