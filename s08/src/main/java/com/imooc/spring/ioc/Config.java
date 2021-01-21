package com.imooc.spring.ioc;

import com.imooc.spring.ioc.controller.UserController;
import com.imooc.spring.ioc.dao.EmployeeDao;
import com.imooc.spring.ioc.dao.UserDao;
import com.imooc.spring.ioc.service.UserService;
import org.springframework.context.annotation.*;

@Configuration //当前类是一个配置类,用于替代applicationContext.xml
@ComponentScan(basePackages = "com.imooc") // <context:component-scan base-package="com.imooc"/>
public class Config {
    @Bean //Java Config利用方法创建对象,将方法返回对象放入容器,beanId=方法名
    public UserDao userDao(){
        UserDao userDao = new UserDao();
        System.out.println("已创建" + userDao);
        return userDao;
    }

    @Bean //Java Config利用方法创建对象,将方法返回对象放入容器,beanId=方法名
    @Primary
    public UserDao userDao1(){
        UserDao userDao = new UserDao();
        System.out.println("已创建" + userDao);
        return userDao;
    }

    @Bean
    //先按name尝试注入,name不存在则按类型注入，比如将userDao改为udao，但是属性中没有udao，按类型注入
    // 如果有多个这样的bean对象按类型注入就会出问题了，必须采取进一步措施，比如@Primary
    public UserService userService(UserDao udao, EmployeeDao employeeDao){
        UserService userService = new UserService();
        System.out.println("已创建" + userService);
        userService.setUserDao(udao);
        System.out.println("调用setUserDao:" + udao);
        userService.setEmployeeDao(employeeDao);
        return userService;
    }

    @Bean //<bean id="xxx" clas="xxx">
    @Scope("prototype")
    public UserController userController(UserService userService){
        UserController userController = new UserController();
        System.out.println("已创建" + userController);
        userController.setUserService(userService);
        System.out.println("调用setUserService:" + userService);
        return userController;
    }
}
