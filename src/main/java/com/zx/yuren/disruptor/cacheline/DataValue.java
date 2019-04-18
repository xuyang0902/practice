package com.zx.yuren.disruptor.cacheline;

/**
 * @author xu.qiang
 * @date 19/4/17
 */
public class DataValue {
    private volatile long value = 0L;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
