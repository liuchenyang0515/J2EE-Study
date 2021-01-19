package com.imooc.spring.ioc.bookshop.dao;

public class BookDaoOracleImpl implements BookDao {
    public void insert() {
        System.out.println("向Oracle Book表插入一条数据");
    }
}
