package com.django.springboot2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.executable.ValidateOnExecution;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseController
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 * @Description TODO
 */
@ControllerAdvice
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * @Author django
     * @Date   2019/5/9
     * @param   * @param exception
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @Desc   统一的异常处理  转向error.jsp页面
    */
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView exception(Exception exception, WebRequest request){
//        logger.error(exception.getMessage());
//        exception.printStackTrace();
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("errorMessage",exception.getMessage());
//        modelAndView.setViewName("error");
//        return modelAndView;
//    }


    /**
     * 统一的异常处理器，这个异常处理器中应该对不同的异常做不同的区分，组织对应的统一的异常信息给前段
     * 本项目中用ResponseEntity作为响应的json体返回给前端，请求的响应码被设置在请求响应的
     * Status中，项目中应该扩展需要的状态嘛来做合理的响应码
     * 请求是否成功信息被设置在响应头success中
     * 项目中应该抽象处各种异常信息来抛出，到这里来处理不同的异常。
     *
     *
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception exception, WebRequest request){

        logger.error(exception.getMessage());
        exception.printStackTrace();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("success","false");

        ResponseEntity<BigDecimal> responseEntity = null;


        String errorMsg = exception.getMessage();

        logger.error("获取异常类型名称=========："+exception.getClass().getName());
        if( exception instanceof MethodArgumentNotValidException){ // 如果是参数验证异常
            Map<String,Object> errorMap = new HashMap<>();

            MethodArgumentNotValidException methodArgumentNotValidException =
                    (MethodArgumentNotValidException) exception;

            List<ObjectError> objectErrors = methodArgumentNotValidException.getBindingResult().getAllErrors();
            for (ObjectError error: objectErrors) {
                if(error instanceof FieldError){
                    FieldError fe = (FieldError) error;
                    errorMap.put(fe.getField(),fe.getDefaultMessage());
                }else {
                    // 对象错
                    errorMap.put(error.getObjectName(),error.getDefaultMessage());
                }

            }

            HashMap<String,Object> errorResultInfo = new HashMap<>();
            errorResultInfo.put("errorMsg",errorMap);
            responseEntity = new ResponseEntity(errorResultInfo,httpHeaders, HttpStatus.BAD_REQUEST);

        }else{

            Map<String,Object> errorMap = new HashMap<>();
            errorMap.put("errorMsg",errorMsg);
            responseEntity = new ResponseEntity(errorMap,httpHeaders, HttpStatus.BAD_REQUEST);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage",exception.getMessage());
            modelAndView.setViewName("error");

        }


        return responseEntity;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("modelattrmsg","所用接口请求作用域里都会添加的信息");
    }

}
