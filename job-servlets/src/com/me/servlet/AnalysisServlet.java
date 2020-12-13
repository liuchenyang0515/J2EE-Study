package com.me.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns="/unused",loadOnStartup=2)
public class AnalysisServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		System.out.println("正在分析结果数据");
	}
	
}
