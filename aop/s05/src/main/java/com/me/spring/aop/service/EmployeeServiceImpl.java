package com.me.spring.aop.service;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public void createEmployee() {
        System.out.println("执行创建员工业务逻辑");
    }
}
