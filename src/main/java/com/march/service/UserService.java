package com.march.service;

import com.march.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author March
 * @since 2020-03-21
 */
public interface UserService extends IService<User> {

    User findUserById(Integer id);
}
