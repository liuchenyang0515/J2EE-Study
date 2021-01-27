package com.imooc.spring.ioc.service;

import com.imooc.spring.ioc.dao.IUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DepartmentService {
    /**
     * 1. @Resource设置name属性,则按name在IoC容器中将bean注入
     * 2. @Resource未设置name属性
     * 2.1 以属性名作为bean name在IoC容器中匹配bean,如有匹配则注入
     * 2.2 按属性名未匹配,则按类型进行匹配,同@Autowired,需加入@Primary解决类型冲突
     * 使用建议:在使用@Resource对象时推荐设置name或保证属性名与beanId名称一致
     * 而beanId默认是对应bean对象类名首字母小写
     */
//    @Resource(name = "userOracleDao")
//    private IUserDao udao;
    // =====等同于下面
//    @Resource
//    private IUserDao userOracleDao; // 其实相当于private IUserDao userOracleDao = new UserOracleDao();

    @Resource
    private IUserDao userDao;

    public void joinDepartment(){
        System.out.println(userDao);
    }
}
