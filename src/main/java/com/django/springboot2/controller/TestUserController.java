package com.django.springboot2.controller;

import com.django.springboot2.pojo.domain.ServerInfo;
import com.django.springboot2.pojo.domain.TestUser;
import com.django.springboot2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * @ClassName UserController
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 * @Description TODO
 */
@RestController
@RequestMapping("/testuser")
public class TestUserController {

    Logger logger = LoggerFactory.getLogger(TestUserController.class);
    @Autowired
    UserService userService ;
    // 从配置文件中获取值
    @Value("${company.name}")
    private String companyName;
    @Autowired
    private ServerInfo serverInfo;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @RequestMapping(value = "/{id}",produces = "application/json;charset=UTF-8")
    public TestUser getUser(HttpServletRequest request, HttpServletResponse response,
                            @PathVariable(name = "id") BigDecimal id) throws Exception{
        logger.info("===========companyNme:"+companyName+"============");
        logger.info(serverInfo.toString());
        logger.info("===========jdbcUrl:"+jdbcUrl+"================");
        return userService.queryByPk(id);
    }


}
