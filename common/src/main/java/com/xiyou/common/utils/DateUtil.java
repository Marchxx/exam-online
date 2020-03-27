package com.xiyou.common.utils;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: tangcan
 * @create: 2019-04-26 19:20
 **/
public class DateUtil {
    /**
     * @Author: tangcan
     * @Description: 格式化时间
     * @Param: [date, format]
     * @date: 2019/6/18
     */
    public static String format(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(localDateTime);
    }

    public static String format(LocalDateTime localDateTime) {
        return localDateTime.toString().replaceAll("T", " ");
    }
}
