package com.django.springboot2.web.inteceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName DemoInterceptor
 * @Author Administrator
 * @Date 2019/5/7
 * @Version 1.0
 * @Description
 * springmvc 的interceptor 实现对每一个请求处理前后进行相关的业务处理，类似于servlet的Filter
 * 可以让普通的bean实现HanlderInterceptor接口或继承HandlerInterceptorAdapter类来实现自定义的拦截器
 *
 *
 *
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {




    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        Long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        request.setAttribute("handlingTime",System.currentTimeMillis()-startTime);
        System.out.println("接口处理所用时间为:"+request.getAttribute("handlingTime"));
    }



}
