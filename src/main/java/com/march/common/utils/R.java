package com.march.common.utils;




import com.march.common.enums.CodeEnum;
import com.march.common.exception.CustomException;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: attendance
 * @description: 返回参数的封装
 * @author: tangcan
 * @create: 2018-11-26 18:35
 **/
public class R extends HashMap<String, Object> {

    public R() {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        this.put("status", codeEnum.getCode());
        this.put("msg", codeEnum.getMsg());
    }

    public static R error(CustomException e) {
        return error(e.getCode(), e.getMsg());
    }

    public static R error(CodeEnum codeEnum) {
        return error(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static R error(int status, String msg) {
        R r = new R();
        r.put("status", status);
        r.put("msg", msg);
        return r;
    }

    public static R error(CodeEnum codeEnum, String msg) {
        R r = new R();
        r.put("status", codeEnum.getCode());
        r.put("msg", msg);
        return r;
    }

    public static R error(CodeEnum codeEnum, Map<String, Object> map) {
        R r = new R();
        r.put("status", codeEnum.getCode());
        r.put("msg", codeEnum.getMsg());
        if (map != null) {
            r.putAll(map);
        }
        return r;
    }

    public static R success() {
        return new R();
    }

    public static R success(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R success(Map<String, Object> map) {
        R r = new R();
        if (map != null) {
            r.putAll(map);
        }
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
