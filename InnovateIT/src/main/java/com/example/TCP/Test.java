package com.example.TCP;

import com.example.InnovateItApplication;
import com.example.module.Module_communication;
import com.example.system.entity.Activity;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(InnovateItApplication.class, args);

        Module_communication moduleCommunication = new Module_communication();
        moduleCommunication.operation = 3;
        moduleCommunication.module = 1;
        Activity activity = new Activity();
        activity.setId(1L);
        moduleCommunication.__activity = activity;
        String gson = moduleCommunication.toGson();
        CacheThread cacheThread = new CacheThread(gson,ctx);
        Thread thread = new Thread(cacheThread);
        thread.start();
    }
}
