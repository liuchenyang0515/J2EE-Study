package com.imooc.spring.ioc.service;

import com.imooc.spring.ioc.dao.IUserDao;
import com.imooc.spring.ioc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
//@Scope("prototype")//设置单例/多例,XML中 bean scope完全相同
public class UserService {
    @Value("${metaData}")//读取config.properties的metaData属性值
    private String metaData ;
    @Value("${connection.password}")
    private String password;

    public UserService(){
        System.out.println("正在创建UserService:" + this);
    }

    @PostConstruct //XML中bean init-method完全相同
    public void init(){
        System.out.println("初始化UserService对象,metaData=" + metaData);
    }

    //Spring IoC容器会自动通过反射技术将属性private修饰符自动改为public,直接进行赋值
    //不再执行set方法
    @Autowired
    private IUserDao udao ;

    public IUserDao getUdao() {
        return udao;
    }


    /*@Autowired
    //如果装配注解放在set方法上,则自动按类型/名称对set方法参数进行注入
    public void setUdao(UserDao udao) {
        System.out.println("setUdao:" + udao);
        this.udao = udao;
    }*/
}
