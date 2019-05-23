package com.django.springboot2.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName DemoFilter
 * @Author Administrator
 * @Date 2019/5/7
 * @Version 1.0
 * @Description servlet3.0过滤器
 */
//@WebFilter(filterName = "demoFilter",
//        urlPatterns = "/*",
//        initParams = {
//                @WebInitParam(name = "resource", value = "/assets"),
//                @WebInitParam(name = "encoding", value = "utf-8")
//        }
//)
public class DemoFilter implements Filter {

    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String encoding = filterConfig.getInitParameter("encoding");
        String resource = filterConfig.getInitParameter("resource");



        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 设置请求的字符集（post请求方式有效）
        request.setCharacterEncoding(encoding);

        String requestURI = request.getRequestURI();

        if (requestURI.contains(resource)) {
            // 请求的登录，放行
            chain.doFilter(request, response);
            System.out.println("=========访问静态资源============放行");
        } else {
            System.out.println("=========不是访问静态资源============检查token");
            if (request.getHeader("token") == null) {
                // 重定向到登录页面
                System.out.println("=========没有token===========返回提示");
                response.getWriter().print("no token");
                return;
            } else {
                // 已经登录，放行
                System.out.println("=========有token============放行");
                chain.doFilter(request, response);
            }
        }



    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
