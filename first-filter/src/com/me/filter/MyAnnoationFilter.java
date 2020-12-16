package com.me.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * ʵ�ʿ�������web.xml���ù���������Ҫдע����ʽ����Ϊ���˷�Χ�仯�˾�Ҫ���±�����룬����Ҫ���²���
 * ����Ҫ�����ļ���ʽ��ע����ʽ���á�
 * */
@WebFilter(filterName="MyAnnoationFilter", urlPatterns="/*")
public class MyAnnoationFilter implements Filter{

	@Override
	public void doFilter(ServletRequest resquest, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("ע����ʽ����������Ч");
		chain.doFilter(resquest, response);
		
	}

}
