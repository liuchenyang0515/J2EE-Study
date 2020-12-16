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
// �������в�Ҫʹ��ע�������ˣ���Ϊ�ᰴ����Ļ˳��������ɺܴ�����
// ע���͹������ǰ���class������ĸ����������˵ģ���ͬ����������ʵ�ֲ�ͬ���������Ҳ��ͬ
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
	// ��������ִ��˳����web.xml�е�<filter-mapping>����˳��
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("I'm Filter A");
		chain.doFilter(request, response); // ���ע�͵�����ôtomcat��ֱ�Ӵ�ӡA��A2Ȼ�󷵻ؿյ���Ӧ�壬��Ӧ�뻹��200
		System.out.println("I'm Filter A2");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
