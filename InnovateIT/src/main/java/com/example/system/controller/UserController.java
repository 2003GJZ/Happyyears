package com.example.system.controller;

import com.example.InnovateItApplication;
import com.example.system.entity.User;

import com.example.system.mapper.UserMapper;
import com.example.system.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(InnovateItApplication.class, args);
        UserServiceImpl userService = ctx.getBean(UserServiceImpl.class);

//        User user = new User();
//        User user1 = new User();
//        user.setId(3L);
//        user.setUserName("zhangsan");
//        user.setPassword("123456");
//        user1.setId(4L);
//        user1.setUserName("lisi");
//        user1.setPassword("123456");
//        userService.insertUser(user);
//        userService.insertUser(user1);
//        user.setId(1774782774679592920L);
//        user.setUserName("zhangsan");
//
//        userService.insertUser(user);
//        int i = userService.updateUser(user);
//        System.out.println(i);


//        User userById = userService.getUserById(1774782774679592973L);
//        System.out.println(userById);

//        int i = userService.deleteUserById(1774782774679592972L);
//        System.out.println(i);

//        int i = userService.deleteAllUsers();
//        System.out.println(i);

//        List<User> userByInterests = userService.getUserByInterests("DIY");
//        System.out.println(userByInterests);

        for (User allUser : userService.getAllUsers()) {
            System.out.println(allUser);
        }


//        int count = userService.getUserCountByGender("男");
//        System.out.println(count);

//        User userById = userService.getUserById(1774782774679592961L);
//        System.out.println(userById);

//        for (User user : userService.getUserByUsername("张三")) {
//            System.out.println(user);
//        }

//        int userCount = userService.getUserCount();
//        System.out.println(userCount);


//        int i = userService.deleteUserById(1774782761501184002L);
//        System.out.println(i);


//        UserMapper userMapper = ctx.getBean(UserMapper.class);
//        for (User user : userMapper.selectList(null)) {
//            System.out.println(user);
//        }

//        User user = new User();
//        user.setUserName("张三");
//        user.setPassword("123456");
//        user.setAge(20);
//        user.setGender("男");
//        user.setEmail("john@example.com");
//        user.setPhoneNumber("12345678901");
//        user.setLocation("北京");
//        user.setIdCardInfo("123456789012345678");
//        user.setLoginStatus("1");
//        user.setLoggedDevices("123456789012345678");
//        user.setUserType("1");
//        user.setInterests("123456789012345678");
//        int i = userService.insertUser(user);
//        System.out.println(i);


//        bean.save(user);//添加用户
//        bean.removeById(1L);//根据用户id删除用户
//        bean.list().forEach(System.out::println);//查询所有用户
//        System.out.println("count:" + userService.count());//查询用户数量

    }
}
