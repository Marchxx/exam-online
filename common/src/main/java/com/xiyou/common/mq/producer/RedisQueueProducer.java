package com.xiyou.common.mq.producer;

import com.xiyou.common.redis.IRedisService;
import com.xiyou.common.mq.RedisQueueMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 */
@Component
public class RedisQueueProducer {
    @Autowired
    private IRedisService redisService;

    public void produceMessage(String queueName, RedisQueueMessage queueMessage) {
        redisService.lLeftPushAll(queueName, queueMessage);
    }
}
