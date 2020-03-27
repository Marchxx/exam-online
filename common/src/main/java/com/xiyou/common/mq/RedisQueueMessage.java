package com.xiyou.common.mq;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RedisQueueMessage implements Serializable {

    public int REPEAT_PUSH_NUM = 1;

    // 消息
    private String content;

    public RedisQueueMessage(String content) {
        this.content = content;
    }
}
