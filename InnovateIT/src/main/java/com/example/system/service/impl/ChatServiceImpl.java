package com.example.system.service.impl;

import com.example.system.entity.Chat;
import com.example.system.mapper.ChatMapper;
import com.example.system.service.IChatService;
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
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {
    @Autowired
    private ChatMapper chatMapper;

    //查询所有
    public List<Chat> selectAll() {
        return chatMapper.selectList(null);
    }

    //增加chat
    public int addChat(Chat chat) {
        return chatMapper.insert(chat);
    }

    //删除chat
    public int deleteChat(Long id) {
        return chatMapper.deleteById(id);
    }

    //根据a_id和b_id删除chat
//    public int deleteChat(Long aId, Long bId) {
//        return chatMapper.deleteChat(aId, bId);
//    }


    //更新chat
    public int updateChat(Chat chat) {
        return chatMapper.updateById(chat);
    }

    //根据id查询chat_id
    public Chat selectChatById(Long id) {
        return chatMapper.selectById(id);
    }

    //根据a_id和b_id查询chat_id
    public Long selectChat(Long aId, Long bId) {
        return chatMapper.selectChat(aId,bId);
    }
}