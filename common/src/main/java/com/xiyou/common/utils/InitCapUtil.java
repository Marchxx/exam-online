package com.xiyou.common.utils;

import java.util.Collection;

public class InitCapUtil {

    private static final int DEFAULT_CAP = 16;

    public static int mapCap(int size) {
        int cap = (int) (size / 0.75 + 1);
        int x = (int) (Math.log(cap) / Math.log(2));
        return 1 << x;
    }

    public static int mapCap(Collection<?> collection) {
        if (collection == null) {
            return DEFAULT_CAP;
        }
        return mapCap(collection.size());
    }

    public static int setCap(int size) {
        int cap = (int) ((double) size / 0.75);
        int x = (int) (Math.log(cap) / Math.log(2));
        return 1 << x;
    }

    public static int setCap(Collection<?> collection) {
        if (collection == null) {
            return DEFAULT_CAP;
        }
        return setCap(collection.size());
    }

    public static int listCap(int size) {
        int cap = (int) ((double) size * 3.0 / 2.0);
        int x = (int) (Math.log(cap) / Math.log(2));
        return 1 << x;
    }

    public static int listCap(Collection<?> collection) {
        if (collection == null) {
            return DEFAULT_CAP;
        }
        return listCap(collection.size());
    }
}
