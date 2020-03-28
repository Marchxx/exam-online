package com.march.main.service;

import com.march.main.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    List<User> findAllUser();

    User getByAccount(String account);
}
