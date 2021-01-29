package com.me.restful.controller;

import com.me.restful.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
// 默认将字符串向请求中输出，而不是页面跳转，就不需要每个uri命中的方法上加@ResponseBody了,作用就是为了简化开发
@RequestMapping("/restful")
public class RestfulController {
    @GetMapping("/request")
//    @ResponseBody
    public String doGetRequest() {
        return "{\"message\":\"返回查询结果\"}";
    }

    // POST /restful/request/100，这里路径变量100赋给了rid
    // 路径变量：URI中可变的数值
    @PostMapping("/request/{rid}") // uri相同，请求方式不同，一个post一个get，并不冲突
//    @ResponseBody
    public String doPostRequest(@PathVariable("rid") Integer requestId, Person person) { // rid注入到requestId
        System.out.println(person.getName() + ":" + person.getAge());
        return "{\"message\":\"数据新建成功\",\"id\":" + requestId + "}";
    }

    @PutMapping("/request")
//    @ResponseBody
    public String doPutRequest(Person person) {
        System.out.println(person.getName() + ":" + person.getAge());
        return "{\"message\":\"数据更新成功\"}";
    }


    @DeleteMapping("/request")
//    @ResponseBody
    public String doDeleteRequest() {
        return "{\"message\":\"数据删除成功\"}";
    }
}
