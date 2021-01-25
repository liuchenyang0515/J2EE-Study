package com.me.spring.aop;

import com.me.spring.aop.service.UserService;
import com.me.spring.aop.service.UserServiceImpl;
import com.me.spring.aop.service.UserServiceProxy;
import com.me.spring.aop.service.UserServiceProxy1;

public class Application {
    public static void main(String[] args) {
        // 二手代理，类似套娃
        UserService userService = new UserServiceProxy1(new UserServiceProxy(new UserServiceImpl()));
        userService.createUser();
    }
}
