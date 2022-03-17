package com.example.restapi.dtos;

public class UserMsDto {
    private Long userId;
    private String username;
    private String emailAddress;
    private String roleName;

    public UserMsDto() {
    }

    public UserMsDto(Long userId, String username, String emailAddress, String roleName) {
        this.userId = userId;
        this.username = username;
        this.emailAddress = emailAddress;
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
