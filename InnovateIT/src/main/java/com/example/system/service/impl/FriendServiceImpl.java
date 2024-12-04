package com.example.system.service.impl;

import com.example.system.entity.Friend;
import com.example.system.mapper.FriendMapper;
import com.example.system.service.IFriendService;
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
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements IFriendService {
    @Autowired
    private FriendMapper friendMapper;

    // 创建朋友关系
    public void createFriend(Friend friend) {
        friendMapper.insert(friend);
    }

    // 更新朋友关系状态
    public void updateFriendStatus(Long id, String status) {
        Friend friend = friendMapper.selectById(id);
        if (friend != null) {
            friend.setStatus(status);
            friendMapper.updateById(friend);
        }
    }

    // 删除朋友关系
    public void deleteFriend(Long id) {
        friendMapper.deleteById(id);
    }

    // 查询用户的所有朋友
    public List<Friend> getAllFriendsByUserId(Long userId) {
        return friendMapper.selectList(null);
    }

    // 查询用户的特定朋友
    public Friend getFriendById(Long id) {
        return friendMapper.selectById(id);
    }
}