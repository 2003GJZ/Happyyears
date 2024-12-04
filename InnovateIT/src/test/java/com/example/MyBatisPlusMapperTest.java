package com.example;

import com.example.system.entity.User;
import com.example.system.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MyBatisPlusMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        // 测试查询所有用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    @Test
    public void testInsert() {
        // 测试插入用户
        User user = new User();
        user.setUserName("张三");
        user.setPassword("123456");
        user.setPhoneNumber("12345678901");
        user.setAge(20);
        user.setGender("男");
        user.setLocation("北京");
//        user.setIdCardInfo("123456789012345678");
        user.setLoginStatus("1");
        user.setLoggedDevices("123456789012345678");
        user.setUserType("1");
        user.setInterests("123456789012345678");
        user.setAvatarUrl("123456789012345678");
        int rows = userMapper.insert(user);
        System.out.println("插入行数：" + rows);
        System.out.println("插入的用户ID：" + user.getId());
    }

    @Test
    public void testDeleteById() {
        // 测试根据id删除
//        int rows = userMapper.deleteById(4L);
//        System.out.println("删除行数：" + rows);
        // 测试根据map条件删除
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", 3L);
//        userMapper.deleteByMap(map);
//        System.out.println("删除成功");
        // 测试批量删除
        userMapper.deleteBatchIds(Arrays.asList(1L, 2L));
        System.out.println("删除成功");
    }

    @Test
    public void testUpdateById() {
        // 测试根据id修改
        User user = new User();
        user.setId(5L);
        user.setUserName("李四");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    public void testSelectById() {
//         测试根据id查询用户信息
        User user = userMapper.selectById(5L);
        System.out.println(user);
        // 测试根据id批量查询
//        List<User> users = userMapper.selectBatchIds(Arrays.asList(5L, 6L));
//        users.forEach(System.out::println);
        // 测试根据map条件查询
//        Map<String, Object> map = new HashMap<>();
//        map.put("phone_number", "12345678901");
//        List<User> users = userMapper.selectByMap(map);
//        users.forEach(System.out::println);
        // 测试查询所有用户信息
//        for (User user : userMapper.selectList(null)) {
//            System.out.println(user);
//        }

    }
}
