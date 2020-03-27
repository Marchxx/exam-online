package com.xiyou.common.mq;

import com.xiyou.common.config.SpringContextHolder;
import com.xiyou.common.mq.producer.RedisQueueProducer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DelayedMsgListener implements Runnable {

    private String queueName;

    private RedisQueueMessage queueMessage;

    public DelayedMsgListener(String queueName, RedisQueueMessage queueMessage) {
        this.queueName = queueName;
        this.queueMessage = queueMessage;
    }

    @Override
    public void run() {
        log.info("redis延迟消息执行:{}", queueName);
        RedisQueueProducer redisQueueProducer = SpringContextHolder.getBean(RedisQueueProducer.class);
        assert redisQueueProducer != null;
        redisQueueProducer.produceMessage(queueName, queueMessage);
    }
}
