package com.django.springboot2.controller;


import com.django.springboot2.pojo.domain.User;
import com.django.springboot2.pojo.vo.UserVO;
import com.django.springboot2.service.JdbcTmplUserService;
import com.django.springboot2.service.serviceImpl.MyBatisUserServiceImpl;
import com.django.springboot2.web.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author liulongyun
 * @create 2019/5/29 14:38
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private  Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private JdbcTmplUserService jdbcTmplUserService;

    @Autowired
    private MyBatisUserServiceImpl userService;


    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public HttpResult registerUser(@RequestBody UserVO userVO) throws Exception{

        User user = new User(userVO);
        jdbcTmplUserService.insertUser(user);
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        return result;

    }


    @RequestMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public HttpResult getUserById(@PathVariable(value = "id") Long id) throws Exception{

        User user = jdbcTmplUserService.getUser(id);
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(new UserVO(user));
        return result;
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.PUT)
    public  HttpResult deleteById(@RequestBody UserVO userVO) throws Exception{

        User user = new User(userVO);
        jdbcTmplUserService.updateUser(user);
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        return result;

    }

    @RequestMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.DELETE)
    public  HttpResult deleteById(@PathVariable(value = "id") Long id) throws Exception{

        jdbcTmplUserService.deleteUser(id);
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        return result;

    }


    @RequestMapping(value = "/getUserByIdWithMybatis",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public HttpResult getUserByIdWithMybatis(@RequestParam(value = "id") Long id) throws Exception{

        logger.info("======================getUserByIdWithMybatis    param id:"+id);

        User user = userService.getUser(new Long(id));
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(user);
        return result;
    }


}
