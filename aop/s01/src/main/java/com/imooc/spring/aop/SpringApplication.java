package com.imooc.spring.aop;

import com.imooc.spring.aop.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.createUser();
        userService.generateRandomPassword("MD5", 16);
        // 此时演示异常通知，手动制造运行时异常
    }
}
