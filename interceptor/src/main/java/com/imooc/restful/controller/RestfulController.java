package com.imooc.restful.controller;

import com.imooc.restful.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/restful")
//@CrossOrigin(origins = {"http://localhost:8080","http://www.imooc.com"})
//@CrossOrigin(origins = "*",maxAge = 3600)
public class RestfulController {
    @GetMapping("/request")
    //@ResponseBody
    public String doGetRequest(){
        return "{\"message\":\"返回查询结果\"}";
    }

    // POST /article/1
    // POST /restful/request/100
    @PostMapping("/request/{rid}")
    //@ResponseBody
    public String doPostRequest(@PathVariable("rid") Integer requestId, Person person){
        System.out.println(person.getName() + ":" + person.getAge());
        return "{\"message\":\"数据新建成功\",\"id\":" + requestId + "}";
    }

    @PutMapping("/request")
    //@ResponseBody
    public String doPutRequest(Person person){
        System.out.println(person.getName() + ":" + person.getAge());
        return "{\"message\":\"数据更新成功\"}";
    }

    @DeleteMapping("/request")
    //@ResponseBody
    public String doDeleteRequest(){
        return "{\"message\":\"数据删除成功\"}";
    }

    @GetMapping("/person")
    public Person findByPersonId(Integer id){
        Person p = new Person();
        if(id==1){
            p.setName("lily");
            p.setAge(23);
        }else if(id==2){
            p.setName("smith");
            p.setAge(22);
        }
        return p;
    }

    @GetMapping("/persons")
    public List<Person> findPersons(){
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
        System.out.println("RestfulController.findPersons() - return list");
        return list;
    }

}
