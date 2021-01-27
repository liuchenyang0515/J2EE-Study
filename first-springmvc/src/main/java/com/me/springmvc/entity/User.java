package com.me.springmvc.entity;

public class User {
    private String username; // 这里成员变量名必须和input的name属性值对应
    private Long password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }
}
