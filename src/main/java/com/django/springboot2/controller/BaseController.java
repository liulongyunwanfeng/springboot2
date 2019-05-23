package com.django.springboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName BaseController
 * @Author Administrator
 * @Date 2019/5/9
 * @Version 1.0
 * @Description TODO
 */
@ControllerAdvice
public class BaseController {

    /**
     * @Author django
     * @Date   2019/5/9
     * @param   * @param exception
     * @param request
     * @return org.springframework.web.servlet.ModelAndView
     * @Desc   统一的异常处理
    */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage",exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("modelattrmsg","所用接口请求作用域里都会添加的信息");
    }

}
