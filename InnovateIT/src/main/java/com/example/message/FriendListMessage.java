package com.example.message;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class FriendListMessage {
    public static class Friend {
        public Long userId; // 用户ID
        public String username; // 用户名
        public String avatarUrl; // 头像URL
        public boolean onlineStatus; // 在线状态
        public long lastOnlineTime; // 最后在线时间
        public String relationship; // 好友关系
        public long addedTime; // 添加时间
        public List<String> commonInterests; // 共同兴趣

        public Friend(Long userId, String username, String avatarUrl, boolean onlineStatus, long lastOnlineTime, String relationship, long addedTime, List<String> commonInterests) {
            this.userId = userId;
            this.username = username;
            this.avatarUrl = avatarUrl;
            this.onlineStatus = onlineStatus;
            this.lastOnlineTime = lastOnlineTime;
            this.relationship = relationship;
            this.addedTime = addedTime;
            this.commonInterests = commonInterests;
        }

        // 编码好友消息为字节数据
        public byte[] encode() {
            int totalLength = 8 + 4 + username.getBytes().length + 4 + avatarUrl.getBytes().length + 1 + 8 + 1 +
                    8 + 1 + relationship.getBytes().length + 8 + 4; // 这几个字节分别表示
            for (String interest : commonInterests) {
                totalLength += 4 + interest.getBytes().length;
            }
            ByteBuffer buffer = ByteBuffer.allocate(totalLength);
            // 用户ID
            buffer.putLong(userId);
            // 用户名
            buffer.putInt(username.getBytes().length);
            buffer.put(username.getBytes());
            // 头像URL
            buffer.putInt(avatarUrl.getBytes().length);
            buffer.put(avatarUrl.getBytes());
            // 在线状态
            buffer.put((byte) (onlineStatus ? 1 : 0));
            // 最后在线时间
            buffer.putLong(lastOnlineTime);
            // 好友关系
            buffer.put((byte) relationship.getBytes().length);
            buffer.put(relationship.getBytes());
            // 添加时间
            buffer.putLong(addedTime);
            // 共同兴趣数量
            buffer.putInt(commonInterests.size());
            // 共同兴趣
            for (String interest : commonInterests) {
                buffer.putInt(interest.getBytes().length);
                buffer.put(interest.getBytes());
            }

            return buffer.array();
        }

        // 解码字节数据为好友消息
        public static Friend decode(byte[] data) {
            ByteBuffer buffer = ByteBuffer.wrap(data);

            Long userId = buffer.getLong();

            int usernameLength = buffer.getInt();
            byte[] usernameBytes = new byte[usernameLength];
            buffer.get(usernameBytes);
            String username = new String(usernameBytes);

            int avatarUrlLength = buffer.getInt();
            byte[] avatarUrlBytes = new byte[avatarUrlLength];
            buffer.get(avatarUrlBytes);
            String avatarUrl = new String(avatarUrlBytes);

            boolean onlineStatus = buffer.get() == 1;
            long lastOnlineTime = buffer.getLong();

            int relationshipLength = buffer.get();
            byte[] relationshipBytes = new byte[relationshipLength];
            buffer.get(relationshipBytes);
            String relationship = new String(relationshipBytes);

            long addedTime = buffer.getLong();

            List<String> commonInterests = new ArrayList<>();
            int interestsCount = buffer.getInt();
            for (int i = 0; i < interestsCount; i++) {
                int interestLength = buffer.getInt();
                byte[] interestBytes = new byte[interestLength];
                buffer.get(interestBytes);
                commonInterests.add(new String(interestBytes));
            }

            return new Friend(userId, username, avatarUrl, onlineStatus, lastOnlineTime, relationship, addedTime, commonInterests);
        }
    }

    private List<Friend> friends;

    public FriendListMessage(List<Friend> friends) {
        this.friends = friends;
    }

    // 编码好友列表消息为字节数据
    public byte[] encode() {
        int totalLength = 4;
        for (Friend friend : friends) {
            totalLength += friend.encode().length;
        }
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        // 好友数量
        buffer.putInt(friends.size());
        // 好友列表
        for (Friend friend : friends) {
            buffer.put(friend.encode());
        }

        return buffer.array();
    }

    // 解码字节数据为好友列表消息
    public static FriendListMessage decode(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);

        List<Friend> friends = new ArrayList<>();
        int friendsCount = buffer.getInt();
        for (int i = 0; i < friendsCount; i++) {
            int friendDataLength = buffer.getInt();
            byte[] friendData = new byte[friendDataLength];
            buffer.get(friendData);
            friends.add(Friend.decode(friendData));
        }

        return new FriendListMessage(friends);
    }
}