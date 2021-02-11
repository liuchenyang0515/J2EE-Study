package com.me.springbootlearn;

import org.springframework.web.bind.annotation.*;

/**
 * 描述：演示各种传参形式
 */
@RestController
@RequestMapping("/prefix") // 每个接口有的前缀
public class ParaController {
    // 或者@GetMapping("/firstrequest")
    @GetMapping({"/firstrequest"})
    public String firstRequest() {
        return "第一个Spring Boot接口";
    }

    /**
     * 正确示范：http://localhost:8080/requestpara?num=2
     * 如果不传参http://localhost:8080/requestpara
     * 就会报错，这里演示出错情况
     * There was an unexpected error (type=Bad Request, status=400).
     * Required Integer parameter 'num' is not present
     * @param num
     * @return
     */
    @GetMapping({"/requestpara"})
    public String requestpara(@RequestParam Integer num) {
        return "para from request: " + num;
    }


    /**
     * 正确示范：http://localhost:8080/pathpara/2
     * @param num
     * @return
     */
    @GetMapping({"/pathpara/{num}"})
    public String pathpara(@PathVariable("num") Integer num) {
        return "para from path: " + num;
    }

    // 多个url，同一个方法处理
    @GetMapping({"/multiurl1", "/multiurl2"})
    public String multiurl(@RequestParam Integer num) {
        return "para from path: " + num;
    }

    // 如果不传参，就有默认值，默认值是字符串形式给出，虽然传递进去时Integer，但也要字符串
    @GetMapping({"/required"})
    public String required(@RequestParam(required = false, defaultValue = "0") Integer num) {
        return "para from request: " + num;
    }
}
