package com.ingta.si.web.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ingta.si.util.InitParams;

public class MyHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Log logger = LogFactory.getLog(MyHandlerInterceptor.class);
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
            "StopWatch-StartTime");
    private String timeOutJson = "{\"statusCode\":\"301\",\"message\":\"登录时间超时，请重新登录！\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":login\"\"}";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        // 简单判断，如果要详细控制可以用spring security
        String url = request.getServletPath();
        // System.out.println(request.getRequestURI()+"-------------------"+request.getContextPath());
        // System.out.println(url);
        // System.out.println(url.startsWith("/static"));
        long beginTime = System.currentTimeMillis();// 1、开始时间
        startTimeThreadLocal.set(beginTime);// 线程绑定变量（该数据只有当前请求的线程可见）

        if (url.endsWith("/login") || url.endsWith("/") || url.endsWith("/logout")
                || url.startsWith("/static")) {
            return true;
        }
        if (request.getSession() != null
                && request.getSession().getAttribute(InitParams.ADMIN) != null) {
            return true;
        } else {
            // ajax请求
            if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))
                    || request.getParameter("ajax") != null) {
                PrintWriter out = response.getWriter();
                out.println(timeOutJson);
            }
        }
        response.sendRedirect(request.getContextPath() + "/login?c=3");
        return false;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();// 2、结束时间
        long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;// 3、消耗的时间
        if (consumeTime > 500) {// 此处认为处理时间超过500毫秒的请求为慢请求
            logger.error(request.getRequestURI() + " " + consumeTime);
        }
    }
}
