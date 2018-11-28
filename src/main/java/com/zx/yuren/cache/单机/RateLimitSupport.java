package com.zx.yuren.cache.单机;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 限流工具类
 * QPS上限，超出拒绝
 *
 * 典型的令牌桶
 * @author xu.qiang
 * @date 18/9/11
 */
public class RateLimitSupport {

    private static RateLimiter rateLimiter = null;

    public RateLimitSupport() {

    }

    public RateLimitSupport(double permitsPerSecond) {
        rateLimiter = RateLimiter.create(permitsPerSecond);
    }

    public <T> T lock(CallBack<T> callBack) {

        //如果系统请求次数草果限制了
        if (!rateLimiter.tryAcquire()) {
            return callBack.runRefuse(null);
        }

        try {
            return callBack.run();
        } catch (Exception e) {
            return callBack.runException(e);
        }

    }


}
