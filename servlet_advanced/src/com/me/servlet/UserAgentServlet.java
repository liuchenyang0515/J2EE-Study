package com.me.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAgentServlet
 */
@WebServlet("/UA") // ������ִ�Сд
public class UserAgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userAgent = request.getHeader("User-Agent");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(userAgent);
		String output = "";
		if (userAgent.indexOf("Windows NT") != -1) {
			output = "<h1>����PC�˵���ҳ</h1>";
		} else if (userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("Android") != -1
				|| userAgent.indexOf("iPad") != -1) {
			output = "<h1>�����ƶ��˵���ҳ</h1>";
		}
		response.getWriter().println(output);
	}

}
