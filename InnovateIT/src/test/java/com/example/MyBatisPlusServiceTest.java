package com.example;

import com.example.system.entity.User;
import com.example.system.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyBatisPlusServiceTest {
    @Autowired
    private IUserService userService;

    // 测试你的Service方法
    @Test
    public void testServiceMethod() {
        //查询总记录数量
        long count = userService.count();
        System.out.println("User count: " + count);
    }

    @Test
    public void testSelectAll() {
        List<User> userList = userService.list();

        for (User user : userList) {
            System.out.println(user);
        }
    }
    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("张三");
        user.setPassword("123456");
        user.setAge(20);
        user.setGender("男");
        user.setPhoneNumber("12345678901");
        user.setLocation("北京");
        user.setIdCardInfo("123456789012345678");
        user.setLoginStatus("1");
        user.setLoggedDevices("123456789012345678");
        user.setUserType("1");
        user.setInterests("123456789012345678");
        user.setAvatarUrl("123456789012345678");
        userService.save(user);
        System.out.println("User inserted successfully.");
        System.out.println("插入的用户ID：" + user.getId());
        // 再次查询总记录数量
        long count = userService.count();
        System.out.println("User count after insert: " + count);
    }

    @Test
    public void testInsertMore() {
        // 批量插入
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName("张三" + i);
            user.setPassword("123456");
            user.setAge(20 + i);
            user.setGender("男");
            user.setPhoneNumber("12345678901");
            user.setLocation("北京");
            user.setIdCardInfo("123456789012345678");
            user.setLoginStatus("1");
            user.setLoggedDevices("123456789012345678");
            user.setUserType("1");
            user.setInterests("123456789012345678");
            user.setAvatarUrl("123456789012345678");
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }

    @Test
    public void testDeleteAll() {
        boolean remove = userService.remove(null);
        System.out.println(remove);
    }

}
