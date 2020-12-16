package com.me.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ��������дcontextPath����Ϊ�������̵�ʱ������Ϊ��/������url�Ͳ��ô��Ϲ�����
public class DeviceAdapterFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		/*
		 * ����/index.html PC: /desktop/index.html MOBILE: /mobile/index.html
		 * 
		 * ����/test.html PC: /desktop/test.html MOBILE: /mobile/test.html
		 */
		String uri = req.getRequestURI();
		System.out.println("URI:" + uri);
		if (uri.startsWith("/desktop") || uri.startsWith("/mobile")) {
			chain.doFilter(request, response);
		} else {
			String userAgent = req.getHeader("user-agent");
			String targetURI = "";
			// ��Ҫ�Ƚ�һ��
			if (userAgent.indexOf("android") != -1 || userAgent.indexOf("iPhone") != -1) {
				targetURI = "/mobile" + uri;
				System.out.println("�ƶ����豸���ڷ��ʣ�������תURI��" + targetURI);
				res.sendRedirect(targetURI);
			} else {
				targetURI = "/desktop" + uri;
				System.out.println("PC���豸���ڷ��ʣ�������תURI��" + targetURI);
				res.sendRedirect(targetURI);
			}
		}

	}

}
