package com.me.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ImportServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		System.out.println("正在导入数据");
	}
	
}
