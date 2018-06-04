package com.kyle.framework.utils;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author -- zhangkai02
 *         6/5/18 05:49
 */
public class RedisUtils {

    private static RedisTemplate REDIS_TEMPLATE;

    public void setRedisTemplate(RedisTemplate redisTemplate){
        REDIS_TEMPLATE = redisTemplate;
    }

}
