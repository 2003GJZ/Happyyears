package com.example.RedisOperations;

import com.example.redis.JedisPoolExample;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.example.system.entity.Announcement;
import com.google.gson.Gson;

public class AnnouncementRedisOperations {

    private static final JedisPool jedisPool = JedisPoolExample.jedisPool;

    // 新增或更新公告信息
    public static void addAnnouncementToRedis(String announcementId, Announcement announcement) {
        String json = serializeToJson(announcement);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("Announcement", announcementId, json);
            jedis.expire("Announcement", 604800);
        }
    }

    // 根据公告ID获取公告信息
    public static Announcement getAnnouncementFromRedis(String announcementId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String json = jedis.hget("Announcement", announcementId);
            if (json != null) {
                return deserializeFromJson(json);
            }
        }
        return null;
    }

    // 删除公告信息
    public static void removeAnnouncementFromRedis(String announcementId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel("Announcement", announcementId);
        }
    }

    // 修改公告信息
    public static void updateAnnouncementInRedis(String announcementId, Announcement updatedAnnouncement) {
        String json = serializeToJson(updatedAnnouncement);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("Announcement", announcementId, json);
        }
    }

    // 将对象序列化为 JSON
    private static String serializeToJson(Announcement announcement) {
        Gson gson = new Gson();
        return gson.toJson(announcement);
    }

    // 将 JSON 反序列化为对象
    private static Announcement deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Announcement.class);
    }

    public static void main(String[] args) {
        // Create a new announcement
        Announcement newAnnouncement = new Announcement();
        newAnnouncement.setId(1L);
        newAnnouncement.setTitle("New Announcement");
        // Add the announcement to Redis
        addAnnouncementToRedis(String.valueOf(newAnnouncement.getId()), newAnnouncement);

        // Retrieve the announcement from Redis
        Announcement retrievedAnnouncement = getAnnouncementFromRedis(String.valueOf(newAnnouncement.getId()));
        System.out.println("Retrieved Announcement: " + retrievedAnnouncement);

        // Update the announcement
        Announcement updatedAnnouncement = new Announcement();
        updatedAnnouncement.setId(1L);
        updatedAnnouncement.setTitle("Updated Announcement");
        updateAnnouncementInRedis("1", updatedAnnouncement);

        // Retrieve the updated announcement from Redis
        retrievedAnnouncement = getAnnouncementFromRedis("1");
        System.out.println("Updated Announcement: " + retrievedAnnouncement);

        // Remove the announcement from Redis
        removeAnnouncementFromRedis("1");

        // Try to retrieve the announcement again (should be null)
        retrievedAnnouncement = getAnnouncementFromRedis("1");
        System.out.println("After removal, Retrieved Announcement: " + retrievedAnnouncement);
    }
}
