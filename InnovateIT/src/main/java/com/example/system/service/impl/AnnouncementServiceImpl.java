package com.example.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.system.entity.Announcement;
import com.example.system.mapper.AnnouncementMapper;
import com.example.system.service.IAnnouncementService;
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
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    //获取所有公告数量
    public int getAllAnnouncementCount() {
        return Math.toIntExact(announcementMapper.selectCount(null));
    }

    //根据title查询公告数量（模糊查询）
    public int getAnnouncementCountByTitle(String title) {
        return Math.toIntExact(announcementMapper.selectCount(new QueryWrapper<Announcement>().like("title", title)));
    }

    //根据content查询公告数量（模糊查询）
    public int getAnnouncementCountByContent(String content) {
        return Math.toIntExact(announcementMapper.selectCount(new QueryWrapper<Announcement>().like("content", content)));
    }

    //根据关键字查询公告数量
    public int getAnnouncementCountByKeyword(String keyword) {
        return Math.toIntExact(announcementMapper.selectCount(new QueryWrapper<Announcement>().like("title", keyword).or().like("content", keyword)));
    }

    //根据author_id查询公告数量
    public int getAnnouncementCountByAuthorId(Long authorId) {
        return Math.toIntExact(announcementMapper.selectCount(new QueryWrapper<Announcement>().eq("author_id", authorId)));
    }

    //根据visibility查询公告数量
    public int getAnnouncementCountByVisibility(byte visibility) {
        return Math.toIntExact(announcementMapper.selectCount(new QueryWrapper<Announcement>().eq("visibility", visibility)));
    }

    //根据priority查询公告数量
    public int getAnnouncementCountByPriority(byte priority) {
        return Math.toIntExact(announcementMapper.selectCount(new QueryWrapper<Announcement>().eq("priority", priority)));
    }

    //根据status查询公告数量
    public int getAnnouncementCountByStatus(byte status) {
        return Math.toIntExact(announcementMapper.selectCount(new QueryWrapper<Announcement>().eq("status", status)));
    }

    //获取所有公告
    public List<Announcement> getAllAnnouncement() {
        return announcementMapper.selectList(null);
    }

    //根据id获取公告
    public Announcement getAnnouncementById(Long id) {
        return announcementMapper.selectById(id);
    }

    //根据author_id获取公告
    public List<Announcement> getAnnouncementByAuthorId(Long authorId) {
        return announcementMapper.selectList(new QueryWrapper<Announcement>().eq("author_id", authorId));
    }

    //根据title获取公告（模糊查询）
    public List<Announcement> getAnnouncementByTitle(String title) {
        return announcementMapper.selectList(new QueryWrapper<Announcement>().like("title", title));
    }

    //根据description查询公告(模糊查询)
    public List<Announcement> getAnnouncementByDescription(String description) {
        return announcementMapper.selectList(new QueryWrapper<Announcement>().like("description", description));
    }

    //根据关键字查询公告
    public List<Announcement> getAnnouncementByKeyword(String keyword) {
        return announcementMapper.selectList(new QueryWrapper<Announcement>().like("title", keyword).or().like("description", keyword));
    }

    //根据visibility查询公告
    public List<Announcement> getAnnouncementByVisibility(byte visibility) {
        return announcementMapper.selectList(new QueryWrapper<Announcement>().eq("visibility", visibility));
    }

    //根据priority查询公告
    public List<Announcement> getAnnouncementByPriority(byte priority) {
        return announcementMapper.selectList(new QueryWrapper<Announcement>().eq("priority", priority));
    }

    //根据status查询公告
    public List<Announcement> getAnnouncementByStatus(byte status) {
        return announcementMapper.selectList(new QueryWrapper<Announcement>().eq("status", status));
    }

    //插入公告
    public int insertAnnouncement(Announcement announcement) {
        //查询公告是否存在
        Announcement oldAnnouncement = announcementMapper.selectById(announcement.getId());
        if (oldAnnouncement != null) {
            System.out.println("公告已存在" + announcement.getId());
            return 0;//公告已存在返回0
        }
        return announcementMapper.insert(announcement);
    }

    //根据id更新公告
    public int updateAnnouncementById(Announcement announcement) {
        //查询公告是否存在
        Announcement oldAnnouncement = announcementMapper.selectById(announcement.getId());
        if (oldAnnouncement == null) {
            System.out.println("公告不存在" + announcement.getId());
            return 0;//公告不存在返回0
        }
        return announcementMapper.update(announcement, new UpdateWrapper<Announcement>().eq("id", announcement.getId()));
    }

    //更新公告
    public int updateAnnouncement(Announcement announcement) {
        //查询公告是否存在
        Announcement oldAnnouncement = announcementMapper.selectById(announcement.getId());
        if (oldAnnouncement == null) {
            System.out.println("公告不存在" + announcement.getId());
            return 0;//公告不存在返回0
        }
        return announcementMapper.updateById(announcement);
    }

    //根据id更新公告title
    public int updateAnnouncementTitleById(Long id, String title) {
        //查询公告是否存在
        Announcement oldAnnouncement = announcementMapper.selectById(id);
        if (oldAnnouncement == null) {
            System.out.println("公告不存在" + id);
            return 0;//公告不存在返回0
        }
        return announcementMapper.update(null, new UpdateWrapper<Announcement>().set("title", title).eq("id", id));
    }

    //根据id更新公告author_id
    public int updateAnnouncementAuthorIdById(Long id, Long authorId) {
        //查询公告是否存在
        Announcement oldAnnouncement = announcementMapper.selectById(id);
        if (oldAnnouncement == null) {
            System.out.println("公告不存在" + id);
            return 0;//公告不存在返回0
        }
        return announcementMapper.update(null, new UpdateWrapper<Announcement>().set("author_id", authorId).eq("id", id));
    }

    //根据id更新公告content
    public int updateAnnouncementContentById(Long id, String content) {
        //查询公告是否存在
        Announcement oldAnnouncement = announcementMapper.selectById(id);
        if (oldAnnouncement == null) {
            System.out.println("公告不存在" + id);
            return 0;//公告不存在返回0
        }
        return announcementMapper.update(null, new UpdateWrapper<Announcement>().set("content", content).eq("id", id));
    }

    //根据id更新公告visibility
    public int updateAnnouncementVisibilityById(Long id, Byte visibility) {
        //查询公告是否存在
        Announcement oldAnnouncement = announcementMapper.selectById(id);
        if (oldAnnouncement == null) {
            System.out.println("公告不存在" + id);
            return 0;//公告不存在返回0
        }
        return announcementMapper.update(null, new UpdateWrapper<Announcement>().set("visibility", visibility).eq("id", id));
    }

    //根据id更新公告priority
    public int updateAnnouncementPriorityById(Long id, Byte priority) {
        //查询公告是否存在
        Announcement oldAnnouncement = announcementMapper.selectById(id);
        if (oldAnnouncement == null) {
            System.out.println("公告不存在" + id);
            return 0;//公告不存在返回0
        }
        return announcementMapper.update(null, new UpdateWrapper<Announcement>().set("priority", priority).eq("id", id));
    }

    //根据id更新公告status
    public int updateAnnouncementStatusById(Long id, Byte status) {
        //查询公告是否存在
        Announcement oldAnnouncement = announcementMapper.selectById(id);
        if (oldAnnouncement == null) {
            System.out.println("公告不存在" + id);
            return 0;//公告不存在返回0
        }
        return announcementMapper.update(null, new UpdateWrapper<Announcement>().set("status", status).eq("id", id));
    }

    //删除公告
    public void deleteAnnouncement(Long id) {
        //查询公告是否存在
        Announcement oldAnnouncement = announcementMapper.selectById(id);
        if (oldAnnouncement == null) {
            System.out.println("公告不存在" + id);
            return;//公告不存在返回
        }
        announcementMapper.deleteById(id);
    }

    //按照发布时间降序查询公告列表
    public List<Announcement> getAnnouncementListByPublishTimeDesc() {
        return announcementMapper.selectList(new QueryWrapper<Announcement>().orderByDesc("publish_time"));
    }

    //按照发布时间降序查询指定数量的公告列表
    public List<Announcement> getAnnouncementListByPublishTimeDesc(int pageNum, int pageSize) {
        return announcementMapper.selectPage(new Page<>(pageNum, pageSize), new QueryWrapper<Announcement>().orderByDesc("publish_time")).getRecords();
    }
}