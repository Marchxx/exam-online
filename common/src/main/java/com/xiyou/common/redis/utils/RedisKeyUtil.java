package com.xiyou.common.redis.utils;

/**
 * @program: multi-module
 * @description: RedisKey的设计
 * @author: tangcan
 * @create: 2019-06-17 20:09
 **/
public class RedisKeyUtil {

    public static String get(String... keys) {
        StringBuilder builder = new StringBuilder();
        for (String key : keys) {
            if (builder.length() > 0) {
                builder.append(':');
            }
            builder.append(key);
        }
        return builder.toString();
    }
}
