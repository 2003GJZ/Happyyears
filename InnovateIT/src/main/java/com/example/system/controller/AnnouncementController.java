package com.example.system.controller;

import com.example.InnovateItApplication;
import com.example.system.entity.Announcement;
import com.example.system.service.impl.ActivityServiceImpl;
import com.example.system.service.impl.AnnouncementServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
@Controller

public class AnnouncementController {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(InnovateItApplication.class, args);
        AnnouncementServiceImpl announcementService = ctx.getBean(AnnouncementServiceImpl.class);
//        Announcement announcement = new Announcement();
//        announcement.setId(1L);
//        announcement.setTitle("测试");
//        announcementService.insertAnnouncement(announcement);
//
//        for (Announcement a:announcementService.getAllAnnouncement()) {
//            System.out.println(a);
//        }
//        announcementService.deleteAnnouncement(1L);
    }
}
