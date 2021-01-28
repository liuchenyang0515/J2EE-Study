package com.me.springmvc.controller;

import com.me.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fm")
public class FreemarkerController {
    @GetMapping("/test")
    public ModelAndView showTest() {
        ModelAndView mav = new ModelAndView("/test"); // 不用增加扩展名.ftl，配置文件已配置
        User user = new User();
        user.setUsername("andy");
        mav.addObject("u", user);
        return mav;
    }
}
