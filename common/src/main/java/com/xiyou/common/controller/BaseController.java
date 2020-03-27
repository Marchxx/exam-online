package com.xiyou.common.controller;

import com.xiyou.common.jwt.JWTToken;
import com.xiyou.common.jwt.service.JWTTokenService;
import com.xiyou.common.jwt.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: multi-module
 * @description:
 * @author: tangcan
 * @create: 2019-06-18 22:01
 **/
@RestController
public class BaseController {
    @Autowired
    private JWTTokenService jwtTokenService;

    public Integer getUserId() {
        return ShiroUtil.getUserId();
    }

    public String getAccount() {
        return ShiroUtil.getAccount();
    }

    public String getName() {
        return ShiroUtil.getName();
    }

    public void logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return;
        }
        JWTToken jwtToken = jwtTokenService.get(token);
        if (jwtToken == null) {
            return;
        }
        jwtTokenService.remove(jwtToken);
        ShiroUtil.logout();
    }
}
