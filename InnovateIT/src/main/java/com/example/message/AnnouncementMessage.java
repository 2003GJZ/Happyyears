package com.example.message;

import java.nio.ByteBuffer;
public class AnnouncementMessage {
    public long senderId; // 发送者ID
    public long subscriptionId; // 订阅ID  0表示所有人
    public byte type; // 公告类型
    //0b 0000 0000   推送公告
    //0b 0000 0001   更改公告
    //0b 0000 0010   删除公告
    //0b 0000 0011
    //0b 0000 0100
    //0b 0000 0101
    //0b 0000 0110
    //0b 0000 0111
    //0b 0000 1111


    //0b 0001 0000   推送系统公告
    //0b 0001 0001   更改系统公告
    //0b 0001 0010   删除系统公告
    //0b 0001 0011
    //0b 0001 0100
    //0b 0001 0101
    //0b 0001 0110
    public Long AnnouncementID; // 公告ID，更改删除时候带上，发布时推送给客户端
    public long startTime; // 公告开始时间
    public long endTime; // 公告结束时间
    public String announcement; // 公告内容

    public AnnouncementMessage(long senderId, byte type, Long AnnouncementID, String announcement, long startTime, long endTime) {
        this.senderId = senderId;
        this.type = type;
        this.AnnouncementID = AnnouncementID;
        this.announcement = announcement;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public long getSenderId() {
        return senderId;
    }

    public byte getType() {
        return type;
    }

    public Long getAnnouncementID() {
        return AnnouncementID;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }


    public void setType(byte type) {
        this.type = type;
    }

    public void setAnnouncementID(Long AnnouncementID) {
        this.AnnouncementID = AnnouncementID;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    // 编码公告消息为字节数据
    public byte[] encode() {
        int totalLength = 32 +4+ announcement.getBytes().length; //  32字节用于除公告以外的所有字段,4字节用于表示公告内容的长度
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);

        buffer.putLong(senderId);
        buffer.put(type);
        buffer.putLong(AnnouncementID);
        buffer.putLong(startTime);
        buffer.putLong(endTime);
        buffer.putInt(announcement.getBytes().length);
        buffer.put(announcement.getBytes());

        return buffer.array();
    }

    // 解码字节数据为公告消息
    public static AnnouncementMessage decode(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);

        long senderId = buffer.getLong();
        byte type = buffer.get();
        Long AnnouncementID = buffer.getLong();
        long startTime = buffer.getLong();
        long endTime = buffer.getLong();
        int announcementLength = buffer.getInt();
        byte[] announcementBytes = new byte[announcementLength];
        buffer.get(announcementBytes);

        String announcement = new String(announcementBytes);

        return new AnnouncementMessage(senderId, type, AnnouncementID, announcement, startTime, endTime);
    }
}