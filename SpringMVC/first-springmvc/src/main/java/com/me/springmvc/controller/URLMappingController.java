package com.me.springmvc.controller;

import com.me.springmvc.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        return "<h1>这是Post响应</h1>";
    }

    @GetMapping("/view")
    public ModelAndView showView(Integer userId) {
        // 默认ModelAndView使用请求转发(forward)至页面
//        ModelAndView mav = new ModelAndView("/view.jsp");
        ModelAndView mav = new ModelAndView();
        /*
            如果是view.jsp而不是/view.jsp，没有"/"代表相对路径，这里是@RequestMapping("/um") 是/um前缀
            http://localhost/um/[view?userId=1]，相对于[...]括号中的路径
            变为http://localhost/um/view.jsp（并带上这里的"lily"参数，直接访问是没有username参数的）
            相对于资源目录webapp的um目录下的view.jsp, 请注意区别
            但是并不推荐这么写，尽量用/开头的绝对路径
        */
        mav.setViewName("/view.jsp"); // 这个等同于直接在构造函数设置
        User user = new User();
        if (userId == 1) {
            user.setUsername("lily");
        } else if (userId == 2) {
            user.setUsername("smith");
        } else if (userId == 3) {
            user.setUsername("lina");
        }
        // mav.addObject()方法设置的属性默认存放在当前请求中，如果是重定向，那么客户端会发起新请求，地址发生了变化，user数据丢失
        // 重定向使用new ModelAndView("redirect:/index.jsp")
        mav.addObject("u", user);
        return mav;
    }

    // String与ModelMap替代ModelAndView也可以
    // Controller方法返回String的情况
    // 1.方法被@ResponseBody描述，SpringMVC直接响应String字符串本身
    // 2.方法不存在@ResponseBody, 则SpringMVC处理String指代的视图(页面)
    // http://localhost/um/view1?userId=1
    @GetMapping("/view1")
//    @ResponseBody
    // 这里没有@ResponseBody就和上面用ModelAndView结果一样
    public String showView1(Integer userId, ModelMap modelMap) {
        String view = "/um/view.jsp"; // 这里直接写出了资源目录绝对路径
        User user = new User();
        if (userId == 1) {
            user.setUsername("lily");
        } else if (userId == 2) {
            user.setUsername("smith");
        } else if (userId == 3) {
            user.setUsername("lina");
        }
        modelMap.addAttribute("u", user); // 代码功能和上面mav.addObject功能完全一样
        return view;
    }
}