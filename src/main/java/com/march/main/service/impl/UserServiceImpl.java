package com.march.main.service.impl;

import com.march.main.entity.User;
import com.march.main.dao.UserMapper;
import com.march.main.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<User> findAllUser() {
        User user=new User();
        return user.selectAll();
    }
}
