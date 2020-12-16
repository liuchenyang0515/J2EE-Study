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
 * ���ڶ�������������������أ������������ļ�����ע����ʽ���� 
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
 * 	�����������ã��������ñȽϸ��ӵĻ���д��web.xml�ȽϺ�
 */
//������ƥ��д���ע���������벻���Ľ����*.jsp�������ز���һЩjsp�����Ƽ�д���ע�⣬�Ƽ�д��web.xml
// @WebFilter(filterName = "UrlPatternFilter", urlPatterns = { "/", "/servlet/*", "*.jsp" })
public class UrlPatternFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		System.out.println("���ص�:" + req.getRequestURI());
		chain.doFilter(request, response);// �������Ӧ��󴫵�
	}

}
