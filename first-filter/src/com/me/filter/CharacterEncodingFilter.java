package com.me.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebFilter(filterName="CharacterEncodingFilter", urlPatterns="/*",initParams= {
		@WebInitParam(name="encoding", value="UTF-8")
})
public class CharacterEncodingFilter implements Filter {
	private String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		/* 在这里获取encoding的时候，要么在web.xml中写上
		 * <init-param>
		 * 		<param-name>encoding</param-name>
		 * 		<param-value>UTF-8</param-value>
		 * </init-param>
		 * 要么在注解中写@WebInitParam(name="encoding", value="UTF-8")
		 * 参数可以有多个，对应取出即可
		 */
		encoding = filterConfig.getInitParameter("encoding");
		System.out.println("encoding: " + encoding);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding(encoding);
		HttpServletResponse res = (HttpServletResponse) response;
		res.setContentType("text/html;charset=" + encoding);
		chain.doFilter(request, response);
	}

}
