package com.me.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * 实际开发建议web.xml配置过滤器，不要写注解形式，因为过滤范围变化了就要重新编译代码，就需要重新测试
 * 更不要配置文件形式和注解形式混用。
 * */
@WebFilter(filterName="MyAnnoationFilter", urlPatterns="/*")
public class MyAnnoationFilter implements Filter{

	@Override
	public void doFilter(ServletRequest resquest, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("注解形式过滤器已生效");
		chain.doFilter(resquest, response);
		
	}

}
