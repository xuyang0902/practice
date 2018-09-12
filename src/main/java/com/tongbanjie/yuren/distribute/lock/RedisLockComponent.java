package com.tongbanjie.yuren.distribute.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;

/**
 * redis分布式锁.<br>
 * 思路：
 * <pre>
 * 用SETNX命令，SETNX只有在key不存在时才返回成功。这意味着只有一个线程可以成功运行SETNX命令，而其他线程会失败，然后不断重试，直到它们能建立锁。
 * 然后使用脚本来创建锁，因为一个redis脚本同一时刻只能运行一次。
 * 创建锁代码：
 * <code>
 * -- KEYS[1] key,
 * -- ARGV[1] value,
 * -- ARGV[2] expireTimeMilliseconds
 *
 * if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then
 * redis.call('pexpire', KEYS[1], ARGV[2])
 * return 1
 * else
 * return 0
 * end
 * </code>
 * 最后使用脚本来解锁。
 * 解锁代码：
 *
 * <code>
 * -- KEYS[1] key,
 * -- ARGV[1] value
 * if redis.call("get", KEYS[1]) == ARGV[1]
 * then
 * return redis.call("del", KEYS[1])
 * else
 * return 0
 * end
 * </code>
 * </pre>
 *
 * @author xu.qiang
 */
public class RedisLockComponent {

    private static final Long SUCCESS = 1L;

    private RedisTemplate redisTemplate;

    private RedisScript<Integer> lockRedisScript;

    private RedisScript<Integer> unLockRedisScript;

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey      锁
     * @param milliseconds 超期时间，多少毫秒后这把锁自动释放
     */
    public boolean tryLock(String lockKey, int milliseconds) {

        Object result = redisTemplate.execute(lockRedisScript,
                Collections.singletonList(lockKey),// KEYS[1]
                "1", // ARGV[1]
                milliseconds // ARGV[2]
        );

        return SUCCESS.equals(result);
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey 锁
     */
    public boolean release(String lockKey) {

        Object result = redisTemplate.execute(unLockRedisScript,
                Collections.singletonList(lockKey),// KEYS[1]
                "1");// ARGV[1]

        return SUCCESS.equals(result);
    }


    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisScript<Integer> getLockRedisScript() {
        return lockRedisScript;
    }

    public void setLockRedisScript(RedisScript<Integer> lockRedisScript) {
        this.lockRedisScript = lockRedisScript;
    }

    public RedisScript<Integer> getUnLockRedisScript() {
        return unLockRedisScript;
    }

    public void setUnLockRedisScript(RedisScript<Integer> unLockRedisScript) {
        this.unLockRedisScript = unLockRedisScript;
    }
}