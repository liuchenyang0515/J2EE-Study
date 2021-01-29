package com.me.restful.controller;

import com.me.restful.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Controller
@RestController
// 默认将字符串向请求中输出，而不是页面跳转，就不需要每个uri命中的方法上加@ResponseBody了,作用就是为了简化开发
@RequestMapping("/restful")
// 服务提供端写允许哪些域名跨域
/**
 * 写了@CrossOrigin(origins = {"http://localhost:8080"})之后，前端看到的响应头多了如下内容
 * Access-Control-Allow-Origin: http://localhost:8080
 *
 * Vary: Origin
 * Vary: Access-Control-Request-Method
 * Vary: Access-Control-Request-Headers
 * 这是与跨域请求相关的响应头，看到Vary字段，浏览器会认为服务器允许跨域访问这些内容，就能解析响应内容
 * */
// 多个域名跨域请求@CrossOrigin(origins = {"http://localhost:8080","http://www.imooc.com"})
// 若允许所有，@CrossOrigin(origins = "*")，并不推荐
// @CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3600)，maxAge是对预检请求(非简单请求put/delete)结果进行缓存的时间，而不是对请求内容缓存
// 预检请求是问服务器当前请求是否允许访问，如果不允许，当前操作就中断了
// maxAge = 3600代表最大时间没超过3600秒，下次put/delete请求就不用发预检请求，直接发送实际请求即可，减轻服务器压力
// 否则put/delete每次都会发送 预检请求和实际请求
// 如果配置注解跨域设置，和全局跨域设置，最终以注解为准
//@CrossOrigin(origins = {"http://localhost:8080"})
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
    // 参数解析到bean对象，是调用空构造+setter方法
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

    // 如果写了@ResponseBody或者@RestController，并且返回的是对象，那么会jackson自动序列化发送到客户端
    @GetMapping("/person")
    public Person findByPersonId(Integer id) {
        Person p = new Person();
        if (id == 1) {
            p.setName("lily");
            p.setAge(23);
        } else if (id == 2) {
            p.setName("smith");
            p.setAge(22);
        }
        return p;
    }

    // 返回多条数据时，改为List，jackson自动序列化发送到客户端
    @GetMapping("/persons")
    public List<Person> findByPersonId() {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("lily");
        p1.setAge(23);
        p1.setBirthday(new Date());

        Person p2 = new Person();
        p2.setName("smith");
        p2.setAge(22);
        p2.setBirthday(new Date());

        list.add(p1);
        list.add(p2);
        return list;
    }
}
