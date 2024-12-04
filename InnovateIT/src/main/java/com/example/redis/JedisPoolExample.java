package com.example.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolExample {
    // 创建连接池配置对象
    private static JedisPoolConfig poolConfig;
    public static JedisPool jedisPool;

    static {
//        RedisProcessUtils.killRedis();
        RedisStart.redisStart();
        // 创建连接池配置对象
        poolConfig = new JedisPoolConfig();
        // 设置连接池大小
        poolConfig.setMaxTotal(20);//最大连接数
        poolConfig.setMaxIdle(10);//最大空闲链接数
        poolConfig.setMinIdle(2);//最小空闲链接数
        // 创建Jedis连接池对象
        jedisPool = new JedisPool(poolConfig, "localhost", 6379, 10000, null);
//        System.out.println("Redis连接池加载完成--------->>>>");

    }
}