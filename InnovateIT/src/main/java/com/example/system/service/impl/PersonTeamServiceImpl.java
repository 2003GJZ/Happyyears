package com.example.system.service.impl;

import com.example.system.entity.PersonTeam;
import com.example.system.mapper.PersonTeamMapper;
import com.example.system.service.IPersonTeamService;
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
public class PersonTeamServiceImpl extends ServiceImpl<PersonTeamMapper, PersonTeam> implements IPersonTeamService {
    @Autowired
    private PersonTeamMapper personTeamMapper;

    // 添加团队成员
    public void addPersonToTeam(PersonTeam personTeam) {
        personTeamMapper.insert(personTeam);
    }

    // 更新团队成员信息
    public void updatePersonInTeam(PersonTeam personTeam) {
        personTeamMapper.updateById(personTeam);
    }

    // 删除团队成员
    public void removePersonFromTeam(Long personId, Long teamId) {
        personTeamMapper.deleteById(personId, teamId);
    }

    // 根据团队ID和成员ID获取团队成员信息
    public PersonTeam getPersonFromTeam(Long personId, Long teamId) {
        return personTeamMapper.selectById(personId, teamId);
    }

    // 查询团队的所有成员
    public List<PersonTeam> getAllMembersByTeamId(Long teamId) {
        return personTeamMapper.selectList(null);
    }

    // 查询成员所属的所有团队
    public List<PersonTeam> getAllTeamsByPersonId(Long personId) {
        return personTeamMapper.selectList(null);
    }
}