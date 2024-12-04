package com.example.system.service.impl;

import com.example.system.entity.TeamActivity;
import com.example.system.mapper.TeamActivityMapper;
import com.example.system.service.ITeamActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
@Service
public class TeamActivityServiceImpl extends ServiceImpl<TeamActivityMapper, TeamActivity> implements ITeamActivityService {
    @Autowired
    private TeamActivityMapper teamActivityMapper;

    // 添加团队活动关联
    public  void addActivityToTeam(TeamActivity teamActivity) {
        teamActivityMapper.insert(teamActivity);
    }

    // 移除团队活动关联
    public void removeActivityFromTeam(Long teamId, Long activityId) {
        teamActivityMapper.deleteById(teamId, activityId);
    }

    // 根据团队ID和活动ID获取团队活动关联信息
    public TeamActivity getActivityFromTeam(Long teamId, Long activityId) {
        return teamActivityMapper.selectById(teamId, activityId);
    }

    // 查询团队的所有活动
    public List<TeamActivity> getAllActivitiesByTeamId(Long teamId) {
        return teamActivityMapper.selectList(null);
    }

    // 查询活动所属的所有团队
    public List<TeamActivity> getAllTeamsByActivityId(Long activityId) {
        return teamActivityMapper.selectList(null);
    }
}
