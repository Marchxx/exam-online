package com.march.main.controller;


import com.march.main.biz.UserBiz;
import com.march.common.utils.R;
import com.march.main.entity.User;
import com.march.main.params.GetUserListParam;
import com.march.main.params.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
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

    @ApiOperation(value = "根据id，获取用户信息")
    @GetMapping("/info/{id}")
    public R findUserById(@PathVariable("id") Integer id) {
        return userBiz.findUserById(id);
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取当前登录用户信息")
    public R getInfo(HttpServletRequest request) {
        //取出经token验证后取出的id
        String id = request.getAttribute("id").toString();
        return userBiz.getInfo(Integer.parseInt(id));
    }

    @ApiOperation(value = "查找所有用户")
    @GetMapping("/list/all")
    public R findUserList() {
        return userBiz.findUserList();
    }

    @ApiOperation(value = "根据rId/uname/name,模糊查询用户列表")
    @PostMapping("/list")
    public R findUserListById(@RequestBody GetUserListParam param) {
        return userBiz.findUserListById(param);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户信息,用户ID必填")
    public R updateInfoById(@RequestBody User user) {
        return userBiz.updateInfoById(user);
    }

    @GetMapping("/batchDelete")
    @ApiOperation(value = "人员批量删除,提交id数组")
    public R batchDeleteUserByIds(Integer ids[]) {
        return userBiz.batchDeleteUserByIds(ids);
    }

    //以下两个url无需拦截
    @PostMapping("/login")
    @ApiOperation(value = "根据用户名登录，登录成功返回token")
    public R login(@RequestBody LoginParam loginParam) {
        return userBiz.login(loginParam);
    }

    @PostMapping("/register")
    @ApiOperation(value = "(新增/注册)用户")
    public R register(@RequestBody User user) {
        return userBiz.register(user);
    }

}

