package com.me.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String getMapping() {
        return "this is get method";
    }

    @PostMapping("/p") // 这里用index.html表单演示
    @ResponseBody
    public String postMapping() {
        return "this is post method";
    }
}
