package com.imooc.spring.ioc.service;

import com.imooc.spring.ioc.dao.IUserDao;
import com.imooc.spring.ioc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Scope("prototype")//设置单例/多例,XML中 bean scope完全相同
public class UserService {
    @Value("${metaData}")//读取config.properties的metaData属性值
    private String metaData ;
    @Value("${connection.password}")
    private String password;
//
    public UserService(){
        System.out.println("正在创建UserService:" + this);
    }
//
    @PostConstruct //XML中bean init-method完全相同
    public void init(){
        System.out.println("初始化UserService对象,metaData=" + metaData);
//        System.out.println("初始化UserService对象");
    }

    //Spring IoC容器会自动通过反射技术将属性private修饰符自动改为public,直接进行赋值,然后改回到private
    //@Autowired写在属性上不执行set方法
    // 这里如果只是在属性上写装配注解，而类型是接口，直接报错
    // No qualifying bean of type 'com.imooc.spring.ioc.dao.IUserDao' available:
    // expected single matching bean but found 2: userDao,userOracleDao
    // 因为不清楚将哪个bean注入到udao中，所以报错
    // 可以让某一个bean不写注解，然后就不被IoC容器管理，或者在某个bean对象上写注解@Primary
    // 表示默认注入这个bean对象，解决了按类型装配的问题
    // 但是在实际开发中，少用这种@Autowired类型装配，都是用按名称装配的，因为名称是唯一的
//
    @Autowired
    private IUserDao udao;

    public IUserDao getUdao() {
        return udao;
    }

    // 实际开发中，用属性就能完成依赖注入，就不用写setXXX方法去装配了，装配注解写在属性上即可

//    //    @Autowired
//    //如果装配注解放在set方法上,则自动按类型/名称对set方法参数进行注入
//    public void setUdao(UserDao udao) {
//        System.out.println("setUdao:" + udao);
//        this.udao = udao;
//    }
}
