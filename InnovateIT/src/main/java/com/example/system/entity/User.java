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
public class User extends ToGson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;
    private Integer age;
    private String gender;

    private String email;

    private String phoneNumber;

    private String location;

    private String idCardInfo;

    private String loginStatus;

    private String loggedDevices;//设备记录

    private String userType;

    private String interests;//爱好

    private String avatarUrl;

    private Integer isDeleted;

    public User(String number, String john, String doe) {

    }

    public User(long l) {
        this.id = l;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdCardInfo() {
        return idCardInfo;
    }

    public void setIdCardInfo(String idCardInfo) {
        this.idCardInfo = idCardInfo;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoggedDevices() {
        return loggedDevices;
    }

    public void setLoggedDevices(String loggedDevices) {
        this.loggedDevices = loggedDevices;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public User() {
    }

    public User(Long id, String username, String password, Integer age, String gender, String email, String phoneNumber, String location, String idCardInfo, String loginStatus, String loggedDevices, String userType, String interests, String avatarUrl, Integer isDeleted) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.idCardInfo = idCardInfo;
        this.loginStatus = loginStatus;
        this.loggedDevices = loggedDevices;
        this.userType = userType;
        this.interests = interests;
        this.avatarUrl = avatarUrl;
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + location + '\'' +
                ", idCardInfo='" + idCardInfo + '\'' +
                ", loginStatus='" + loginStatus + '\'' +
                ", loggedDevices='" + loggedDevices + '\'' +
                ", userType='" + userType + '\'' +
                ", interests='" + interests + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
