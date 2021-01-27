package com.me.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String getMapping(@RequestParam("manager_name") String requestName) {
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
}