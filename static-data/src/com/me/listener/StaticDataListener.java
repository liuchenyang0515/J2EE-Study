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
		list.add(new Channel("��ѿγ�", "http://www.imooc.com/1"));
		list.add(new Channel("ʵս�γ�", "http://www.imooc.com/2"));
		list.add(new Channel("��ҵ��", "http://www.imooc.com/3"));
		sce.getServletContext().setAttribute("channelList", list);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

}
