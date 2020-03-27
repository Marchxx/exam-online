package com.xiyou.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * @description: 公用方法
 * @author: tangcan
 * @create: 2019-04-25 21:05
 **/
public class SystemUtil {

    public static String getClassesPath() {
        String os = System.getProperty("os.name");
        String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(".")).getPath();
        try {
            path = java.net.URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (os.toLowerCase().startsWith("win")) {
            /*
            去掉前面的 /
             */
            return path.substring(1);
        }
        return path;
    }
}
