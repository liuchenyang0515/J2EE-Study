package com.me.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 关于多个过滤器符合条件拦截，可以在配置文件或者注解形式设置 
 * <filter-mapping>
 * 		<filter-name>UrlPatternFilter</filter-name> 
 * 		<url-pattern>/</url-pattern>
 * </filter-mapping>
 * <filter-mapping>
 * 		<filter-name>UrlPatternFilter</filter-name>
 * 		<url-pattern>/servlet/*</url-pattern> 
 * </filter-mapping> 
 * <filter-mapping>
 * 		<filter-name>UrlPatternFilter</filter-name>
 * 		<url-pattern>*.jsp</url-pattern>
 * </filter-mapping>
 * 	或者如下配置，建议配置比较复杂的还是写到web.xml比较好
 */
//多条件匹配写这个注解会出现意想不到的结果，*.jsp就是拦截不到一些jsp，不推荐写这个注解，推荐写到web.xml
// @WebFilter(filterName = "UrlPatternFilter", urlPatterns = { "/", "/servlet/*", "*.jsp" })
public class UrlPatternFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		System.out.println("拦截到:" + req.getRequestURI());
		chain.doFilter(request, response);// 请求和响应向后传递
	}

}
