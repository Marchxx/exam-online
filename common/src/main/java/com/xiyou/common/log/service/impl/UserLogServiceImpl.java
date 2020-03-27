package com.xiyou.common.log.service.impl;

import com.xiyou.common.log.entity.UserLog;
import com.xiyou.common.log.dao.UserLogMapper;
import com.xiyou.common.log.service.UserLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangcan
 * @since 2019-06-14
 */
@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog> implements UserLogService {

}
