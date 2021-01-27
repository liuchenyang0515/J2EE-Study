package com.me.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 可以看成原生Serlvet的替代品
public class TestController {
    @GetMapping("/t")  // locahost/t,拦截到get请求触发的方法，将test()方法绑定到"/t"
    @ResponseBody   // 直接向响应输出字符串数据，不跳转页面
    public String test() {
        return "Hello SpringMVC";
    }
}
