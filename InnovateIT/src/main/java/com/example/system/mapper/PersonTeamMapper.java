package com.example.system.mapper;

import com.example.system.entity.PersonTeam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
public interface PersonTeamMapper extends BaseMapper<PersonTeam> {

    void deleteById(Long personId, Long teamId);

    PersonTeam selectById(Long personId, Long teamId);
}
