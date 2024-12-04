package com.example.system.mapper;
import java.util.Collection;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    //批量插入用户
    int insertBatch(@Param("userCollection") Collection<User> userCollection);

}
