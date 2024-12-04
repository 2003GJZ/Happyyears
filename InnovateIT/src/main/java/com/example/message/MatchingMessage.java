package com.example.message;

import java.nio.ByteBuffer;
public class MatchingMessage {//快速组队
    public byte success;// 0为匹配成功 1为匹配失败
    public byte type;//消息类型
    // 发起组队 0b 0000 0000
    // 响应组队 0b 0000 0001
    // 取消组队 0b 0000 0010

    public long launch;//发起人ID
    public long target;//系统匹配人ID

    public long launch_latitude;//发起人纬度
    public long llaunch_ongitude;//发起人经度

    public long target_latitude;//系统匹配人纬度
    public long target_ongitude;// 系统匹配人经度
    public MatchingMessage(byte success, byte type, long launch, long target, long launch_latitude, long llaunch_ongitude, long target_latitude, long target_ongitude) {
        this.success = success;
        this.type = type;
        this.launch = launch;
        this.target = target;
        this.launch_latitude = launch_latitude;
        this.llaunch_ongitude = llaunch_ongitude;
        this.target_latitude = target_latitude;
        this.target_ongitude = target_ongitude;
    }

    public byte[] encode() {
        // 计算报文总长度，包括头部和报文体
        int totalLength = 2 + 8 + 8 + 8 + 8 + 8 + 8;// 假设报文总长度为2+8+8+8+8+8+8
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);

        buffer.put(success);
        buffer.put(type);
        buffer.putLong(launch);
        buffer.putLong(target);
        buffer.putLong(launch_latitude);
        buffer.putLong(llaunch_ongitude);
        buffer.putLong(target_latitude);
        buffer.putLong(target_ongitude);
        return buffer.array();
    }
    public static  MatchingMessage decode(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);

        byte success = buffer.get();
        byte type = buffer.get();
        long launch = buffer.getLong();
        long target = buffer.getLong();
        long launch_latitude = buffer.getLong();
        long llaunch_ongitude = buffer.getLong();
        long target_latitude = buffer.getLong();
        long target_ongitude = buffer.getLong();
        return new MatchingMessage(success, type, launch, target, launch_latitude, llaunch_ongitude, target_latitude, target_ongitude);

    }


}
