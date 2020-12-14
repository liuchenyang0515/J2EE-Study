package com.me.servlet.pattern;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatternServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 查询员工的基本信息
		// 查询当前访问的URL
		String url = req.getRequestURL().toString();
		System.out.println(url);
		String id = url.substring(url.lastIndexOf("/") + 1);
		System.out.println(id);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(id);
		if (id.equals("1")) {
			out.println("张三");
		} else if (id.equals("2")) {
			out.println("李四");
		} else {
			out.println("其他员工");
		}
	}

}
