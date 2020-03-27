package com.xiyou.common.mq.consumer;

import com.xiyou.common.mq.RedisQueueListener;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 消费者容器
 */
@Slf4j
public class ConsumerContainer {
    // 存放消费者的Map Key：消费者队列名 Value：消费者对象
    Map<String, RedisQueueConsumer> consumerMap = new HashMap<>();

    // 容器是否初始化完成
    public static boolean run = false;

    public static IRedisThreadPool redisThreadPool;

    /**
     * 添加消费者
     *
     * @param consumer 消费者
     */
    public void addConsumer(RedisQueueConsumer consumer) {
        // 判断消费者队列名
        if (null == consumer.queueName()) {
            log.info("添加消费者失败：{}", "队列名为null");
            return;
        }
        // 如果没有加入过，便加入
        if (consumerMap.get(consumer.queueName()) == null) {
            consumerMap.put(consumer.queueName(), consumer);
            log.info("添加消费者成功：{}", consumer.queueName());
            return;
        }
        log.info("添加消费者失败：{}", "重复添加");
    }

    public void destroy() {
        log.info("redis消费者容器已被销毁");
        redisThreadPool.destroy();
    }

    public void init() {
        log.info("redis消费者容器初始化开始");
        redisThreadPool = IRedisThreadPool.getInstance();
        consumerMap.values().forEach(V -> {
            // 执行监听器
            redisThreadPool.executor(new RedisQueueListener(V));
        });
        run = true;
        log.info("redis消费者容器初始化结束");
    }
}
