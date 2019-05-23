package com.django.springboot2.web.inteceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName DemoFilter2
 * @Author Administrator
 * @Date 2019/5/7
 * @Version 1.0
 * @Description TODO
 */
public class DemoFilter2 implements HandlerInterceptor {
    /**
     * @Author django
     * @Date   2019/5/7
     * @param   * @param request
     * @param response
     * @param handler
     * @return boolean
     * @Desc   调用接口之前
    */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }

    /**
     * @Author django
     * @Date   2019/5/7
     * @param   * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @return void
     * @Desc   接口调用完，试图渲染之前
    */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * @Author django
     * @Date   2019/5/7
     * @param   * @param request
     * @param response
     * @param handler
     * @param ex
     * @return void
     * @Desc   试图渲染完之后
    */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
