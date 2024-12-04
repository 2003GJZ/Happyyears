package com.example.redis;

import java.io.IOException;

public class RedisStart {
    public static void redisStart(){
        try {
            Runtime.getRuntime().exec("redis-server");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
//        System.out.println("Redis已启动------>");
    }
}
