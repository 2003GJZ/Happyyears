package com.example.message;

import java.nio.ByteBuffer;


public class ActivityMessage {
    public byte type; //何种操作
    //    0b 0000 0000   增加活动
    //    0b 0000 0001   取消活动
    //    0b 0000 0010   推送活动
    //    0b 0000 0011   查询活动

    public Long activityId; // 活动ID
    public Long userId; // 主办方ID
    public String organizer; // 活动主办方
    public String title; // 活动标题
    public String location; // 活动地点
    public String activityType; // 活动类型
    public String activityDetails; // 活动详情
    public long startTime; // 活动开始时间
    public long endTime; // 活动结束时间
    public double latitude; // 纬度
    public double longitude; // 经度
    public String additionalProperty; // 其他属性

    public ActivityMessage(byte type, Long activityId, Long userId, String organizer, String title, String location,
                           String activityType, String activityDetails, long startTime, long endTime, String additionalProperty,
                           double latitude, double longitude) {
        this.type = type;
        this.activityId = activityId;
        this.userId = userId;
        this.organizer = organizer;
        this.title = title;
        this.location = location;
        this.activityType = activityType;
        this.activityDetails = activityDetails;
        this.startTime = startTime;
        this.endTime = endTime;
        this.additionalProperty = additionalProperty;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ActivityMessage() {
    }

    // 编码活动消息为字节数据
    public byte[] encode() {
        int totalLength = 1 + 8 + 8 + 4 + organizer.getBytes().length + 4 + title.getBytes().length + 4 + location.getBytes().length + 4 + activityType.getBytes().length + 4 + activityDetails.getBytes().length +
                8 + 8 + 4 + additionalProperty.getBytes().length+16; // 这几个字节分别表示
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        //活动信息属性
        buffer.put(type);
        // 活动ID
        buffer.putLong(activityId);
        // 主办方ID
        buffer.putLong(userId);
        // 活动主办方
        buffer.putInt(organizer.getBytes().length);
        buffer.put(organizer.getBytes());
        // 活动标题
        buffer.putInt(title.getBytes().length);
        buffer.put(title.getBytes());
        // 活动地点
        buffer.putInt(location.getBytes().length);
        buffer.put(location.getBytes());
        // 活动类型
        buffer.putInt(activityType.getBytes().length);
        buffer.put(activityType.getBytes());
        // 活动详情
        buffer.putInt(activityDetails.getBytes().length);
        buffer.put(activityDetails.getBytes());
        // 活动开始时间
        buffer.putLong(startTime);
        // 活动结束时间
        buffer.putLong(endTime);
        // 其他属性
        buffer.putInt(additionalProperty.getBytes().length);
        buffer.put(additionalProperty.getBytes());
        buffer.putDouble(latitude);
        buffer.putDouble(longitude);


        return buffer.array();
    }

    // 解码字节数据为活动消息
    public static ActivityMessage decode(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);

        byte type = buffer.get();
        long activityId = buffer.getLong();
        long userId = buffer.getLong();

        int organizerLength = buffer.getInt();
        byte[] organizerBytes = new byte[organizerLength];
        buffer.get(organizerBytes);
        String organizer = new String(organizerBytes);

        int titleLength = buffer.getInt();
        byte[] titleBytes = new byte[titleLength];
        buffer.get(titleBytes);
        String title = new String(titleBytes);

        int locationLength = buffer.getInt();
        byte[] locationBytes = new byte[locationLength];
        buffer.get(locationBytes);
        String location = new String(locationBytes);

        int activityTypeLength = buffer.getInt();
        byte[] activityTypeBytes = new byte[activityTypeLength];
        buffer.get(activityTypeBytes);
        String activityType = new String(activityTypeBytes);

        int activityDetailsLength = buffer.getInt();
        byte[] activityDetailsBytes = new byte[activityDetailsLength];
        buffer.get(activityDetailsBytes);
        String activityDetails = new String(activityDetailsBytes);

        long startTime = buffer.getLong();
        long endTime = buffer.getLong();

        int additionalPropertyLength = buffer.getInt();
        byte[] additionalPropertyBytes = new byte[additionalPropertyLength];
        buffer.get(additionalPropertyBytes);
        String additionalProperty = new String(additionalPropertyBytes);
        double latitude = buffer.getDouble();
        double longitude = buffer.getDouble();

        return new ActivityMessage(type, activityId, userId, organizer, title, location, activityType, activityDetails, startTime, endTime, additionalProperty,latitude,longitude);
    }
}