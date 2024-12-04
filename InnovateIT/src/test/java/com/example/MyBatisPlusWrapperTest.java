package com.example;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.system.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.system.entity.User;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;

    // 测试查询
    @Test
    public void testWrapper() {
        // 创建查询条件
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("username", "三")
                .between("id", 1, 100)
                .isNotNull("id_card_info ");
        for (User user : userMapper.selectList(userQueryWrapper)) {
            System.out.println(user); // 输出符合条件的结果
        }
    }

    // 测试排序
    @Test
    public void testWrapper2() {
        // 按年龄降序，id降序排序
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByDesc("age")
                .orderByDesc("id");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper3() {
        //删除指定属性为null的记录
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.isNull("id_card_info");
        int delete = userMapper.delete(userQueryWrapper);
        System.out.println("删除记录数：" + delete);
    }

    @Test
    public void testWrapper4() {
        // 更新(年龄大于10且名字含有三)或者id_card_info为null的记录
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt("age", 10)
                .like("username", "三")
                .or()
                .isNull("id_card_info");
        User user = new User();
        user.setAge(18);
        user.setUsername("张3");
        int update = userMapper.update(user, userQueryWrapper);
        System.out.println("更新记录数：" + update);
    }


    @Test
    public void testWrapper5() {
        //将用户名中含有"三"并且（年龄大于10或者id_card_info为null）的用户信息修改
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("username", "3")
                .and(i -> i.gt("age", 10).or().isNull("id_card_info"));
        User user = new User();
        user.setUsername("小红");
        int update = userMapper.update(user, userQueryWrapper);
        System.out.println("更新记录数：" + update);
    }


    @Test
    public void testWrapper6() {
        //查询用户的用户名，年龄，电话号码
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("username", "age", "phone_number");
        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);
        maps.forEach(System.out::println);
    }


    @Test
    public void testWrapper7() {
        //查询用户id大于100的用户的信息  SELECT id,username,password,age,gender,phone_number,location,id_card_info,login_status,logged_devices,user_type,interests,avatar_url,is_deleted FROM user WHERE is_deleted=0 AND (id IN (select id from user where id > 100))
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.inSql("id", "select id from user where id > 100");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }


    @Test
    public void testWrapper8() {
        //将用户名中含有"三"并且（年龄大于10或者id_card_info为null）的用户信息修改
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.like("username", "红")
                .and(i -> i.gt("age", 10).or().isNull("id_card_info"));
        userUpdateWrapper.set("username", "张三").set("id_card_info", "1234567890123456789");
        int update = userMapper.update(null, userUpdateWrapper);
        System.out.println(update);
    }


    @Test
    public void testWrapper9() {
        String username = "张三";
        Integer ageBegin = null;
        Integer aggEnd = 30;

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            //判断某个字符串是否不为空字符串，不为null,不为空白符
            userQueryWrapper.like("username", username);
        }

        if (ageBegin != null) {
            userQueryWrapper.ge("age", ageBegin);
        }

        if (aggEnd != null) {
            userQueryWrapper.le("age", aggEnd);
        }

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
        System.out.println(users.size());
    }


    @Test
    public void testWrapper10() {
        String username = "张三";
        Integer ageBegin = null;
        Integer aggEnd = 30;

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like(StringUtils.isNotBlank(username), "username", username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(aggEnd != null, "age", aggEnd);

        List<User> users = userMapper.selectList
                (userQueryWrapper);
        users.forEach(System.out::println);
        System.out.println(users.size());
    }


    @Test
    public void testWrapper11() {
        String username = "张三";
        Integer ageBegin = null;
        Integer aggEnd = 30;
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.like(StringUtils.isNotBlank(username), User::getUserName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(aggEnd != null, User::getAge, aggEnd);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
        System.out.println(users.size());
    }

    @Test
    public void testWrapper12() {
        //将用户名中含有"三"并且（年龄大于10或者id_card_info为null）的用户信息修改
        LambdaUpdateWrapper<User> userUpdateWrapper = new LambdaUpdateWrapper<>();
        userUpdateWrapper.like(User::getUserName, "三·")
                .and(i -> i.gt(User::getAge, 10).or().isNull(User::getIdCardInfo));
        userUpdateWrapper.set(User::getUserName, "张三").set(User::getIdCardInfo, "1234567890123456789");
        int update = userMapper.update(null, userUpdateWrapper);
        System.out.println(update);
    }
}
