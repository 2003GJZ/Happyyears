package com.example.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.system.entity.Team;
import com.example.system.mapper.TeamMapper;
import com.example.system.service.ITeamService;
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
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements ITeamService {
    @Autowired
    private TeamMapper teamMapper;

    //查询所有团队数量
    public int findAllTeamCount() {
        return Math.toIntExact(teamMapper.selectCount(null));
    }

    //查询所有团队信息
    public List<Team> findAllTeam() {
        return teamMapper.selectList(null);
    }

    //根据id查询团队信息
    public Team findTeamById(Long id) {
        return teamMapper.selectById(id);
    }

    //根据name查询团队信息
    public Team findTeamByName(String name) {
        return teamMapper.selectOne(new QueryWrapper<Team>().eq("name", name));
    }

    //根据type查询团队信息
    public List<Team> findTeamByType(String type) {
        return teamMapper.selectList(new QueryWrapper<Team>().eq("type", type));
    }

    //根据description查询团队信息（模糊查询）
    public List<Team> findTeamByDescription(String description) {
        return teamMapper.selectList(new QueryWrapper<Team>().like("description", description));
    }

    //根据leader_id查询团队信息
    public List<Team> findTeamByLeaderId(Long leader_id) {
        return teamMapper.selectList(new QueryWrapper<Team>().eq("leader_id", leader_id));
    }

    //根据status查询团队信息
    public List<Team> findTeamByStatus(String status) {
        return teamMapper.selectList(new QueryWrapper<Team>().eq("status", status));
    }


    //根据id更新团队信息
    public int updateTeamById(Team team) {
        //查询团队是否存在
        Team team1 = teamMapper.selectById(team.getId());
        if (team1 == null) {
            System.out.println("团队不存在" + team.getId());
            return 0;//团队不存在，返回0
        }
        return teamMapper.updateById(team);
    }

    //根据id删除团队信息
    public int deleteTeamById(Long id) {
        //查询团队是否存在
        Team team1 = teamMapper.selectById(id);
        if (team1 == null) {
            System.out.println("团队不存在" + id);
            return 0;//团队不存在，返回0
        }
        return teamMapper.deleteById(id);
    }

    //插入团队
    public int insertTeam(Team team) {
        //查询团队是否存在
        Team team1 = teamMapper.selectOne(new QueryWrapper<Team>().eq("name", team.getName()));
        if (team1 != null) {
            System.out.println("团队已存在" + team.getName());
            return 0;//团队已存在，返回0
        }
        return teamMapper.insert(team);
    }

}
