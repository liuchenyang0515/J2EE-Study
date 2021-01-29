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
