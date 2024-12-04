package com.example.RedisOperations;

import com.example.redis.JedisPoolExample;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.example.system.entity.Team;
import com.google.gson.Gson;

public class TeamRedisOperations {

    private static final JedisPool jedisPool = JedisPoolExample.jedisPool;

    // 新增团队
    public static void addTeamToRedis(String teamId, Team team) {
        String json = serializeToJson(team);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("Team", teamId, json);
            jedis.expire("Team", 604800);
        }
    }

    // 根据团队ID获取团队信息
    public static Team getTeamFromRedis(String teamId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String json = jedis.hget("Team", teamId);
            if (json != null) {
                return deserializeFromJson(json);
            }
        }
        return null;
    }

    // 删除团队
    public static void removeTeamFromRedis(String teamId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel("Team", teamId);
        }
    }

    // 修改团队信息
    public static void updateTeamInRedis(String teamId, Team updatedTeam) {
        String json = serializeToJson(updatedTeam);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("Team", teamId, json);
        }
    }

    // 将对象序列化为 JSON
    private static String serializeToJson(Team team) {
        Gson gson = new Gson();
        return gson.toJson(team);
    }

    // 将 JSON 反序列化为对象
    private static Team deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Team.class);
    }

    public static void main(String[] args) {

    }
}