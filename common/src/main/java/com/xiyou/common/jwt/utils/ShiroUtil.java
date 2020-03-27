package com.xiyou.common.jwt.utils;

import com.xiyou.common.jwt.JWTToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * @program: attendance
 * @description: Shiro的工具
 * @author: tangcan
 * @create: 2018-12-03 18:11
 **/
public class ShiroUtil {
    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static JWTToken getJWTToken() {
        return (JWTToken) getSubjct().getPrincipal();
    }

    public static Integer getUserId() {
        JWTToken jwtToken = getJWTToken();
        if (jwtToken == null) {
            return 1;
        }
        return jwtToken.getUserId();
    }

    public static String getAccount() {
        JWTToken jwtToken = getJWTToken();
        if (jwtToken == null) {
            return null;
        }
        return jwtToken.getAccount();
    }

    public static String getName() {
        JWTToken jwtToken = getJWTToken();
        if (jwtToken == null) {
            return null;
        }
        return jwtToken.getName();
    }

    public static void logout() {
        getSubjct().logout();
    }
}
