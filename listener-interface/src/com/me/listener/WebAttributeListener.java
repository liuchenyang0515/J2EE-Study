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
		System.out.println("Request�������ԣ�" + srae.getName() + "->" + srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("Requestɾ�����ԣ�" + srae.getName());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("Request�滻���ԣ�" + srae.getName() + "->" + srae.getValue());
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("HttpSession�������ԣ�" + se.getName() + "->" + se.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("HttpSessionɾ�����ԣ�" + se.getName());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("HttpSession�滻���ԣ�" + se.getName() + "->" + se.getValue());
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("ServletContext�������ԣ�" + scae.getName() + "->" + scae.getValue());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("ServletContextɾ�����ԣ�" + scae.getName());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("ServletContext�滻���ԣ�" + scae.getName() + "->" + scae.getValue());
	}

}
