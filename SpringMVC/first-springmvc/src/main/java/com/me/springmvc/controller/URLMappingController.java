package com.me.springmvc.controller;

import com.me.springmvc.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/um") // 全局通用请求映射，就是get和post方法全局的访问前缀，get请求变为访问localhost/um/g
public class URLMappingController {

    /* @RequestMapping("/g") // 如果作用在方法上，不再区分get/post请求，但不推荐这么用，应该区分get/post
     * @RequestMapping("/g")等同于@RequestMapping(value = "/g")
     * @RequestMapping(value = "/g", method = RequestMethod.GET) 这也可以指定接收get请求，但这么写很麻烦
     * 所以就有了@GetMapping("/g")进行url绑定工作
     * */

    @GetMapping("/g")
    @ResponseBody
    /* http://localhost/um/g?manager_name=lily */
    // @RequestParam("manager_name"),自定义参数对照关系，请求中manager_name，动态注入到requestName，就不要求名称对应了
    // http://localhost/um/g?manager_name=lily&createTime=2021-02-01  测试第二个参数，自定义日期转换器生效, @DateTimeFormat会被忽略掉，自定义日期转换器优先级比 @DateTimeFormat高
    public String getMapping(@RequestParam("manager_name") String requestName, Date createTime) {
        System.out.println("requestName:" + requestName);
        return "this is get method";
    }

    @PostMapping("/p") // 这里用index.html表单演示
    @ResponseBody
    // 只要保证input的name值和这里的变量名对应，SpringMVC就自动注入，如果参数名不对应，则为null
    public String postMapping(String username, String password) {// 如果密码是纯数字，这里参数类型可以设置Long password，SpringMVC会自动转换
        // 之前servlet还需要request.getParameter来获取
        // SpringMVC就是为了简化web应用的开发难度
        System.out.println(username + ":" + password);
        return "this is post method";
    }

    @PostMapping("/p1")
    @ResponseBody
    // 遇到大表单，参数较多的情况，考虑使用bean对象接收参数
    // 第三个参数Date createTime，前端输入字符串日期会报错
    //  Failed to convert from type [java.lang.String] to type [java.util.Date] for value '2021-1-1';
    // 这时需要加上@DateTimeFormat(pattern = "yyyy-MM-dd")
    public String postMapping1(User user, String username, @DateTimeFormat(pattern = "yyyy-MM-dd") Date createTime) { // bean对象来接收客户端的数据，不管是实体类还是这里的参数，只要对应input的name值，会全部注入，比如有多少username就注入多少
        // SpringMBC发现接收方法的参数是实体类，会在实体类寻找同名参数，如果找到对应变量，则自动注入
        System.out.println(user.getUsername() + ":" + user.getPassword());
        return "This is post method";
    }
}