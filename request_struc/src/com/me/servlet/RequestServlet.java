package com.me.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().println("This is get method");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().println("This is post method");
		int number = Integer.valueOf(request.getParameter("number"));
		System.out.println("number=" + number);
		int sum = 0;
		for (int i = 1; i <= number; ++i) {
			sum += i;
		}
		request.setAttribute("sum", "" + sum);
		// 请求转发自由编程
		request.getRequestDispatcher("/direct/rd").forward(request, response);
	}

}
