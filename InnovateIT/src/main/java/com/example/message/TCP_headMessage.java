package com.example.message;

import java.nio.ByteBuffer;

public class TCP_headMessage {
    // TCP报文头部分字段
    public byte magic; //魔数
    public byte version; //协议版本
    public byte number; // 编码
    public byte type; //何种操作
//    0b 0000 0000   登录验证
//    0b 0000 0001   聊天
//    0b 0000 0010   群聊
//    0b 0000 0011   活动
//    0b 0000 0100   公告
//    0b 0000 0101   验证消息
//    0b 0000 0110   好友详细信息（基本信息）
//    0b 0000 0111   用户列表（用于好友列表，和群成员）
//    0b 0000 1111   心跳

//    0b 0001 0000   快速组队
//    0b 0001 0001   更改系统公告
//    0b 0001 0010   删除系统公告
//    0b 0001 0011
//    0b 0001 0100
//    0b 0001 0101
//    0b 0001 0110


    public byte exception; //错误的类型 0无错误
//    0b 0000 0000   无错误
//    0b 0000 0001   密码错误
//    0b 0000 0010   验证信息过期或错误
//    0b 0000 0011   异常账号
//    0b 0000 0100   异常ip
//    0b 0000 0101
//    0b 0000 0110
//    0b 0000 0111
//    0b 0000 1111  版本异常或已经停用

    public long userId;//用户id
    public byte tokenLength; // 令牌长度max为255
    public byte[] token; // 令牌

    // TCP报文体
    public String messageBody; // 报文体内容

    public TCP_headMessage(byte magic, byte version, byte number, byte type, byte exception, long userId, byte[] token, String messageBody) {
        this.magic = magic;
        this.version = version;
        this.number = number;
        this.type = type;
        this.exception = exception;
        this.userId = userId;
        this.token = token;
        this.tokenLength = (byte) token.length;
        this.messageBody = messageBody;
    }

    // 编码TCP报文为字节数据
    public byte[] encode() {
        // 计算报文总长度，包括头部和报文体
        int totalLength = 6 + 8 + token.length + 4 + messageBody.getBytes().length;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);

        buffer.put(magic); //魔数
        buffer.put(version);
        buffer.put(number);
        buffer.put(type);
        buffer.put(exception);
        buffer.putLong(userId); // 存储用户ID
        buffer.put(tokenLength);
        buffer.put(token);
        buffer.putInt(messageBody.getBytes().length);
        buffer.put(messageBody.getBytes());
        return buffer.array();
    }


    // 解码字节数据为TCP报文
    public static TCP_headMessage decode(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        byte magic = buffer.get();
        byte version = buffer.get();
        byte number = buffer.get();
        byte type = buffer.get();
        byte exception = buffer.get();
        long userId = buffer.getLong(); // 读取用户ID字段
        byte tokenLength = buffer.get();
        byte[] token = new byte[tokenLength];
        buffer.get(token);
        // 解析报文体`
        int messageBodyLength = buffer.getInt();
        // 读取报文体
        byte[] messageBodyBytes = new byte[messageBodyLength];
        buffer.get(messageBodyBytes);
        String messageBody = new String(messageBodyBytes);

        return new TCP_headMessage(magic, version, number, type, exception, userId, token, messageBody);
    }
}