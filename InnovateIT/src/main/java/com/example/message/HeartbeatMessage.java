package com.example.message;

import java.nio.ByteBuffer;

public class HeartbeatMessage { //心跳
    public byte have_location;//是否有位置信息
    public long latitude;//纬度
    public long longitude;// 经度
    public long time;// 时间

    public byte getHave_location() {
        return have_location;
    }

    public void setHave_location(byte have_location) {
        this.have_location = have_location;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getTime() {
        return time;
    }

    public HeartbeatMessage(byte have_location, long latitude, long longitude, long time) {
        this.have_location = have_location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    public byte[] encode() {
        // 计算报文总长度，包括头部和报文体
        int totalLength =1+8+8+8;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);

       buffer.put(have_location);
       buffer.putLong(latitude);
       buffer.putLong(longitude);
       buffer.putLong(time);

        return buffer.array();
    }

    public static HeartbeatMessage decode(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        byte have_location = buffer.get();
        long latitude = buffer.getLong();
        long longitude = buffer.getLong();
        long time = buffer.getLong();

        return new HeartbeatMessage(have_location, latitude, longitude, time);
    }

}
