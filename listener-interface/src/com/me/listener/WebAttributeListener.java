package com.me.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class WebAttributeListener
		implements ServletContextAttributeListener, HttpSessionAttributeListener, ServletRequestAttributeListener {

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("Request新增属性：" + srae.getName() + "->" + srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("Request删除属性：" + srae.getName());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("Request替换属性：" + srae.getName() + "->" + srae.getValue());
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("HttpSession新增属性：" + se.getName() + "->" + se.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("HttpSession删除属性：" + se.getName());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("HttpSession替换属性：" + se.getName() + "->" + se.getValue());
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("ServletContext新增属性：" + scae.getName() + "->" + scae.getValue());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("ServletContext删除属性：" + scae.getName());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("ServletContext替换属性：" + scae.getName() + "->" + scae.getValue());
	}

}
