package com.xiyou.common.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @program: multi-module
 * @description: Redis缓存工具
 * @author: tangcan
 * @create: 2019-06-17 14:14
 **/
@Component
public class IRedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public IRedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取key的剩余生存时间
     */
    public long getExpire(String key) {
        Long ret = redisTemplate.getExpire(key);
        return ret == null ? 0 : ret;
    }

    /**
     * 设置key的过期时间
     */
    public boolean setExpire(String key, long timeout) {
        Boolean ret = redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        return ret != null && ret;
    }

    public boolean setExpire(String key, long timeout, TimeUnit timeUnit) {
        Boolean ret = redisTemplate.expire(key, timeout, timeUnit);
        return ret != null && ret;
    }

    /**
     * 将 key 所储存的值加上增量 delta
     */
    public long inc(String key, long delta) {
        Long ret = redisTemplate.opsForValue().increment(key, delta);
        return ret == null ? 0 : ret;
    }

    /**
     * 查找所有符合给定模式pattern的key
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除keys
     */
    public long delete(Collection<String> keys) {
        Long ret = redisTemplate.delete(keys);
        return ret == null ? 0 : ret;
    }

    /**
     * 设置key的值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 获取key的值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /*
    存储Hash的操作，Map
     */

    /**
     * 将哈希表 key中的Hash键 hKey value
     */
    public void hmSetValue(String key, String hKey, Object hValue) {
        redisTemplate.opsForHash().put(key, hKey, hValue);
    }

    public void hmSetMap(String key, Map<String, Object> hValues) {
        redisTemplate.opsForHash().putAll(key, hValues);
    }

    /**
     * 返回哈希表 key中的Hash键 hKey的值
     */
    public Object hmGetValue(String key, String hKey) {
        return redisTemplate.opsForHash().get(key, hKey);
    }

    public Map<String, Object> hmGetMap(String key) {
        // 获取缓存值
        Map<Object, Object> objectMap = redisTemplate.opsForHash().entries(key);
        // 判断是否为空
        if (objectMap.isEmpty()) {
            return null;
        }
        // 将Map<Object, Object> 转成 Map<String, Object>
        Map<String, Object> stringMap = new HashMap<>();
        objectMap.forEach((k, v) -> stringMap.put(String.valueOf(k), v));
        return stringMap;
    }

    public List<Object> hmMultiGet(String key, Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除哈希表 key中的Hash键 hKey的值
     */
    public long hmDelete(String key, Object... hKeys) {
        return redisTemplate.opsForHash().delete(key, hKeys);
    }

    /**
     * 查询元素个数
     */
    public long hmSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /*
    存储Set的操作
     */

    /**
     * 查询key存储的Set值
     */
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 往Set中存入数据
     */
    public long sSet(String key, Object... sValues) {
        Long ret = redisTemplate.opsForSet().add(key, sValues);
        return ret == null ? 0 : ret;
    }

    /**
     * 查询Set是否存在sValue
     */
    public boolean sHasValue(String key, Object sValue) {
        Boolean ret = redisTemplate.opsForSet().isMember(key, sValue);
        return ret != null && ret;
    }

    /**
     * 移除Set中的sValues
     */
    public long sRemove(String key, Object... sValues) {
        Long ret = redisTemplate.opsForSet().remove(key, sValues);
        return ret == null ? 0 : ret;
    }

    /**
     * 查询Set元素个数
     */
    public long sSize(String key) {
        Long ret = redisTemplate.opsForSet().size(key);
        return ret == null ? 0 : ret;
    }

    /*
    存储List的操作
     */

    /**
     * 往List左边添加数据
     */
    public long lLeftPush(String key, Object lValue) {
        Long ret = redisTemplate.opsForList().leftPush(key, lValue);
        return ret == null ? 0 : ret;
    }

    public long lLeftPushAll(String key, Object... lValues) {
        Long ret = redisTemplate.opsForList().leftPushAll(key, lValues);
        return ret == null ? 0 : ret;
    }

    /**
     * 移除并返回List左边的第一个元素
     */
    public Object lLeftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public long lRightPush(String key, Object lValue) {
        Long ret = redisTemplate.opsForList().rightPush(key, lValue);
        return ret == null ? 0 : ret;
    }

    public long lRightPushAll(String key, Object... lValues) {
        Long ret = redisTemplate.opsForList().rightPushAll(key, lValues);
        return ret == null ? 0 : ret;
    }

    /**
     * 移除并返回List右边的第一个元素
     */
    public Object lRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public Object lRightPop(String key, long waitTime, TimeUnit timeUnit) {
        return redisTemplate.opsForList().rightPop(key, waitTime, timeUnit);
    }

    /**
     * 查询List元素个数
     */
    public long lSize(String key) {
        Long ret = redisTemplate.opsForList().size(key);
        return ret == null ? 0 : ret;
    }

    /**
     * 从List中获取begin到end之间的元素（start=0，end=-1表示获取全部元素）
     */
    public List<Object> lGet(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }
}
