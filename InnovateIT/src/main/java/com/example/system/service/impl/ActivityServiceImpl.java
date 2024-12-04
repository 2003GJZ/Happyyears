package com.example.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.system.entity.Activity;
import com.example.system.mapper.ActivityMapper;
import com.example.system.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    //查询所有活动数量
    public int selectActivityCount() {
        return Math.toIntExact(activityMapper.selectCount(null));
    }

    //根据title查询活动数量（模糊查询）
    public int selectActivityCountByTitle(String title) {
        return Math.toIntExact(activityMapper.selectCount(new QueryWrapper<Activity>().like("title", title)));
    }

    //根据description查询活动数量（模糊查询）
    public int selectActivityCountByDescription(String description) {
        return Math.toIntExact(activityMapper.selectCount(new QueryWrapper<Activity>().like("description", description)));
    }

    //根据start_date查询活动数量
    public int selectActivityCountByStartDate(LocalDateTime start_date) {
        return Math.toIntExact(activityMapper.selectCount(new QueryWrapper<Activity>().ge("start_date", start_date)));
    }

    //根据end_date查询活动数量
    public int selectActivityCountByEndDate(LocalDateTime end_date) {
        return Math.toIntExact(activityMapper.selectCount(new QueryWrapper<Activity>().le("end_date", end_date)));
    }

    //根据organizer查询活动数量
    public int selectActivityCountByOrganizer(String organizer) {
        return Math.toIntExact(activityMapper.selectCount(new QueryWrapper<Activity>().eq("organizer", organizer)));
    }

    //根据type查询活动数量
    public int selectActivityCountByType(String type) {
        return Math.toIntExact(activityMapper.selectCount(new QueryWrapper<Activity>().eq("type", type)));
    }

    //根据status查询活动数量
    public int selectActivityCountByStatus(String status) {
        return Math.toIntExact(activityMapper.selectCount(new QueryWrapper<Activity>().eq("status", status)));
    }

    //根据location查询活动数量
    public int selectActivityCountByLocation(String location) {
        return Math.toIntExact(activityMapper.selectCount(new QueryWrapper<Activity>().eq("location", location)));
    }

    //查询所有活动
    public List<Activity> selectAllActivity() {
        return activityMapper.selectList(null);
    }

    //根据id查询活动
    public Activity selectActivityById(Long id) {
        return activityMapper.selectById(id);
    }

    //根据title查询活动(模糊查询)
    public List<Activity> selectActivityByTitle(String title) {
        return activityMapper.selectList(new QueryWrapper<Activity>().like("title", title));
    }

    //根据description查询活动(模糊查询)
    public List<Activity> selectActivityByDescription(String description) {
        return activityMapper.selectList(new QueryWrapper<Activity>().like("description", description));
    }

    //根据start_date查询活动
    public Activity selectActivityByStartDate(LocalDateTime start_date) {
        return activityMapper.selectOne(new QueryWrapper<Activity>().eq("start_date", start_date));
    }

    //根据end_date查询活动
    public Activity selectActivityByEndDate(LocalDateTime end_date) {
        return activityMapper.selectOne(new QueryWrapper<Activity>().eq("end_date", end_date));
    }

    //根据时间区间查询活动
    public List<Activity> selectActivityByTimeRange(LocalDateTime start_date, LocalDateTime end_date) {
        return activityMapper.selectList(new QueryWrapper<Activity>().ge("start_date", start_date).le("end_date", end_date));
    }

    //根据organizer查询活动
    public List<Activity> selectActivityByOrganizer(String organizer) {
        return activityMapper.selectList(new QueryWrapper<Activity>().eq("organizer", organizer));
    }

    //根据type查询活动
    public List<Activity> selectActivityByType(String type) {
        return activityMapper.selectList(new QueryWrapper<Activity>().eq("type", type));
    }

    //根据status查询活动
    public List<Activity> selectActivityByStatus(String status) {
        return activityMapper.selectList(new QueryWrapper<Activity>().eq("status", status));
    }

    //根据location查询活动
    public List<Activity> selectActivityByLocation(String location) {
        return activityMapper.selectList(new QueryWrapper<Activity>().eq("location", location));
    }

    //根据关键字查询活动
    public List<Activity> selectActivityByKeyword(String keyword) {
        return activityMapper.selectList(new QueryWrapper<Activity>().like("title", keyword).or().like("description", keyword));
    }

    //根据时间范围和关键字查询活动
    public List<Activity> selectActivityByKeywordAndDateRange(String keyword, LocalDateTime start_date, LocalDateTime end_date) {
        return activityMapper.selectList(new QueryWrapper<Activity>().like("title", keyword).or().like("description", keyword).ge("start_date", start_date).le("end_date", end_date));
    }

    //根据organizer,type查询活动
    public List<Activity> selectActivityByOrganizerAndType(String organizer, String type) {
        return activityMapper.selectList(new QueryWrapper<Activity>().eq("organizer", organizer).eq("type", type));
    }

    //根据organizer,type,status查询活动
    public List<Activity> selectActivityByOrganizerAndTypeAndStatus(String organizer, String type, String status) {
        return activityMapper.selectList(new QueryWrapper<Activity>().eq("organizer", organizer).eq("type", type).eq("status", status));
    }

    //根据organizer,type,status,location查询活动
    public List<Activity> selectActivityByOrganizerAndTypeAndStatusAndLocation(String organizer, String type, String status, String location) {
        return activityMapper.selectList(new QueryWrapper<Activity>().eq("organizer", organizer).eq("type", type).eq("status", status).eq("location", location));
    }

    //根据时间范围,organizer,type查询活动
    public List<Activity> selectActivityByDateRangeAndOrganizerAndType(LocalDateTime start_date, LocalDateTime end_date, String organizer, String type) {
        return activityMapper.selectList(new QueryWrapper<Activity>().ge("start_date", start_date).le("end_date", end_date).eq("organizer", organizer).eq("type", type));
    }

    //根据时间范围,organizer,type,status查询活动
    public List<Activity> selectActivityByDateRangeAndOrganizerAndTypeAndStatus(LocalDateTime start_date, LocalDateTime end_date, String organizer, String type, String status) {
        return activityMapper.selectList(new QueryWrapper<Activity>().ge("start_date", start_date).le("end_date", end_date).eq("organizer", organizer).eq("type", type).eq("status", status));
    }

    //插入活动
    public int insertActivity(Activity activity) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(activity.getId());
        if (oldActivity != null) {
            System.out.println("活动已存在" + activity.getId());
            return 0;//活动已存在，返回0表示插入失败
        }
        return activityMapper.insert(activity);
    }

    //根据id删除活动
    public int deleteActivityById(Long id) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(id);
        if (oldActivity == null) {
            System.out.println("活动不存在" + id);
            return 0;//活动不存在，返回0表示删除失败
        }
        return activityMapper.deleteById(id);
    }

    //根据实体类删除活动
    public int deleteActivityByEntity(Activity activity) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(activity.getId());
        if (oldActivity == null) {
            System.out.println("活动不存在" + activity.getId());
            return 0;//活动不存在，返回0表示删除失败
        }
        return activityMapper.deleteById(activity);
    }

    //更新活动
    public int updateActivity(Activity activity) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(activity.getId());
        if (oldActivity == null) {
            System.out.println("活动不存在" + activity.getId());
            return 0;//活动不存在，返回0表示更新失败
        }
        return activityMapper.updateById(activity);
    }

    //根据id更新活动
    public int updateActivityById(Activity activity) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(activity.getId());
        if (oldActivity == null) {
            System.out.println("活动不存在" + activity.getId());
            return 0;//活动不存在，返回0表示更新失败
        }
        return activityMapper.update(activity, new UpdateWrapper<Activity>().eq("id", activity.getId()));
    }

    //根据id更新活动的title
    public int updateActivityTitleById(Long id, String title) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(id);
        if (oldActivity == null) {
            System.out.println("活动不存在" + id);
            return 0;//活动不存在，返回0表示更新失败
        }
        return activityMapper.update(null, new UpdateWrapper<Activity>().set("title", title).eq("id", id));
    }

    //根据id更新活动的description
    public int updateActivityDescriptionById(Long id, String description) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(id);
        if (oldActivity == null) {
            System.out.println("活动不存在" + id);
            return 0;//活动不存在，返回0表示更新失败
        }
        return activityMapper.update(null, new UpdateWrapper<Activity>().set("description", description).eq("id", id));
    }

    //根据id更新活动的start_date
    public int updateActivityStartDateById(Long id, LocalDateTime start_date) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(id);
        if (oldActivity == null) {
            System.out.println("活动不存在" + id);
            return 0;//活动不存在，返回0表示更新失败
        }
        return activityMapper.update(null, new UpdateWrapper<Activity>().set("start_date", start_date).eq("id", id));
    }

    //根据id更新活动的end_date
    public int updateActivityEndDateById(Long id, LocalDateTime end_date) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(id);
        if (oldActivity == null) {
            System.out.println("活动不存在" + id);
            return 0;//活动不存在，返回0表示更新失败
        }
        return activityMapper.update(null, new UpdateWrapper<Activity>().set("end_date", end_date).eq("id", id));
    }

    //根据id更新活动的organizer
    public int updateActivityOrganizerById(Long id, String organizer) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(id);
        if (oldActivity == null) {
            System.out.println("活动不存在" + id);
            return 0;//活动不存在，返回0表示更新失败
        }
        return activityMapper.update(null, new UpdateWrapper<Activity>().set("organizer", organizer).eq("id", id));
    }

    //根据id更新活动的type
    public int updateActivityTypeById(Long id, String type) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(id);
        if (oldActivity == null) {
            System.out.println("活动不存在" + id);
            return 0;//活动不存在，返回0表示更新失败
        }
        return activityMapper.update(null, new UpdateWrapper<Activity>().set("type", type).eq("id", id));
    }

    //根据id更新活动的status
    public int updateActivityStatusById(Long id, String status) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(id);
        if (oldActivity == null) {
            System.out.println("活动不存在" + id);
            return 0;//活动不存在，返回0表示更新失败
        }
        return activityMapper.update(null, new UpdateWrapper<Activity>().set("status", status).eq("id", id));
    }

    //根据id更新活动的location
    public int updateActivityLocationById(Long id, String location) {
        //查询活动是否存在
        Activity oldActivity = activityMapper.selectById(id);
        if (oldActivity == null) {
            System.out.println("活动不存在" + id);
            return 0;//活动不存在，返回0表示更新失败
        }
        return activityMapper.update(null, new UpdateWrapper<Activity>().set("location", location).eq("id", id));
    }
}