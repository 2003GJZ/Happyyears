package com.example.RedisOperations;

import com.example.redis.JedisPoolExample;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.example.system.entity.TeamActivity;
import com.google.gson.Gson;


public class TeamActivityRedisOperations {

    private static final JedisPool jedisPool = JedisPoolExample.jedisPool;

    // 新增团队活动
    public static void addActivityToTeamInRedis(String teamId, String activityId, TeamActivity activity) {
        String json = serializeToJson(activity);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("TeamActivity" + teamId, activityId, json);
        }
    }

    // 根据团队ID和活动ID获取活动信息
    public static TeamActivity getActivityFromTeamInRedis(String teamId, String activityId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String json = jedis.hget("TeamActivity" + teamId, activityId);
            if (json != null) {
                return deserializeFromJson(json);
            }
        }
        return null;
    }

    // 删除团队活动
    public static void removeActivityFromTeamInRedis(String teamId, String activityId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel("TeamActivity" + teamId, activityId);
        }
    }

    // 修改团队活动信息
    public static void updateActivityInTeamInRedis(String teamId, String activityId, TeamActivity updatedActivity) {
        String json = serializeToJson(updatedActivity);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("TeamActivity" + teamId, activityId, json);
        }
    }

    // 将对象序列化为 JSON
    private static String serializeToJson(TeamActivity activity) {
        Gson gson = new Gson();
        return gson.toJson(activity);
    }

    // 将 JSON 反序列化为对象
    private static TeamActivity deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, TeamActivity.class);
    }

    public static void main(String[] args) {

    }
}
