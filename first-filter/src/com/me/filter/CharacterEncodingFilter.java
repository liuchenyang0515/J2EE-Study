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
		/* �������ȡencoding��ʱ��Ҫô��web.xml��д��
		 * <init-param>
		 * 		<param-name>encoding</param-name>
		 * 		<param-value>UTF-8</param-value>
		 * </init-param>
		 * Ҫô��ע����д@WebInitParam(name="encoding", value="UTF-8")
		 * ���������ж������Ӧȡ������
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
