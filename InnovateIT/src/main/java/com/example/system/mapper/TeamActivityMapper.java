package com.example.system.mapper;

import com.example.system.entity.TeamActivity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
public interface TeamActivityMapper extends BaseMapper<TeamActivity> {

    void deleteById(Long teamId, Long activityId);

    TeamActivity selectById(Long teamId, Long activityId);

}
