package com.imooc.spring.ioc;

import com.imooc.spring.ioc.context.ApplicationContext;
import com.imooc.spring.ioc.context.ClassPathXmlApplicationContext;
import com.imooc.spring.ioc.entity.Apple;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Apple apple = (Apple)context.getBean("sweetApple");
        System.out.println(apple);
    }
}
