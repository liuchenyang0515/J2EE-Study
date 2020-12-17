package com.me.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Administrator
 *
 */
public class WebListener implements ServletContextListener, HttpSessionListener, ServletRequestListener{
	

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("HttpServletRequest已被销毁");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("HttpServletRequest已被创建，URI:" + ((HttpServletRequest)sre.getServletRequest()).getRequestURI());
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Session已被创建，SessionId:" + se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Session已被销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContext已初始化");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletContext已销毁");
	}

}
