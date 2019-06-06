package com.django.springboot2.controller;

import com.django.springboot2.pojo.domain.User;
import com.django.springboot2.pojo.vo.UserVO;
import com.django.springboot2.service.MongodbUserService;
import com.django.springboot2.web.HttpResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liulongyun
 * @create 2019/6/4 17:10
 **/
@RestController
@RequestMapping("/mongodb/user")
public class MongoUserController {

    @Autowired
    private  MongodbUserService mongodbUserService = null;


    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public HttpResult addUser(@RequestBody UserVO userVO){


        User user = new User(userVO);
        mongodbUserService.saveUser(user);

        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        return result;
    }


    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public HttpResult getUserById(@RequestParam("id") Long id){

        User user = mongodbUserService.getUser(id);

        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(user);
        return result;
    }

    @RequestMapping(value = "/finduser",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public HttpResult findUser(String userName,String note,int skip ,int limit){

        List<User> users = mongodbUserService.findUser(userName, note, skip, limit);

        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(users);
        return result;
    }


    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.DELETE)
    public HttpResult deleteUserById(@RequestParam("id") Long id){

        DeleteResult deleteResult = mongodbUserService.deleteUser(id);

        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(deleteResult);
        return result;
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.PUT)
    public HttpResult updateUser(Long id,String userName,String note){

        UpdateResult updateResult = mongodbUserService.updateUser(id, userName, note);

        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(updateResult);
        return result;
    }




}
