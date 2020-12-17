package com.me.listener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestTotalListener implements ServletContextListener, ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		ServletRequestListener.super.requestDestroyed(sre);
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		String url = request.getRequestURL().toString();
		if (url.endsWith("rt")) {//��������ȥrt��β���������׼ȷͳ�ƣ���Ȼ����Ҳ����ȥ��ͳ��ҳ��total.html����Ȼÿ��ˢ��ͳ��ҳ��Ҳ������һ�������
			return ;
		}
		
		List<String> timeList = (List<String>) sre.getServletContext().getAttribute("timeList");
		List<Integer> valueList = (List<Integer>) sre.getServletContext().getAttribute("valueList");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String time = sdf.format(date);
		if (timeList.indexOf(time) == -1) {
			timeList.add(time);
			valueList.add(1);
			sre.getServletContext().setAttribute("timeList", timeList);
			sre.getServletContext().setAttribute("valueList", valueList);
		} else {
			int index = timeList.indexOf(time);
			int value = valueList.get(index);
			valueList.set(index, value + 1);
			// ��֤ʱ��˳�򣬲���Map����List
			sre.getServletContext().setAttribute("value", valueList);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		List<String> timeList = new ArrayList<>();
		List<Integer> valueList = new ArrayList<>();
		sce.getServletContext().setAttribute("timeList", timeList);
		sce.getServletContext().setAttribute("valueList", valueList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

}
