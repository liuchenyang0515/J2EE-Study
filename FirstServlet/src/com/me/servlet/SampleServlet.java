package com.me.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getMethod();
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String sex = request.getParameter("sex");
		String[] specs = request.getParameterValues("spec");
		PrintWriter out = response.getWriter(); // 向浏览器输出的数据流
		out.println("<h1>method:" + methodName + "</h1>");
		out.println("<h1>name:" + name + "</h1>");
		out.println("<h1>mobile:" + mobile + "</h1>");
		out.println("<h1>sex:" + sex + "</h1>");
		for (int i = 0; i < specs.length; ++i) {
			out.println("<h2>spec:" + specs[i] + "</h2>");
		}
		out.println("<a href='https://www.baidu.com'>Baidu</a>");
	}

}
