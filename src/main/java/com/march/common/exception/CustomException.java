package com.march.common.exception;


import com.march.common.enums.CodeEnum;

/**
 * @program: attendance
 * @description: 自定义异常
 * @author: tangcan
 * @create: 2018-12-03 19:03
 **/
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code;

    public CustomException(CodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public CustomException(CodeEnum codeEnum, String msg) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
        this.msg = msg;
    }

    public CustomException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
