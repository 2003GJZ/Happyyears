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
public class Activity extends ToGson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;//活动id

    private String title;//活动标题

    private String description;//活动描述

    private long startDate;//活动开始时间

    private long endDate;//活动结束时间

    private String organizer;//活动主办方

    public Long userid; // 主办方ID

    private String type;//活动类型

    private String status;//活动状态

    private String location;//活动地点

    private Double longitude;//活动地点经度

    private Double latitude;//活动地点纬度

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Activity() {
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id = " + id +
                ", title = " + title +
                ", description = " + description +
                ", startDate = " + startDate +
                ", endDate = " + endDate +
                ", organizer = " + organizer +
                ", type = " + type +
                ", status = " + status +
                ", location = " + location +
                ", longitude = " + longitude +
                ", latitude = " + latitude +
                "}";
    }
}
