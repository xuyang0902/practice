package com.zx.yuren.cache.单机;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流工具类
 * 超过应用设定接口最大并发数上限；超过拒绝
 *
 * 漏桶
 * @author xu.qiang
 * @date 18/9/11
 */
public class LimitSupport {

    private int maxReqNum = 100;

    private AtomicInteger currentNum = new AtomicInteger(0);

    public LimitSupport() {
    }

    public LimitSupport(int maxReqNum) {
        this.maxReqNum = maxReqNum;
    }

    public <T> T lock(CallBack<T> callBack) {

        try {

            //如果系统请求次数草果限制了
            if (currentNum.incrementAndGet() > maxReqNum) {
                return callBack.runRefuse(null);
            }

            try {
                return callBack.run();
            } catch (Exception e) {
                return callBack.runException(e);
            }

        } finally {
            currentNum.decrementAndGet();
        }

    }


}
