package com.xiyou.common.mq;

import com.xiyou.common.config.SpringContextHolder;
import com.xiyou.common.redis.IRedisService;
import com.xiyou.common.mq.consumer.ConsumerContainer;
import com.xiyou.common.mq.consumer.RedisQueueConsumer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisQueueListener implements Runnable {

    private RedisQueueConsumer redisQueueConsumer;

    public RedisQueueListener(RedisQueueConsumer redisQueueConsumer) {
        this.redisQueueConsumer = redisQueueConsumer;
    }

    @Override
    public void run() {
        IRedisService redisService = SpringContextHolder.getBean(IRedisService.class);
        String queueName = redisQueueConsumer.queueName();
        log.info("redis监听器开始监听:{}", queueName);
        while (ConsumerContainer.run) {
            try {
                long WAIT_TIME = 3000;
                Object object = redisService.lRightPop(queueName, WAIT_TIME, TimeUnit.MILLISECONDS);
                if (object != null) {
                    redisQueueConsumer.consumerMessage((RedisQueueMessage) object);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
