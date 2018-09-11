package com.tongbanjie.yuren.cache.单机;

/**
 * 回调方法
 * @author xu.qiang
 * @param <T>
 */
public interface CallBack<T> {

    /**
     * 超过限制了，拒绝
     */
    T runRefuse(Exception e);

    /**
     * 通过限制，执行真正的业务逻辑
     */
    T run();

    /**
     * 业务逻辑异常 处理
     */
    T runException(Exception e);

}