package com.example.redis;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedisProcessUtils {
    public static void killRedis() {
        String redisProcessName = "redis-server"; // Redis 进程的名称
        String redisPid = getRedisPid(redisProcessName);

        if (!redisPid.isEmpty()) {
            killRedisProcess(redisPid);
            System.out.println("已杀死已启动的Redis------->");
        } else {
            System.out.println("未启动Redis------->" );
        }

    }

    public static String getRedisPid(String processName) {
        String command = "pgrep " + processName;
        StringBuilder pidBuilder = new StringBuilder();

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String pid;
            while ((pid = reader.readLine()) != null) {
                pidBuilder.append(pid).append(" ");
            }

            reader.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return pidBuilder.toString().trim();
    }

    public static void killRedisProcess(String pid) {
        String command = "kill " + pid;
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}