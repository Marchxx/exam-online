package com.march.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

    public final static String key = "march_exam";

    /**
     * MD5加密
     *
     * @param text 明文
     * @param key  秘钥
     * @return 密文
     * @throws Exception
     */
    public static String md5Encode(String text, String key) {
        String encodeStr = DigestUtils.md5Hex(text + key);
        return encodeStr;
    }

    /**
     * MD5验证
     *
     * @param text   明文
     * @param key    秘钥
     * @param md5Str 密文
     * @return
     */
    public static Boolean verify(String text, String key, String md5Str) {
        String encodeStr = DigestUtils.md5Hex(text + key);
        //System.out.println(encodeStr+"\n"+md5Str);
        //忽略大小写
        if (encodeStr.equalsIgnoreCase(md5Str)) {
            return true;
        } else
            return false;
    }
}
