package com.me.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class FilterA
 */
// 过滤链中不要使用注解来过滤，因为会按照字幕顺序排序，造成很大困扰
// 注解型过滤器是按照class类名字母升序排序过滤的，不同服务器可能实现不同，排序规则也不同
public class FilterA implements Filter {

    /**
     * Default constructor. 
     */
    public FilterA() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	// 过滤器的执行顺序是web.xml中的<filter-mapping>声明顺序
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("I'm Filter A");
		chain.doFilter(request, response); // 如果注释掉，那么tomcat会直接打印A和A2然后返回空的响应体，响应码还是200
		System.out.println("I'm Filter A2");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
