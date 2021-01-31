package com.imooc.restful.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessHistoryInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(AccessHistoryInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuilder log = new StringBuilder();
        log.append(request.getRemoteAddr()); // 获取远程访问用户的IP地址
        log.append("|");
        log.append(request.getRequestURL()); // 获取用户访问的URL
        log.append("|");
        log.append(request.getHeader("user-agent")); // 获取用户系统信息(客户端环境)
        //     <logger name="com.imooc.restful.interceptor.AccessHistoryInterceptor"
        //            level="INFO" additivity="false"> 日志打印最低级别为INFO，所以是logger.info打印
        logger.info(log.toString());
        // 这两种日志打印方法效果一样，可以在文件中对比查看
        logger.info("==={} | {} | {}", request.getRemoteAddr(), request.getRequestURL(), request.getHeader("user-agent"));
        return true;
    }
}
