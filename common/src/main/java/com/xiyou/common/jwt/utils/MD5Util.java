package com.xiyou.common.jwt.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * MD5加密
 */
public class MD5Util {

    private static final String SALT = "@swust_acm_306";

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String username, String password) {
        return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex();
    }

    public static String encrypt(String username, String password, String salt) {
        return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(username + salt),
                HASH_ITERATIONS).toHex();
    }

    public static boolean verifyPwd(String username, String password, String encryptPwd) {
        if (StringUtils.isBlank(password) || StringUtils.isBlank(encryptPwd)) {
            return false;
        }
        return encryptPwd.equals(encrypt(username, password));
    }

    public static boolean verifyPwd(String username, String password, String encryptPwd, String salt) {
        if (StringUtils.isBlank(password) || StringUtils.isBlank(encryptPwd)) {
            return true;
        }
        return !encryptPwd.equals(encrypt(username, password, salt));
    }

    /**
     * @Author: tangcan
     * @Description: 计算密码加密值
     * @Param: [args]
     * @date: 2019/3/28
     */
    public static void main(String[] args) {
        String pwd = encrypt("erefejsfkeeerte", "458erf4efer555r46555ttytyttu415u5t5y*44er4e", "GJhSmzVRFDwNWOQSRpIY");
        System.out.println(pwd.length());
        System.out.println(pwd);
    }
}
