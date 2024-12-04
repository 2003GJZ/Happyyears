package com.example.message;

import java.nio.ByteBuffer;
public class ChatMessage {
    public long senderId; // 发送者ID
    public long receiverId; // 接收者ID
    public long SendingTime; // 发送时间
    public String message; // 聊天消息内容

    public ChatMessage(long senderId, long receiverId, long SendingTime,String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.SendingTime = SendingTime;
        this.message = message;
    }

    public long getSendingTime() {
        return SendingTime;
    }

    public void setAddedTime(long SendingTime) {
        this.SendingTime = SendingTime;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // 编码聊天消息为字节数据
    public byte[] encode() {
        int totalLength = 16+4+ message.getBytes().length; // 16字节用于senderId、receiverId和消息长度字段
        //  4个字节的，消息长度字段用于存储消息长度
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);

        buffer.putLong(senderId);
        buffer.putLong(receiverId);
        buffer.putLong(SendingTime);
        buffer.putInt(message.getBytes().length);//长度
        buffer.put(message.getBytes());
        return buffer.array();
    }

    // 解码字节数据为聊天消息
    public static ChatMessage decode(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);

        long senderId = buffer.getLong();
        long receiverId = buffer.getLong();
        long SendingTime = buffer.getLong();
        int messageLength = buffer.getInt();
        byte[] messageBytes = new byte[messageLength];
        buffer.get(messageBytes);
        String message = new String(messageBytes);

        return new ChatMessage(senderId, receiverId, SendingTime,message);
    }
}