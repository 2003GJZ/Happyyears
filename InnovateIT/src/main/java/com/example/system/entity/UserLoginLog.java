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
public class UserLoginLog extends ToGson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long logId;

    private Long userId;

    private String deviceIdentifier;

    private long loginTime;

    private long logoutTime;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceIdentifier() {
        return deviceIdentifier;
    }

    public void setDeviceIdentifier(String deviceIdentifier) {
        this.deviceIdentifier = deviceIdentifier;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public long getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(long logoutTime) {
        this.logoutTime = logoutTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "UserLoginLog{" +
                "logId = " + logId +
                ", userId = " + userId +
                ", deviceIdentifier = " + deviceIdentifier +
                ", loginTime = " + loginTime +
                ", logoutTime = " + logoutTime +
                "}";
    }

}
