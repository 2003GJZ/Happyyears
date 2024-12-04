package com.example.system.controller;

import com.example.InnovateItApplication;
import com.example.system.entity.Friend;
import com.example.system.service.impl.FriendServiceImpl;
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
public class FriendController {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(InnovateItApplication.class, args);
        // 获取bean
        FriendServiceImpl friendService = ctx.getBean(FriendServiceImpl.class);
        Friend friend = new Friend();
//        friend.setUserId(1L);
//        friend.setFriendId(2L);
//        friend.setStatus(String.valueOf(0));
//
//        Friend friend1 = new Friend();
//        friend1.setUserId(1L);
//        friend1.setFriendId(3L);
//        friend1.setStatus(String.valueOf(0));
//
//        Friend friend2 = new Friend();
//        friend2.setUserId(1L);
//        friend2.setFriendId(4L);
//        friend2.setStatus(String.valueOf(0));
//
//        Friend friend3 = new Friend();
//        friend3.setUserId(2L);
//        friend3.setFriendId(3L);
//        friend3.setStatus(String.valueOf(0));
//
//        // 创建朋友关系
//        friendService.createFriend(friend);
//        friendService.createFriend(friend1);
//        friendService.createFriend(friend2);
//        friendService.createFriend(friend3);

//        // 更新朋友关系状态
//        friendService.updateFriendStatus(1L, String.valueOf(1));
//
//        // 查询用户的所有朋友
        List<Friend> friends = friendService.getAllFriendsByUserId(1L);
        for (Friend friend1 : friends) {
            System.out.println(friend1);
        }
//
//        // 查询特定朋友
//        Friend specificFriend = friendService.getFriendById(1L);
//
//        // 删除朋友关系
//        friendService.deleteFriend(1L);
    }
}
