package test;


import redis.clients.jedis.Jedis;

public class TestJedis {
    public static void main(String[] args) {
        // 1、 new Jedis 对象即可
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();//清空当前库的所有数据
        jedis.set("name", "dingyongjun");
        jedis.set("age", "23");
        jedis.set("high", "173");
        System.out.println("name:" + jedis.get("name") + "\nage:" + jedis.get("age") + "\nhigh" + jedis.get("high"));
    }
}
