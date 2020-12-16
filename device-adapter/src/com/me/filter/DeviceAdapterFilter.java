package com.me.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 这里无需写contextPath，因为创建工程的时候设置为了/，访问url就不用带上工程名
public class DeviceAdapterFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		/*
		 * 访问/index.html PC: /desktop/index.html MOBILE: /mobile/index.html
		 * 
		 * 访问/test.html PC: /desktop/test.html MOBILE: /mobile/test.html
		 */
		String uri = req.getRequestURI();
		System.out.println("URI:" + uri);
		if (uri.startsWith("/desktop") || uri.startsWith("/mobile")) {
			chain.doFilter(request, response);
		} else {
			String userAgent = req.getHeader("user-agent");
			String targetURI = "";
			// 简要比较一下
			if (userAgent.indexOf("android") != -1 || userAgent.indexOf("iPhone") != -1) {
				targetURI = "/mobile" + uri;
				System.out.println("移动端设备正在访问，重新跳转URI：" + targetURI);
				res.sendRedirect(targetURI);
			} else {
				targetURI = "/desktop" + uri;
				System.out.println("PC端设备正在访问，重新跳转URI：" + targetURI);
				res.sendRedirect(targetURI);
			}
		}

	}

}
