package com.march.controller;


import com.march.biz.UserBiz;
import com.xiyou.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author March
 * @since 2020-03-21
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理模块")
public class UserController {

    @Autowired
    UserBiz userBiz;

    @ApiOperation(value = "根据ID查找")
    @GetMapping("/{id}")
    public R findUserById(@PathVariable("id") Integer id){
        return userBiz.findUserById(id);
    }

    @ApiOperation(value = "查找所有用户")
    @GetMapping("/list")
    public R findUserList(){
        return userBiz.findUserList();
    }
}

