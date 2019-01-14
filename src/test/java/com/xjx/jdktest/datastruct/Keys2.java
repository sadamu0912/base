package com.xjx.jdktest.datastruct;

public class Keys2 {
    // 业余草：www.xttblog.com
    public static final int MAX_KEY = 10_000_000;
    private static final Key2[] KEYS_CACHE = new Key2[MAX_KEY];
    static {
        for (int i = 0; i < MAX_KEY; ++i) {
            KEYS_CACHE[i] = new Key2(i);
        }
    }
    public static Key2 of(int value) {
        return KEYS_CACHE[value];
    }
}
