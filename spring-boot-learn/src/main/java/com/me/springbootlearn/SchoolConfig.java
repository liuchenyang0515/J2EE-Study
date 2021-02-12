package com.me.springbootlearn;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述：School配置类
 */
@Component
@ConfigurationProperties(prefix = "school")
public class SchoolConfig {
    // 这里不能加上private，否则编译器都过不了编译
    Integer grade;
    Integer classnum;

    // 必须要写getter和setter，否则读不到值，为null
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getClassnum() {
        return classnum;
    }

    public void setClassnum(Integer classnum) {
        this.classnum = classnum;
    }
}
