package com.imooc.spring.ioc.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class UserOracleDao implements IUserDao {
    public UserOracleDao(){
        System.out.println("正在创建UserOracleDao:" + this);
    }
}
