package com.example.TCP;


import com.example.system.entity.*;
import com.example.module.Module_communication;
import com.example.RedisOperations.*;
import com.example.system.service.impl.*;
import org.springframework.context.ApplicationContext;

import java.util.Set;


public class CacheThread implements Runnable {
    public Module_communication module_communication;
    public ApplicationContext ctx;
    public static String gson;

    public CacheThread(String gson, ApplicationContext ctx) {
        this.module_communication = Module_communication.fromGson(gson);
        this.ctx = ctx;
    }

    public CacheThread() {

    }

    public void run() {
        switch (module_communication.module) {
            case 0://0  所有实体类
                switch (module_communication.operation) {

                    case 0:
                        break;

                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        break;
                    default:

                        break;
                }
                break;

            case 1: //1  Activity
                Activity activity = module_communication.__activity;
                ActivityServiceImpl activityService = ctx.getBean(ActivityServiceImpl.class);
                switch (module_communication.operation) {
                    case 0:
                        activityService.insertActivity(activity);//先插入数据库
                        ActivityRedisOperations.addActivityToRedis(String.valueOf(activity.getId()), activity);//再插入redis
                        break;
                    case 1:
                        activityService.deleteActivityById(activity.getId());//先删除数据库
                        ActivityRedisOperations.removeActivityFromRedis(String.valueOf(activity.getId()));//再删除redis
                        break;
                    case 2://延迟双删除
                        activityService.updateActivityById(activity);//先更新数据库
                        ActivityRedisOperations.removeActivityFromRedis(String.valueOf(activity.getId()));//先删除redis中的数据
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }//延迟删除
                        ActivityRedisOperations.removeActivityFromRedis(String.valueOf(activity.getId()));//再删除redis中的数据
                        break;
                    case 3:
                        Activity activityFromRedis = ActivityRedisOperations.getActivityFromRedis(String.valueOf(activity.getId()));//先从redis中获取数据
                        Module_communication moduleCommunication = new Module_communication();
                        if (activityFromRedis == null) {
                            Activity activity1 = activityService.selectActivityById(activity.getId());//如果redis中没有，则从数据库中获取
                            if (activity1 != null) {//mysql中存在数据
                                moduleCommunication.__activity = activity1;
                                ActivityRedisOperations.addActivityToRedis(String.valueOf(activity.getId()), activity1);//再插入redis
                            } else {
                                System.out.println("mysql not find activity");//mysql也没有
                            }
                        } else {
                            moduleCommunication.__activity = activityFromRedis;//操作类对象
                        }
                        moduleCommunication.module = 1;//操作类型
                        String gson = moduleCommunication.toGson();//将module_communication对象转换为gson
                        break;
                    default:

                        break;
                }
                break;

            case 2://2 Announcement
                Announcement announcement = module_communication.__announcement;
                AnnouncementServiceImpl announcementService = ctx.getBean(AnnouncementServiceImpl.class);
                switch (module_communication.operation) {
                    case 0:
                        announcementService.insertAnnouncement(announcement);//先插入数据库
                        AnnouncementRedisOperations.addAnnouncementToRedis(String.valueOf(announcement.getId()), announcement);//再插入redis
                        break;
                    case 1:
                        announcementService.deleteAnnouncement(announcement.getId());//先删除数据库
                        AnnouncementRedisOperations.removeAnnouncementFromRedis(String.valueOf(announcement.getId()));//再删除redis
                        break;
                    case 2:
                        announcementService.updateAnnouncement(announcement);//先更新数据库
                        AnnouncementRedisOperations.removeAnnouncementFromRedis(String.valueOf(announcement.getId()));//删除redis中的数据
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }//延迟删除
                        AnnouncementRedisOperations.removeAnnouncementFromRedis(String.valueOf(announcement.getId()));//再删除redis中的数据
                        break;
                    case 3:
                        Announcement announcementFromRedis = AnnouncementRedisOperations.getAnnouncementFromRedis(String.valueOf(announcement.getId()));
                        Module_communication moduleCommunication = new Module_communication();
                        if (announcementFromRedis == null) {
                            Announcement announcement1 = announcementService.getAnnouncementById(announcement.getId());
                            if (announcement1 != null) {
                                moduleCommunication.__announcement = announcement1;
                                AnnouncementRedisOperations.addAnnouncementToRedis(String.valueOf(announcement.getId()), announcement1);
                            } else {
                                System.out.println("mysql not find announcement");//mysql也没有
                            }
                        } else {
                            moduleCommunication.__announcement = announcementFromRedis;//操作类对象
                        }
                        moduleCommunication.module = 2;//操作类型
                        String gson = moduleCommunication.toGson();//将module_communication对象转换为gson
                        break;
                    default:
                        break;
                }
                break;

            case 3://3  Chat
                Chat chat = module_communication.__chat;
                ChatServiceImpl chatService = ctx.getBean(ChatServiceImpl.class);
                switch (module_communication.operation) {
                    case 0:
                        chatService.addChat(chat);//先插入数据库
                        ChatRedisOperations.addChatToRedis(String.valueOf(chat.getId()), chat);
                        break;
                    case 1:
                        chatService.deleteChat(chat.getId());//先删除数据库
                        ChatRedisOperations.removeChatFromRedis(String.valueOf(chat.getId()));
                        break;
                    case 2:
                        chatService.updateChat(chat);//先更新数据库
                        ChatRedisOperations.removeChatFromRedis(String.valueOf(chat.getId()));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        ChatRedisOperations.removeChatFromRedis(String.valueOf(chat.getId()));
                        break;
                    case 3:
                        Chat chatFromRedis = ChatRedisOperations.getChatFromRedis(String.valueOf(chat.getId()));
                        Module_communication moduleCommunication = new Module_communication();
                        if (chatFromRedis == null) {
                            Chat chat1 = chatService.selectChatById(chat.getId());
                            if (chat1 != null) {
                                moduleCommunication.__chat = chat1;
                                ChatRedisOperations.addChatToRedis(String.valueOf(chat.getId()), chat1);
                            } else {
                                System.out.println("mysql not find chat");//mysql也没有
                            }
                        } else {
                            moduleCommunication.__chat = chatFromRedis;//操作类对象
                        }
                        moduleCommunication.module = 3;//操作类型
                        String gson = moduleCommunication.toGson();//将module_communication对象转换为gson
                        break;
                    default:
                        break;
                }
                break;

            case 4://4  Friend
                Friend friend = module_communication.__friend;
                User user = module_communication.__user;
                FriendServiceImpl friendService = ctx.getBean(FriendServiceImpl.class);
                switch (module_communication.operation) {
                    case 0:
                        friendService.createFriend(friend);//先插入数据库
                        FriendRedisOperations.addFriendToRedis(String.valueOf(user.getId()), String.valueOf(friend.getId()));//再插入redis
                        break;

                    case 1:
                        friendService.deleteFriend(friend.getId());//先删除数据库
                        FriendRedisOperations.removeFriendFromRedis(String.valueOf(user.getId()), String.valueOf(friend.getId()));//再删除redis
                        break;

                    case 2:
                        friendService.updateFriendStatus(friend.getId(), friend.getStatus());//先更新数据库
                        FriendRedisOperations.removeFriendFromRedis(String.valueOf(user.getId()), String.valueOf(friend.getId()));//再删除redis
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        FriendRedisOperations.removeFriendFromRedis(String.valueOf(user.getId()), String.valueOf(friend.getId()));//再删除redis
                        break;

                    case 3:
                        boolean friendInRedis = FriendRedisOperations.isFriendInRedis(String.valueOf(user.getId()), String.valueOf(friend.getId()));
                        break;
                    default:

                        break;
                }
                break;

            case 5://5  PersonTeam
                PersonTeam personTeam = module_communication.__personTeam;
                User user1 = module_communication.__user;
                switch (module_communication.operation) {
                    case 0:
                        PersonTeamRedisOperations.addPersonToTeamInRedis(String.valueOf(personTeam.getTeamId()), String.valueOf(personTeam.getPersonId()), user1);
                        break;
                    case 1:
                        PersonTeamRedisOperations.removePersonFromTeamInRedis(String.valueOf(personTeam.getTeamId()), String.valueOf(personTeam.getPersonId()));
                        break;
                    case 2:
                        PersonTeamRedisOperations.updatePersonInTeamInRedis(String.valueOf(personTeam.getTeamId()), String.valueOf(personTeam.getPersonId()), user1);
                        break;
                    case 3:
                        User personFromTeamInRedis = PersonTeamRedisOperations.getPersonFromTeamInRedis(String.valueOf(personTeam.getTeamId()), String.valueOf(personTeam.getPersonId()));
                        break;
                    default:

                        break;
                }
                break;

            case 6://6  Team
                Team team = module_communication.__team;
                TeamServiceImpl teamService = ctx.getBean(TeamServiceImpl.class);
                switch (module_communication.operation) {
                    case 0:
                        teamService.insertTeam(team);//先插入数据库
                        TeamRedisOperations.addTeamToRedis(String.valueOf(team.getId()), team);
                        break;
                    case 1:
                        teamService.deleteTeamById(team.getId());//先删除数据库
                        TeamRedisOperations.removeTeamFromRedis(String.valueOf(team.getId()));
                        break;
                    case 2:
                        teamService.updateTeamById(team);
                        TeamRedisOperations.removeTeamFromRedis(String.valueOf(team.getId()));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        TeamRedisOperations.removeTeamFromRedis(String.valueOf(team.getId()));
                        break;
                    case 3:
                        Team teamFromRedis = TeamRedisOperations.getTeamFromRedis(String.valueOf(team.getId()));
                        Module_communication moduleCommunication = new Module_communication();
                        if (teamFromRedis == null) {
                            Team team1 = teamService.findTeamById(team.getId());
                            if (team1 != null) {
                                moduleCommunication.__team = team1;
                                TeamRedisOperations.addTeamToRedis(String.valueOf(team.getId()), team1);
                            } else {
                                System.out.println("mysql not find team");//mysql也没有
                            }
                        } else {
                            moduleCommunication.__team = teamFromRedis;//操作类对象
                        }
                        moduleCommunication.module = 6;//操作类型
                        String gson = moduleCommunication.toGson();//将module_communication对象转换为gson
                        break;
                    default:
                        break;
                }
                break;

            case 7://7  TeamActivity
                TeamActivity teamActivity = module_communication.__teamActivity;
                Activity activity1 = module_communication.__activity;
                Team team1 = module_communication.__team;
                switch (module_communication.operation) {
                    case 0:
                        TeamActivityRedisOperations.addActivityToTeamInRedis(String.valueOf(team1.getId()), String.valueOf(activity1.getId()), teamActivity);
                        break;

                    case 1:
                        TeamActivityRedisOperations.removeActivityFromTeamInRedis(String.valueOf(team1.getId()), String.valueOf(activity1.getId()));
                        break;

                    case 2:
                        TeamActivityRedisOperations.updateActivityInTeamInRedis(String.valueOf(team1.getId()), String.valueOf(activity1.getId()), teamActivity);
                        break;

                    case 3:
                        TeamActivityRedisOperations.getActivityFromTeamInRedis(String.valueOf(team1.getId()), String.valueOf(activity1.getId()));
                        break;
                    default:

                        break;
                }
                break;

            case 8://8  User
                user = module_communication.__user;
                UserServiceImpl userService = ctx.getBean(UserServiceImpl.class);
                switch (module_communication.operation) {
                    case 0:
                        userService.insertUser(user);
                        UserRedisOperations.addUserToRedis(String.valueOf(user.getId()), user);
                        break;
                    case 1:
                        userService.deleteUserById(user.getId());
                        UserRedisOperations.removeUserFromRedis(String.valueOf(user.getId()));
                        break;
                    case 2:
                        userService.updateUser(user);
                        UserRedisOperations.removeUserFromRedis(String.valueOf(user.getId()));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        UserRedisOperations.removeUserFromRedis(String.valueOf(user.getId()));
                        break;
                    case 3:
                        User userFromRedis = UserRedisOperations.getUserFromRedis(String.valueOf(user.getId()));
                        Module_communication moduleCommunication = new Module_communication();
                        if (userFromRedis == null) {
                            User user2 = userService.getUserById(user.getId());
                            if (user2 != null) {
                                moduleCommunication.__user = user2;
                                UserRedisOperations.addUserToRedis(String.valueOf(user.getId()), user2);
                            } else {
                                System.out.println("mysql not find user");//mysql也没有
                            }
                        } else {
                            moduleCommunication.__user = userFromRedis;//操作类对象
                        }
                        moduleCommunication.module = 8;//操作类型
                        String gson = moduleCommunication.toGson();//将module_communication对象转换为gson
                        break;
                    default:

                        break;
                }
                break;

            case 9://9  UserLoginLog
                UserLoginLog userLoginLog = module_communication.__userLoginLog;
                UserLoginLogServiceImpl userLoginLogService = ctx.getBean(UserLoginLogServiceImpl.class);
                switch (module_communication.operation) {
                    case 0:
                        userLoginLogService.addUserLoginLog(userLoginLog);
                        UserLoginLogRedisOperations.addUserLoginLogToRedis(String.valueOf(userLoginLog.getLogId()), userLoginLog);
                        break;
                    case 1:
                        userLoginLogService.deleteUserLoginLog(userLoginLog.getLogId());
                        UserLoginLogRedisOperations.removeUserLoginLogFromRedis(String.valueOf(userLoginLog.getLogId()));
                        break;
                    case 2:
                        userLoginLogService.updateUserLoginLog(userLoginLog);
                        UserLoginLogRedisOperations.removeUserLoginLogFromRedis(String.valueOf(userLoginLog.getLogId()));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        UserLoginLogRedisOperations.removeUserLoginLogFromRedis(String.valueOf(userLoginLog.getLogId()));
                        break;
                    case 3:
                        UserLoginLog userLoginLogFromRedis = UserLoginLogRedisOperations.getUserLoginLogFromRedis(String.valueOf(userLoginLog.getUserId()), String.valueOf(userLoginLog.getLogId()));
                        Module_communication moduleCommunication = new Module_communication();
                        if (userLoginLogFromRedis == null) {
                            UserLoginLog userLoginLog1 = userLoginLogService.queryUserLoginLog(userLoginLog.getLogId());
                            if (userLoginLog1 != null) {
                                moduleCommunication.__userLoginLog = userLoginLog1;
                                UserLoginLogRedisOperations.addUserLoginLogToRedis(String.valueOf(userLoginLog.getLogId()), userLoginLog1);
                            } else {
                                System.out.println("mysql not find userLoginLog");//mysql也没有
                            }
                        } else {
                            moduleCommunication.__userLoginLog = userLoginLogFromRedis;//操作类对象
                        }
                        moduleCommunication.module = 9;//操作类型
                        String gson = moduleCommunication.toGson();//将module_communication对象转换为gson
                        break;
                    default:
                }
            default:
                break;
        }
    }
}
