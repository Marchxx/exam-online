package com.march.common.enums;

public enum ResultEnum {

    //返回错误代码
    REGISTER_SUCCESS(0,"注册成功"),
    REGISTER_FAILED(-2,"注册失败"),
    LOGIN_SUCCESS(0, "登录成功"),
    LOGIN_FAILED(-1, "用户名或者密码错误"),
    GET_USER_SUCCESS(0,"获取用户信息成功"),
    PARAM_ERROR(1,"参数不正确"),
    USER_NOT_EXIST(10,"用户不存在");

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

}
