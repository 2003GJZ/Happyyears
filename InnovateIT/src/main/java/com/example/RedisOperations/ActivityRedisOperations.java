package com.example.RedisOperations;

import com.example.redis.JedisPoolExample;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.example.system.entity.Activity;
import com.google.gson.Gson;

public class ActivityRedisOperations {

    private static final JedisPool jedisPool = JedisPoolExample.jedisPool;

    // 新增活动信息
    public static long addActivityToRedis(String activityId, Activity activity) {
        long count;
        String json = serializeToJson(activity);
        try (Jedis jedis = jedisPool.getResource()) {
            count = jedis.hset("Activity", activityId, json);
            jedis.expire("Activity", 604800);
        }
        return count;
    }

    // 根据活动ID获取活动信息
    public static Activity getActivityFromRedis(String activityId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String json = jedis.hget("Activity", activityId);
            if (json != null) {
                return deserializeFromJson(json);
            }
        }
        return null;
    }

    // 删除活动信息
    public static long removeActivityFromRedis(String activityId) {
        long count;
        try (Jedis jedis = jedisPool.getResource()) {
            count = jedis.hdel("Activity", activityId);
        }
        return count;
    }

    // 修改活动信息
    public static long updateActivityInRedis(String activityId, Activity updatedActivity) {
        long count;
        String json = serializeToJson(updatedActivity);
        try (Jedis jedis = jedisPool.getResource()) {
            count = jedis.hset("Activity", activityId, json);
        }
        return count;
    }


    // 将对象序列化为 JSON
    private static String serializeToJson(Activity activity) {
        Gson gson = new Gson();
        return gson.toJson(activity);
    }

    // 将 JSON 反序列化为对象
    private static Activity deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Activity.class);
    }

    public static void main(String[] args) {
        // 创建一个示例活动
        Activity sampleActivity = new Activity();
        sampleActivity.setId(1L);
        sampleActivity.setTitle("示例活动1");
        sampleActivity.setDescription("这是一个示例活动1");
//        Activity sampleActivity1 = new Activity();
//        sampleActivity1.setId(2L);
//        sampleActivity1.setTitle("示例活动2");
//        sampleActivity1.setDescription("这是一个示例活动2");
//
//
//        // 将活动添加到 Redis
        addActivityToRedis(String.valueOf(sampleActivity.getId()), sampleActivity);
//        addActivityToRedis(String.valueOf(sampleActivity1.getId()), sampleActivity1);
//
//        // 从 Redis 获取活动
//        Activity retrievedActivity = getActivityFromRedis(String.valueOf(sampleActivity.getId()));
//        Activity retrievedActivity1 = getActivityFromRedis(String.valueOf(sampleActivity1.getId()));
//
//        System.out.println("获取到的活动信息:");
//        System.out.println(retrievedActivity);
//        System.out.println(retrievedActivity1);
//
//
//        // 在 Redis 中更新活动
//        Activity updatedActivity = new Activity();
//        updatedActivity.setId(1L);
//        updatedActivity.setTitle("示例活动一");
//        updatedActivity.setDescription("这是一个示例活动一");
//        updateActivityInRedis(String.valueOf(updatedActivity.getId()), updatedActivity);
//        Activity updatedActivity1 = new Activity();
//        updatedActivity1.setId(2L);
//        updatedActivity1.setTitle("示例活动二");
//        updatedActivity1.setDescription("这是一个示例活动二");
//        updateActivityInRedis(String.valueOf(updatedActivity1.getId()), updatedActivity1);
//
//        // 从 Redis 获取更新后的活动
//        retrievedActivity = getActivityFromRedis(String.valueOf(updatedActivity.getId()));
//        System.out.println("更新后的活动信息:");
//        System.out.println(retrievedActivity);
//
//        retrievedActivity1 = getActivityFromRedis(String.valueOf(updatedActivity1.getId()));
//        System.out.println(retrievedActivity1);
//
//        // 从 Redis 中删除活动
//        removeActivityFromRedis(String.valueOf(updatedActivity.getId()));
//
//        // 从 Redis 获取已删除的活动
//        retrievedActivity = getActivityFromRedis(String.valueOf(updatedActivity.getId()));
//        System.out.println("已删除的活动信息:");
//        System.out.println(retrievedActivity);
    }
}
