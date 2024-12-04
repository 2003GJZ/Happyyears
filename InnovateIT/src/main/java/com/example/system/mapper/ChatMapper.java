package com.example.system.mapper;

import com.example.system.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
public interface ChatMapper extends BaseMapper<Chat> {

    Long selectChat(@Param("aId") Long aId, @Param("bId") Long bId);

}
