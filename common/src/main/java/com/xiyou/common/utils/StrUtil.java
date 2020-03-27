package com.xiyou.common.utils;

import cn.hutool.core.text.StrSpliter;

import java.util.*;
import java.util.stream.Collectors;

public class StrUtil {

    /*
    将字符串解码成key value形式存入map中
     */
    public static Map<String, String> str2Map(String str, String separator) {
        Map<String, String> map = new HashMap<>();
        if (str == null) {
            return map;
        }
        str = str.replaceAll(" ", "");
        Arrays.stream(str.split(separator))
                .filter(p -> p.contains("="))
                .map(p -> p.split("="))
                .forEach(arr -> map.put(arr[0], arr[1]));
        return map;
    }

    public static List<String> splitToStr(String str, char separator) {
        return StrSpliter.split(str, separator, 0, true, true);
    }

    public static List<Integer> splitToInt(String str, char separator) {
        return splitToStr(str, separator)
                .stream().map(Integer::valueOf)
                .collect(Collectors.toList());
    }

}
