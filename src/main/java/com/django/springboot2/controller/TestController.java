package com.django.springboot2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;

/**
 * @ClassName TestController
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 * @Description 框架接口测试类
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping(value = "/hello")
    String home() {
        return "Hello World!";
    }
}
