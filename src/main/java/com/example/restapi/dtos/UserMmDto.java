package com.example.restapi.dtos;

import com.example.restapi.entities.Order;

import java.util.List;

public class UserMmDto {
    private Long userId;
    private String username;
    private String firstname;
    private List<Order> order;

    public UserMmDto() {
    }

    public UserMmDto(Long userId, String username, String firstname, List<Order> order) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.order = order;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "UserMmDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", order=" + order +
                '}';
    }
}
