package com.example.system.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author biwang
 * @since 2024-03-29
 */
public class Friend extends ToGson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long friendId;

    private String status;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    @Override
    public String toString() {
        return "Friend{" +
            "id = " + id +
            ", userId = " + userId +
            ", friendId = " + friendId +
            ", status = " + status +
            ", createdAt = " + createdAt +
        "}";
    }
}
