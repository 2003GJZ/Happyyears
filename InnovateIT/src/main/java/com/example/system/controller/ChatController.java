package com.example.system.controller;

import com.example.InnovateItApplication;
import com.example.system.entity.Chat;
import com.example.system.service.impl.ChatServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
@Controller
public class ChatController {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(InnovateItApplication.class, args);
        ChatServiceImpl chatService = ctx.getBean(ChatServiceImpl.class);

        Long l = chatService.selectChat(1774782774679592973L, 1774782774679592974L);
        System.out.println(l);
//        List<Chat> chats = chatService.selectAll();
//
//        for (Chat chat : chats) {
//            System.out.println(chat);
//        }
    }
}