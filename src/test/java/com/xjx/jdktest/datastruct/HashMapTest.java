package com.xjx.jdktest.datastruct;

import java.util.HashMap;
import java.util.Random;

public class HashMapTest {

    public static String getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return String.valueOf(s);

    }
    static void test(int mapSize) {
        HashMap<Key2, Integer> map = new HashMap<Key2, Integer>(mapSize);
        for (int i = 0; i < mapSize; ++i) {
            map.put(Keys2.of(i), i);
        }
        // 业余草：www.xttblog.com
        long beginTime = System.nanoTime(); //获取纳秒

            map.get(getRandom(0,mapSize));

        long endTime = System.nanoTime();
        System.out.println("从"+mapSize+"个数中获取"+(endTime - beginTime));
    }

    public static void main(String[] args) {
        for (int i = 10; i <= 10000000; i *= 10) {
            test(i);
        }
    }
}
