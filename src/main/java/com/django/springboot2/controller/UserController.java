package com.django.springboot2.controller;


import com.django.springboot2.pojo.domain.User;
import com.django.springboot2.pojo.vo.UserVO;
import com.django.springboot2.service.serviceImpl.MyBatisUserServiceImpl;
import com.django.springboot2.web.HttpResult;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liulongyun
 * @create 2019/5/29 14:38
 **/


@RestController
@RequestMapping("/user")
public class UserController {

    private  Logger logger = LoggerFactory.getLogger(this.getClass().getName());

//    @Autowired
//    private JdbcTmplUserService jdbcTmplUserService;

    @Autowired
    private MyBatisUserServiceImpl userService;


//    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
//    public HttpResult registerUser(@RequestBody UserVO userVO) throws Exception{
//
//        User user = new User(userVO);
//        jdbcTmplUserService.insertUser(user);
//        HttpResult result = new HttpResult();
//        result.setStatus(HttpStatus.OK.value());
//        result.setMsg(HttpStatus.OK.getReasonPhrase());
//        return result;
//
//    }
//
//
//    @RequestMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
//    public HttpResult getUserById(@PathVariable(value = "id") Long id) throws Exception{
//
//        User user = jdbcTmplUserService.getUser(id);
//        HttpResult result = new HttpResult();
//        result.setStatus(HttpStatus.OK.value());
//        result.setMsg(HttpStatus.OK.getReasonPhrase());
//        result.setData(new UserVO(user));
//        return result;
//    }
//
//    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.PUT)
//    public  HttpResult deleteById(@RequestBody UserVO userVO) throws Exception{
//
//        User user = new User(userVO);
//        jdbcTmplUserService.updateUser(user);
//        HttpResult result = new HttpResult();
//        result.setStatus(HttpStatus.OK.value());
//        result.setMsg(HttpStatus.OK.getReasonPhrase());
//        return result;
//
//    }
//
//    @RequestMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.DELETE)
//    public  HttpResult deleteById(@PathVariable(value = "id") Long id) throws Exception{
//
//        jdbcTmplUserService.deleteUser(id);
//        HttpResult result = new HttpResult();
//        result.setStatus(HttpStatus.OK.value());
//        result.setMsg(HttpStatus.OK.getReasonPhrase());
//        return result;
//
//    }


    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public HttpResult registerUser(@RequestBody UserVO userVO) throws Exception{

        User user = new User(userVO);
        user.setPwd(new BCryptPasswordEncoder().encode(user.getPwd()));
        userService.insertUser(user);
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        return result;

    }

   /**
    * @Author liulongyun
    * @Date 2019/10/29 16:13
    * @api {POST} /user/updateUser   --更新用户
    * @apiGroup  custom-info
    * @apiVersion 1.0.0
    * @apiDescription 更新用户
    * @apiParam {Long} id 客户主表主键
    * @apiParamExample {json} 请求样例：
    * {
    * "id":"1234567890"
    * }
    * @apiSuccessExample {json} 成功:
    * {
    *   "code": 0,
    *   "message": "操作成功",
    * }
    *
    */

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.PUT)
    public HttpResult updateUser(@RequestBody UserVO userVO) throws Exception{


        User user = new User(userVO);
        userService.updateUserName(userVO.getId(),userVO.getUserName());
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        return result;


    }

    @RequestMapping(value = "/findusers",method = RequestMethod.GET)
    public HttpResult findUsers(@RequestParam("userName") String userName,@RequestParam("note")String note)throws Exception{

        List<User> users = userService.findUsers(userName, note);
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(users);
        return result;
    }


    @RequestMapping(method = RequestMethod.DELETE)
    public HttpResult deleteUser(@RequestParam("id") Long id) throws Exception{

        userService.deleteUser(id);
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        return result;

    }





    @RequestMapping(value = "/batch",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public HttpResult registerUserBath(@RequestBody List<UserVO> userVOs) throws Exception{

        List<User> users = new ArrayList<>();

        for (int i = 0; i < userVOs.size(); i++) {
            users.add(new User(userVOs.get(i)));

        }
        userService.insertUserBatch1(users);
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(users);
        return result;

    }




    @RequestMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public HttpResult getUserById(@PathVariable(value = "id") Long id) throws Exception{

        logger.info("======================getUserByIdWithMybatis    param id:"+id);

        User user = userService.getUser(new Long(id));
        HttpResult result = new HttpResult();
        result.setStatus(HttpStatus.OK.value());
        result.setMsg(HttpStatus.OK.getReasonPhrase());
        result.setData(user);
        return result;
    }




}
