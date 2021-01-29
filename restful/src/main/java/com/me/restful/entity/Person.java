package com.me.restful.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Person {
    private String name;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss SSS", timezone = "GMT+8")
    // 将日期类型按照格式输出，否则默认输出1970到现在的毫秒数;不写timezone则是格林威治时间，不是北京时间，比北京时间晚8小时
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
