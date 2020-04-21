package com.march.main.dao;

import com.march.main.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.march.main.params.GetUserListParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author March
 * @since 2020-03-21
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectListById(GetUserListParam param);
}
