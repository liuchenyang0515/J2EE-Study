package com.imooc.spring.aop.dao;

/**
 * 用户表Dao
 */
public class UserDao {
    public String insert(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("新增用户数据");
        return "insert()故意返回String字符串测试环绕通知";
    }
}
