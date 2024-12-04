package com.example.system.entity;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
public class Announcement extends ToGson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;//公告ID

    private String title;//公告标题

    private Long authorId;//公告作者ID

    private String content;//公告内容

    private String visibility;//公告可见性

    private String priority;//公告优先级

    private String status;//公告状态

    private long publishTime;//公告发布时间

    private long validityPeriodStart;//公告有效期开始时间

    private long validityPeriodEnd;//公告有效期结束时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public long getValidityPeriodStart() {
        return validityPeriodStart;
    }

    public void setValidityPeriodStart(long validityPeriodStart) {
        this.validityPeriodStart = validityPeriodStart;
    }

    public long getValidityPeriodEnd() {
        return validityPeriodEnd;
    }

    public void setValidityPeriodEnd(long validityPeriodEnd) {
        this.validityPeriodEnd = validityPeriodEnd;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id = " + id +
                ", title = " + title +
                ", authorId = " + authorId +
                ", content = " + content +
                ", visibility = " + visibility +
                ", priority = " + priority +
                ", status = " + status +
                ", publishTime = " + publishTime +
                ", validityPeriodStart = " + validityPeriodStart +
                ", validityPeriodEnd = " + validityPeriodEnd +
                "}";
    }
}
