package com.me.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFirstFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("�������ѱ�����");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("��������ʼ���ɹ�");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("����������Ч");
		chain.doFilter(request, response);
	}

}
