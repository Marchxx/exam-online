package com.march.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.march.main.entity.User;
import com.march.main.dao.UserMapper;
import com.march.main.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author March
 * @since 2020-03-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(Integer id) {
        User user = new User();
        user.setUserId(id);
        return user.selectById();
    }

    @Override
    public List<User> findAllUser() {
        User user = new User();
        return user.selectAll();
    }

    @Override
    public User getByAccount(String account) {
        User user = new User();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", account);
        return user.selectOne(queryWrapper);
    }

    @Override
    public User getInfo(Integer id) {
        User user = new User();
        return user.selectById(id);
    }

    @Override
    //返回删除记录的条数
    public int batchDeleteUserByIds(Integer[] ids) {
        List<Integer> idList =new ArrayList<>();
        for (Integer id : ids) {
            idList.add(id);
        }
        return userMapper.deleteBatchIds(idList);
    }

}
