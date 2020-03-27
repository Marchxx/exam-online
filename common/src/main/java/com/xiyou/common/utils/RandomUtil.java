package com.xiyou.common.utils;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    private static Random random = new Random();

    public static String createFileName() {
        // 具体时间 + 随机字符串命名
        return LocalDateTime.now().toString().replaceAll(":", "").replace(".", "")
                + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }

    public static long longs(long min, long max) {
        return random.longs(min, (max + 1)).limit(1).findFirst().getAsLong();
    }

    public static int ints(int min, int max) {
        return random.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }
}
