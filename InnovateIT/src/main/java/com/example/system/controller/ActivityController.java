package com.example.system.controller;

import com.example.InnovateItApplication;
import com.example.system.entity.Activity;
import com.example.system.mapper.ActivityMapper;
import com.example.system.service.IActivityService;
import com.example.system.service.impl.ActivityServiceImpl;
import com.example.system.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
@Controller

public class ActivityController {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(InnovateItApplication.class, args);
        ActivityServiceImpl activityService = ctx.getBean(ActivityServiceImpl.class);
//        ActivityServiceImpl activityService = new ActivityServiceImpl();
//        Activity activity = activityService.selectActivityById(1L);
//        System.out.println(activity);
//        activityService.deleteActivityById(10L);
//        Activity activity = new Activity();

//        activity.setId(10L);
//        activity.setTitle("活动4");
//        activity.setUserid(1774782774679592972L);
//        activityService.insertActivity(activity);

        for (Activity activity : activityService.selectAllActivity()) {
            System.out.println(activity);
        }


//

//        int i = activityService.deleteActivityById(10L);
//        System.out.println(i);

    }
}
