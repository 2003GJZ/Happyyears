package com.example.system.service.impl;

import com.example.system.entity.UserLoginLog;
import com.example.system.mapper.UserLoginLogMapper;
import com.example.system.service.IUserLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements IUserLoginLogService {
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    //增加UserLoginLog
    public int addUserLoginLog(UserLoginLog userLoginLog) {
        return userLoginLogMapper.insert(userLoginLog);
    }

    //删除UserLoginLog
    public int deleteUserLoginLog(Long id) {
        return userLoginLogMapper.deleteById(id);
    }

    //修改UserLoginLog
    public int updateUserLoginLog(UserLoginLog userLoginLog) {
        return userLoginLogMapper.updateById(userLoginLog);
    }

    //查询UserLoginLog

    public UserLoginLog queryUserLoginLog(Long id) {
        return userLoginLogMapper.selectById(id);
    }
}
