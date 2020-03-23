package com.march.service.impl;

import com.march.entity.User;
import com.march.dao.UserMapper;
import com.march.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author March
 * @since 2020-03-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findUserById(Integer id) {
        User user=new User();
        user.setUserId(id);
        return user.selectById();
    }
}
