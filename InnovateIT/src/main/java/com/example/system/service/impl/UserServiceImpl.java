package com.example.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.system.entity.User;
import com.example.system.mapper.UserMapper;
import com.example.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    //获取所有用户数量
    public int getUserCount() {
        return Math.toIntExact(userMapper.selectCount(null));
    }

    //根据年龄获取用户数量
    public int getUserCountByAge(int age) {
        return Math.toIntExact(userMapper.selectCount(new QueryWrapper<User>().eq("age", age)));
    }

    //根据年龄区间获取用户数量
    public int getUserCountByAgeRange(int minAge, int maxAge) {
        return Math.toIntExact(userMapper.selectCount(new QueryWrapper<User>().ge("age", minAge).le("age", maxAge)));
    }

    //根据性别获取用户数量
    public int getUserCountByGender(String gender) {
        return Math.toIntExact(userMapper.selectCount(new QueryWrapper<User>().eq("gender", gender)));
    }

    //根据所在地用户数量
    public int getUserCountByLocation(String location) {
        return Math.toIntExact(userMapper.selectCount(new QueryWrapper<User>().eq("location", location)));
    }

    //根据用户登录状态用户数量
    public int getUserCountByLoginStatus(String loginStatus) {
        return Math.toIntExact(userMapper.selectCount(new QueryWrapper<User>().eq("login_status", loginStatus)));
    }

    //根据用户类型获取用户数量
    public int getUserCountByUserType(String userType) {
        return Math.toIntExact(userMapper.selectCount(new QueryWrapper<User>().eq("user_type", userType)));
    }

    //根据爱好获取用户数量(模糊匹配)
    public int getUserCountByInterests(String interests) {
        return Math.toIntExact(userMapper.selectCount(new QueryWrapper<User>().like("interests", interests)));
    }

    // 获取所有用户信息
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    // 根据id获取用户信息
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    //根据用户名获取用户信息(可能包含多个用户，因为一个用户名可能对应多个用户，例如用户名相同但id不同的用户)
    public List<User> getUserByUsername(String username) {
        return userMapper.selectList(new QueryWrapper<User>().eq("username", username));
    }

    //根据用户密码获取用户列表
    public List<User> getUserByPassword(String password) {
        return userMapper.selectList(new QueryWrapper<User>().eq("password", password));
    }

    //根据指定年龄获取用户列表
    public List<User> getUserByAge(int age) {
        return userMapper.selectList(new QueryWrapper<User>().eq("age", age));
    }

    //根据年龄范围获取用户列表
    public List<User> getUserByAgeRange(int minAge, int maxAge) {
        return userMapper.selectList(new QueryWrapper<User>().ge("age", minAge).le("age", maxAge));
    }

    //根据性别获取用户列表
    public List<User> getUserByGender(String gender) {
        return userMapper.selectList(new QueryWrapper<User>().eq("gender", gender));
    }

    //根据年龄范围和性别获取用户列表
    public List<User> getUserByAgeRangeAndGender(int minAge, int maxAge, String gender) {
        // 使用lambda表达式构建查询条件
        return userMapper.selectList(new QueryWrapper<User>().ge("age", minAge).le("age", maxAge).eq("gender", gender));
    }

    //根据email获取用户
    public User getUserByEmail(String email) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("email", email));
    }

    //根据手机号获取用户信息
    public User getUserByPhoneNumber(String phoneNumber) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("phone_number", phoneNumber));
    }

    //根据地址获取用户列表
    public List<User> getUserByAddress(String location) {
        return userMapper.selectList(new QueryWrapper<User>().like("location", location));
    }

    //根据idCardInfo获取用户
    public User getUserByIdCardInfo(String idCardInfo) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("id_card_info", idCardInfo));
    }

    //根据loginStatus获取用户
    public List<User> getUserByLoginStatus(String loginStatus) {
        return userMapper.selectList(new QueryWrapper<User>().eq("login_status", loginStatus));
    }

    //根据loggedDevices获取用户
    public List<User> getUserByLoggedDevices(String loggedDevices) {
        return userMapper.selectList(new QueryWrapper<User>().eq("logged_devices", loggedDevices));
    }

    //根据userType获取用户
    public List<User> getUserByUserType(String userType) {
        return userMapper.selectList(new QueryWrapper<User>().eq("user_type", userType));
    }

    //根据interests获取用户列表
    public List<User> getUserByInterests(String interests) {
        return userMapper.selectList(new QueryWrapper<User>().like("interests", interests));
    }

    //根据isDeleted获取用户列表
    public List<User> getUserByIsDeleted(Integer isDeleted) {
        return userMapper.selectList(new QueryWrapper<User>().eq("is_deleted", isDeleted));
    }

    //根据age，gender和interests获取用户列表
    public List<User> getUserByAgeGenderInterests(Integer age, String gender, String interests) {
        return userMapper.selectList(new QueryWrapper<User>()
                .eq("age", age)
                .eq("gender", gender)
                .like("interests", interests));
    }


    //根据id删除用户
    public int deleteUserById(Long id) {
        // 删除用户之前，判断该用户是否存在
        User user = userMapper.selectById(id);
        if (user == null) {
            System.out.println("用户id不存在：" + id);
            return 0; // 用户不存在，返回0表示删除失败
        }
        return userMapper.deleteById(id);
    }

    //删除批量用户
    public int deleteUserByIds(List<Long> ids) {
        // 删除用户之前，判断该用户是否存在
        for (Long id : ids) {
            User user = userMapper.selectById(id);
            if (user == null) {
                System.out.println("用户id不存在：" + id);
                return 0; // 用户不存在，返回0表示删除失败
            }
        }
        return userMapper.deleteBatchIds(ids);
    }

    //删除所有用户
    public int deleteAllUsers() {
        return userMapper.delete(null);
    }

//    //恢复isDeleted为1的用户
//    public List<User> listDeletedRecords() {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("is_deleted", 1); // 查找逻辑删除字段为已删除状态的记录
//        return this.list(queryWrapper); // 使用 MyBatis Plus 提供的 list 方法查询记录
//    }

    //插入用户
    public int insertUser(User user) {
        //插入用户之前，判断该用户的phone_number是否已经存在
        User existingUserPhoneNumber = userMapper.selectOne(new QueryWrapper<User>().eq("phone_number", user.getPhoneNumber()));
        //插入用户之前，判断该用户的email是否已经存在
        User existingUserEmail = userMapper.selectOne(new QueryWrapper<User>().eq("email", user.getEmail()));
        //插入用户之前，判断该用户的id_card_info是否已经存在
        User existingUserIdCardInfo = userMapper.selectOne(new QueryWrapper<User>().eq("id_card_info", user.getIdCardInfo()));
        if (existingUserPhoneNumber != null || existingUserEmail != null || existingUserIdCardInfo != null) {
            if (existingUserPhoneNumber != null) {
                System.out.println("该手机号已存在：" + existingUserPhoneNumber.getPhoneNumber());
            }
            if (existingUserEmail != null) {
                System.out.println("该邮箱已存在：" + existingUserEmail.getEmail());
            }
            if (existingUserIdCardInfo != null) {
                System.out.println("该身份证信息已存在：" + existingUserIdCardInfo.getIdCardInfo());
            }
            return 0; // 用户已存在，返回0表示插入失败
        }
        return userMapper.insert(user);
    }

    //批量插入用户
    public int insertUsers(List<User> users) {
        //批量插入用户之前，判断每个用户的phone_number是否已经存在
        for (User user : users) {
            User existingUserPhoneNumber = userMapper.selectOne(new QueryWrapper<User>().eq("phone_number", user.getPhoneNumber()));
            //批量插入用户之前，判断每个用户的email是否已经存在
            User existingUserEmail = userMapper.selectOne(new QueryWrapper<User>().eq("email", user.getEmail()));
            //批量插入用户之前，判断每个用户的id_card_info是否已经存在
            User existingUserIdCardInfo = userMapper.selectOne(new QueryWrapper<User>().eq("id_card_info", user.getIdCardInfo()));

            if (existingUserPhoneNumber != null || existingUserEmail != null || existingUserIdCardInfo != null) {
                if (existingUserPhoneNumber != null) {
                    System.out.println("该手机号已存在：" + existingUserPhoneNumber.getPhoneNumber());
                }
                if (existingUserEmail != null) {
                    System.out.println("该邮箱已存在：" + existingUserEmail.getEmail());
                }
                if (existingUserIdCardInfo != null) {
                    System.out.println("该身份证信息已存在：" + existingUserIdCardInfo.getIdCardInfo());
                }
                return 0; // 用户已存在
            }
        }
        return userMapper.insertBatch(users);
    }


    //更新用户信息
    public int updateUser(User user) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(user.getId());
        if (existingUser == null) {
            System.out.println("用户id不存在：" + user.getId());
            return 0; // 用户不存在，返回0表示更新失败
        }
        return userMapper.updateById(user);
    }

    //更新用户信息（根据id）
    public int updateUserById(User user) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(user.getId());
        if (existingUser == null) {
            System.out.println("用户id不存在：" + user.getId());
            return 0; // 用户不存在，返回0表示更新失败
        }
        return userMapper.update(user, new UpdateWrapper<User>().eq("id", user.getId()));
    }

    //根据id更新用户username
    public int updateUserUsernameById(Long id, String username) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("username", username).eq("id", id));
    }

    //根据id更新用户password
    public int updateUserPasswordById(Long id, String password) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("password", password).eq("id", id));
    }

    //根据id更新用户age
    public int updateUserAgeById(Long id, Integer age) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("age", age).eq("id", id));
    }

    //根据id更新用户gender
    public int updateUserGenderById(Long id, String gender) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("gender", gender).eq("id", id));
    }

    //根据id更新用户email
    public int updateUserEmailById(Long id, String email) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("email", email).eq("id", id));
    }

    //根据id更新用户phone_number
    public int updateUserPhoneNumberById(Long id, String phone_number) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("phone_number", phone_number).eq("id", id));
    }

    //根据id更新用户location
    public int updateUserLocationById(Long id, String location) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("location", location).eq("id", id));
    }

    //根据id更新用户loginStatus
    public int updateUserLoginStatusById(Long id, String loginStatus) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("login_status", loginStatus).eq("id", id));
    }

    //根据id更新用户loggedDevices
    public int updateUserLoggedDevicesById(Long id, String loggedDevices) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("logged_devices", loggedDevices).eq("id", id));
    }

    //根据id更新用户userType
    public int updateUserUserTypeById(Long id, String userType) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("user_type", userType).eq("id", id));
    }

    //根据id更新用户interests
    public int updateUserInterestsById(Long id, String interests) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("interests", interests).eq("id", id));
    }

    //根据id更新用户avatarUrl
    public int updateUserAvatarUrlById(Long id, String avatarUrl) {
        // 更新用户之前，判断该用户是否存在
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            System.out.println("用户id不存在：" + id);
        }
        return userMapper.update(null, new UpdateWrapper<User>().set("avatar_url", avatarUrl).eq("id", id));
    }
}