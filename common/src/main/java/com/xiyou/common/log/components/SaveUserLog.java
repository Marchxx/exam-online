package com.xiyou.common.log.components;

import com.xiyou.common.log.entity.UserLog;
import com.xiyou.common.log.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @program: project
 * @description: 用户操作日志存库
 * @author: tangcan
 * @create: 2019-06-14 20:52
 **/
@Component
public class SaveUserLog {
    @Autowired
    UserLogService userLogService;

    @Async
    public void save(UserLog userLog) {
        userLog.insert();
    }
}
