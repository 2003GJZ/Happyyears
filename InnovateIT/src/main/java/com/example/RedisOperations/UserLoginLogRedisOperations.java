package com.example.RedisOperations;

import com.example.redis.JedisPoolExample;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.example.system.entity.UserLoginLog;
import com.google.gson.Gson;

public class UserLoginLogRedisOperations {

    private static final JedisPool jedisPool = JedisPoolExample.jedisPool;

    // 新增用户登录日志
    public static void addUserLoginLogToRedis(String logId, UserLoginLog loginLog) {
        String json = serializeToJson(loginLog);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("UserLoginLog", logId, json);
            jedis.expire("UserLoginLog", 604800);
        }
    }

    // 根据用户ID和登录ID获取登录日志信息
    public static UserLoginLog getUserLoginLogFromRedis(String userId, String logId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String json = jedis.hget("UserLoginLog", logId);
            if (json != null) {
                return deserializeFromJson(json);
            }
        }
        return null;
    }

    // 删除用户登录日志
    public static void removeUserLoginLogFromRedis(String logId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel("UserLoginLog", logId);
        }
    }

    // 修改用户登录日志信息
    public static void updateUserLoginLogInRedis(String logId, UserLoginLog updatedLoginLog) {
        String json = serializeToJson(updatedLoginLog);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("UserLoginLog", logId, json);
        }
    }

    // 将对象序列化为 JSON
    private static String serializeToJson(UserLoginLog loginLog) {
        Gson gson = new Gson();
        return gson.toJson(loginLog);
    }

    // 将 JSON 反序列化为对象
    private static UserLoginLog deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, UserLoginLog.class);
    }

    public static void main(String[] args) {

    }
}