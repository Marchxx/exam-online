package com.march.main.controller;


import com.march.main.biz.UserBiz;
import com.march.common.utils.R;
import com.march.main.params.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/login")
    @ApiOperation(value = "根据用户名登录，登录成功返回token")
    public R login(@RequestBody @Validated LoginParam loginParam){
        return userBiz.login(loginParam);
    }

    @ApiOperation(value = "测试接口")
    @GetMapping("/test")
    public String test(HttpServletRequest request){
        String userName=request.getAttribute("username").toString();
        String userId=request.getAttribute("id").toString();
        System.out.println(userName+" "+userId);
        return userName+userId;
    }

}

