package com.march.main.service;

import com.march.main.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.march.main.params.GetUserListParam;

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

    User getInfo(Integer id);

    int batchDeleteUserByIds(Integer[] ids);

    List<User> findUserListById(GetUserListParam param);
}
