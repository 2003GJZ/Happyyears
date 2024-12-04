package com.example.RedisOperations;

import com.example.redis.JedisPoolExample;
import com.example.system.entity.Team;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.example.system.entity.User;
import com.google.gson.Gson;

public class PersonTeamRedisOperations {

    private static final JedisPool jedisPool = JedisPoolExample.jedisPool;

    // 新增团队成员
    public static void addPersonToTeamInRedis(String teamId, String personId, User person) {
        String json = serializeToJson(person);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("Team" + teamId, personId, json);
        }
    }

    // 根据团队ID和成员ID获取成员信息
    public static User getPersonFromTeamInRedis(String teamId, String personId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String json = jedis.hget("Team" + teamId, personId);
            if (json != null) {
                return deserializeFromJson(json);
            }
        }
        return null;
    }

    // 删除团队成员
    public static void removePersonFromTeamInRedis(String teamId, String personId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel("Team" + teamId, personId);
        }
    }

    // 修改团队成员信息
    public static void updatePersonInTeamInRedis(String teamId, String personId, User updatedPerson) {
        String json = serializeToJson(updatedPerson);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("Team" + teamId, personId, json);
        }
    }

    // 将对象序列化为 JSON
    private static String serializeToJson(User person) {
        Gson gson = new Gson();
        return gson.toJson(person);
    }

    // 将 JSON 反序列化为对象
    private static User deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

    public static void main(String[] args) {
        // 测试向 Redis 中添加团队成员
        User user = new User();
        user.setId(1L);
        user.setUserName("John");
        User user1 = new User();
        user1.setId(2L);
        user1.setUserName("Jane");
        Team team = new Team();
        team.setId(1L);
        team.setName("Team 1");
//        addPersonToTeamInRedis(String.valueOf(team.getId()), String.valueOf(user.getId()), user);
//        addPersonToTeamInRedis(String.valueOf(team.getId()), String.valueOf(user1.getId()), user1);
//
//        // 测试从 Redis 中获取团队成员
//        User retrievedUser1 = getPersonFromTeamInRedis(String.valueOf(team.getId()), String.valueOf(user.getId()));
//        User retrievedUser2 = getPersonFromTeamInRedis(String.valueOf(team.getId()), String.valueOf(user1.getId()));
//        System.out.println("获取到的用户信息：" + retrievedUser1);
//        System.out.println("获取到的用户信息：" + retrievedUser2);

//        // 测试更新 Redis 中的团队成员信息
//        User updatedUser1 = new User();
//        updatedUser1.setId(1L);
//        updatedUser1.setUserName("update");
//        updatePersonInTeamInRedis(String.valueOf(1), "1", updatedUser1);
//        User retrievedUser1 = getPersonFromTeamInRedis(String.valueOf(1), "1");
//        System.out.println("更新后的用户信息：" + retrievedUser1);
//
//        // 测试从 Redis 中移除团队成员
        removePersonFromTeamInRedis(String.valueOf(1), "1");
        User retrievedUser1 = getPersonFromTeamInRedis(String.valueOf(1), "1");
        System.out.println("移除后的用户信息：" + retrievedUser1); // 应为 null
    }
}