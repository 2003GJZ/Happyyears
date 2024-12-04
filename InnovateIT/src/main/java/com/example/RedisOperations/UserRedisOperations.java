package com.example.RedisOperations;

import com.example.redis.JedisPoolExample;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.example.system.entity.User;
import com.google.gson.Gson;

public class UserRedisOperations {

    private static final JedisPool jedisPool = JedisPoolExample.jedisPool;

    // 新增或更新用户信息
    public static void addUserToRedis(String userId, User user) {
        String json = serializeToJson(user);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("User",userId, json);
            jedis.expire("User", 604800);
        }
    }

    // 根据用户ID获取用户信息
    public static User getUserFromRedis(String userId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String json = jedis.hget("User",userId);
            if (json != null) {
                return deserializeFromJson(json);
            }
        }
        return null;
    }

    // 删除用户信息
    public static void removeUserFromRedis(String userId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel("User",userId);
        }
    }

    // 修改用户信息
    public static void updateUserInRedis(String userId, User updatedUser) {
        String json = serializeToJson(updatedUser);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("User",userId, json);
        }
    }

    // 将对象序列化为 JSON
    private static String serializeToJson(User user) {
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    // 将 JSON 反序列化为对象
    private static User deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

    public static void main(String[] args) {
    }
}