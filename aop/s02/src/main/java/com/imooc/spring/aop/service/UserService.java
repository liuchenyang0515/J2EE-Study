package com.imooc.spring.aop.service;

import com.imooc.spring.aop.dao.UserDao;

/**
 * 用户服务
 */
public class UserService {
    private UserDao userDao;

    public void createUser() {
        try {
            Thread.sleep(2000); // 为了触发环绕通知
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行员工入职业务逻辑");
        userDao.insert(); // 为了测试环绕通知proceedingJoinPoint.proceed()返回值
        generateRandomPassword("MD5", 16); // 为了测试环绕通知proceedingJoinPoint.proceed()返回值
    }

    public String generateRandomPassword(String type, Integer length) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("按" + type + "方式生成" + length + "位随机密码");
        return "Zxcquei1";
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
