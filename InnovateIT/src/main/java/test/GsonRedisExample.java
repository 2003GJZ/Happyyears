package test;

import com.example.redis.JedisPoolExample;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import static com.example.redis.JedisPoolExample.jedisPool;

public class GsonRedisExample {
    public static void main(String[] args) {
        // 创建一个示例对象
        User user = new User("John", 30, "New York");

        try {
            Class<?> aClass = Class.forName("com.example.redis.JedisPoolExample");
            aClass.getName();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        JedisPool jedisPool = JedisPoolExample.jedisPool;


        // 将对象序列化为 JSON
        String json = serializeToJson(user);
        System.out.println("Serialized JSON:");
        System.out.println(json);

        // 将 JSON 存储到 Redis 中
        storeJsonInRedis("user:id", json);

        // 从 Redis 中获取 JSON 并反序列化为对象
        String retrievedJson = retrieveJsonFromRedis("user:id");
        User retrievedUser = deserializeFromJson(retrievedJson);
        System.out.println("\nDeserialized User from Redis:");
        System.out.println("Name: " + retrievedUser.getName());
        System.out.println("Age: " + retrievedUser.getAge());
        System.out.println("City: " + retrievedUser.getCity());
    }

    // 将对象序列化为 JSON
    public static String serializeToJson(User user) {
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    // 将 JSON 存储到 Redis 中
    public static void storeJsonInRedis(String key, String json) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, json);
        jedis.close();
    }

    // 从 Redis 中获取 JSON 并反序列化为对象
    public static String retrieveJsonFromRedis(String key) {
        Jedis jedis = new Jedis("localhost", 6379);
        String json = jedis.get(key);
        jedis.close();
        return json;
    }

    // 将 JSON 反序列化为对象
    public static User deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }
}

// 示例对象类
class User {
    private String name;
    private int age;
    private String city;

    public User(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }
}