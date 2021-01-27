package com.imooc.springmvc.controller;

import com.imooc.springmvc.entity.MyForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class FormController {
    //    @PostMapping("/apply")
    @ResponseBody
    // input里面的name没有n，取默认值
    public String apply(@RequestParam(value = "n", defaultValue = "ANON") String name, String course, Integer[] purpose) {
        System.out.println(name);
        System.out.println(course);
        for (Integer p : purpose) {
            System.out.println(p);
        }
        return "SUCCESS";
    }


    //    @PostMapping("/apply")
    @ResponseBody
    // 本来是Integer[]类型改为List<Integer>一定要写@RequestParam，非常推荐使用List接收前端复合数据
    public String apply(String name, String course, @RequestParam List<Integer> purpose) {
        System.out.println(name);
        System.out.println(course);
        for (Integer p : purpose) {
            System.out.println(p);
        }
        return "SUCCESS";
    }


    @PostMapping("/apply")
    @ResponseBody // 也可以用实体类去接收复合表单数据，极大减少开发工作量，需要掌握
    public String apply(MyForm form) {
        return "SUCCESS";
    }


    // 接收复合数据，Map有天生缺陷，复合数组数据会丢失，只能接收单个数据
//    @PostMapping("/apply")
    @ResponseBody // 只要包含数组数据，那么就只返回数组第一项，这就是缺陷，比如多选框选择了3项，那么它只保存第一项的序号，数据会丢失
    public String apply(@RequestParam Map map) {
        System.out.println(map);
        return "SUCCESS";
    }
}
