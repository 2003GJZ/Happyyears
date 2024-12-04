package com.example.module;

import com.google.gson.Gson;
import com.example.system.entity.*;

public class Module_communication {//各个模块之间的通信
    public int myType;//0为请求，1为响应，2为查询无结果 此项不可为空查询，结果直接返回
    public int viscous;//是否关连查询(关于这个相关的全部放入缓存) 默认为0放入, 1为不放入 可为空
    public int operation;//操作类型 此项不可为空
    //0为添加，向redis，1为删除，2为修改，3查询
    public int module;//操作类类型   此项不可为空
    //0  所有实体类
    //1  Activity
    //2  Announcement
    //3  Chat
    //4  Friend
    //5  PersonTeam
    //6  Team
    //7  TeamActivity
    //8  User
    //9  UserLoginLog

    public Activity __activity;//活动
    public Announcement __announcement;//公告
    public Chat __chat;//聊天
    public Friend __friend;//好友
    public PersonTeam __personTeam;//个人团队
    public Team __team;//团队
    public TeamActivity __teamActivity;//团队活动
    public User __user;//用户
    public UserLoginLog __userLoginLog;//用户登录日志

    public String toGson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Module_communication fromGson(String gson) {
        Gson gson1 = new Gson();
        return gson1.fromJson(gson, Module_communication.class);
    }
}