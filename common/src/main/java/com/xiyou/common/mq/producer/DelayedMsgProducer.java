package com.xiyou.common.mq.producer;

import com.xiyou.common.mq.DelayedMsgListener;
import com.xiyou.common.mq.RedisQueueMessage;
import com.xiyou.common.mq.consumer.IRedisThreadPool;
import org.springframework.stereotype.Component;

/**
 * 延迟处理失败的消息
 */
@Component
public class DelayedMsgProducer {

    IRedisThreadPool redisThreadPool = IRedisThreadPool.getInstance();

    public void delayMsg(String queueName, RedisQueueMessage queueMessage) {
        if (queueMessage.REPEAT_PUSH_NUM <= 0) {
            // 重复推送次数用完
            return;
        }
        queueMessage.REPEAT_PUSH_NUM--;
        redisThreadPool.schedule(new DelayedMsgListener(queueName, queueMessage));
    }
}
