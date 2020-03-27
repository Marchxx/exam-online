package com.xiyou.common.mq.consumer;

import com.xiyou.common.mq.RedisQueueMessage;

/**
 * 消费者接口
 */
    public interface RedisQueueConsumer {

    // 获取队列名称
    String queueName();

    // 消费消息
    void consumerMessage(RedisQueueMessage queueMessage);

    // 获取监听器返回的错误消息
    void error(String error);
}
