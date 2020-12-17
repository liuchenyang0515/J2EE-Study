package com.me.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.me.listener.entity.Channel;

public class StaticDataListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		List<Channel> list = new ArrayList<>();
		list.add(new Channel("免费课程", "http://www.imooc.com/1"));
		list.add(new Channel("实战课程", "http://www.imooc.com/2"));
		list.add(new Channel("就业班", "http://www.imooc.com/3"));
		sce.getServletContext().setAttribute("channelList", list);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

}
