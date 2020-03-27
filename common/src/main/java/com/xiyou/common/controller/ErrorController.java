package com.xiyou.common.controller;

import com.xiyou.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: multi-module
 * @description: 错误跳转
 * @author: tangcan
 * @create: 2019-06-18 12:03
 **/
@RestController
@Api(tags = "错误跳转", hidden = true)
@RequestMapping("/error")
public class ErrorController {
    @ResponseBody
    @RequestMapping("/authentication/fail")
    @ApiOperation(value = "登陆校验失败", hidden = true)
    public R noLoginError(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute("code");
        String msg = (String) request.getAttribute("msg");
        return R.error(code, msg);
    }
}
