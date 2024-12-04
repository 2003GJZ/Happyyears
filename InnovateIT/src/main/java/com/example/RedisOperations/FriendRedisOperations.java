package com.example.RedisOperations;

import com.example.redis.JedisPoolExample;
import com.example.system.entity.Chat;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.google.gson.Gson;
import com.example.system.entity.*;

import java.util.Set;

public class FriendRedisOperations {

    private static final JedisPool jedisPool = JedisPoolExample.jedisPool;

    // 新增好友关系
    public static void addFriendToRedis(String userId, String friendId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.sadd("user:" + userId + ":friends", friendId);
        }
    }

    // 删除好友关系
    public static void removeFriendFromRedis(String userId, String friendId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.srem("user:" + userId + ":friends", friendId);
        }
    }

    // 检查是否是好友关系
    public static boolean isFriendInRedis(String userId, String friendId) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.sismember("user:" + userId + ":friends", friendId);
        }
    }

    // 获取用户的所有好友
    public static Set<String> getAllFriendsFromRedis(String userId) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.smembers("user:" + userId + ":friends");
        }
    }

    // 将对象序列化为 JSON
    private static String serializeToJson(Chat chat) {
        Gson gson = new Gson();
        return gson.toJson(chat);
    }

    // 将 JSON 反序列化为对象
    private static Chat deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Chat.class);
    }

    public static void main(String[] args) {
        User user1 = new User(1L);
        User user2 = new User(2L);
        User user3 = new User(3L);
        User user4 = new User(4L);

        addFriendToRedis(String.valueOf(user1.getId()), String.valueOf(user2.getId()));
        addFriendToRedis(String.valueOf(user1.getId()), String.valueOf(user3.getId()));
        addFriendToRedis(String.valueOf(user1.getId()), String.valueOf(user4.getId()));

        System.out.println(isFriendInRedis(String.valueOf(user1.getId()), String.valueOf(user2.getId()))); // true
        System.out.println(getAllFriendsFromRedis(String.valueOf(user1.getId()))); // [2, 3, 4]
        removeFriendFromRedis(String.valueOf(user1.getId()), String.valueOf(user2.getId()));
        System.out.println(isFriendInRedis(String.valueOf(user1.getId()), String.valueOf(user2.getId())));//false
        System.out.println(getAllFriendsFromRedis(String.valueOf(user1.getId()))); // [3, 4]
    }
}