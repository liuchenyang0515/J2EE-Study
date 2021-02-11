package com.me.springbootlearn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：演示各种传参形式
 */
@RestController
public class ParaController {
    // 或者@GetMapping("/firstrequest")
    @GetMapping({"/firstrequest"})
    public String firstRequest() {
        return "第一个Spring Boot接口";
    }
}
