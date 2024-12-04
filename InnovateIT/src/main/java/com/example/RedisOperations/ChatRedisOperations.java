package com.example.RedisOperations;

import com.example.redis.JedisPoolExample;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.example.system.entity.Chat;
import com.google.gson.Gson;

public class ChatRedisOperations {

    private static final JedisPool jedisPool = JedisPoolExample.jedisPool;

    // 新增或更新聊天信息
    public static void addChatToRedis(String chatId, Chat chat) {
        String json = serializeToJson(chat);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("Chat", chatId, json);
            jedis.expire("Chat", 604800);
        }
    }

    // 根据聊天ID获取聊天信息
    public static Chat getChatFromRedis(String chatId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String json = jedis.hget("Chat", chatId);
            if (json != null) {
                return deserializeFromJson(json);
            }
        }
        return null;
    }

    // 删除聊天信息
    public static void removeChatFromRedis(String chatId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel("Chat", chatId);
        }
    }

    // 修改聊天信息
    public static void updateChatInRedis(String chatId, Chat updatedChat) {
        String json = serializeToJson(updatedChat);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset("Chat", chatId, json);
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
        // Create a sample Chat object
        Chat chat = new Chat();
        chat.setId(1L);
        chat.setaId(1L);
        chat.setbId(2L);

        // Add the chat to Redis
        ChatRedisOperations.addChatToRedis(String.valueOf(chat.getId()), chat);

        // Retrieve the chat from Redis
        Chat retrievedChat = ChatRedisOperations.getChatFromRedis(String.valueOf(chat.getId()));
        System.out.println("Retrieved chat: " + retrievedChat);

        // Update the chat in Redis
        Chat chat1 = new Chat();
        chat1.setId(1L);
        chat1.setaId(1L);
        chat1.setbId(3L);
        ChatRedisOperations.updateChatInRedis(String.valueOf(chat1.getId()), retrievedChat);


        // Retrieve the updated chat from Redis
        Chat updatedChat = ChatRedisOperations.getChatFromRedis(String.valueOf(chat1.getId()));
        System.out.println("Updated chat: " + updatedChat);

        // Remove the chat from Redis
        ChatRedisOperations.removeChatFromRedis(String.valueOf(chat1.getId()));

        // Check if chat was successfully removed
        Chat removedChat = ChatRedisOperations.getChatFromRedis(String.valueOf(chat1.getId()));
        System.out.println("Removed chat: " + removedChat);
    }
}
