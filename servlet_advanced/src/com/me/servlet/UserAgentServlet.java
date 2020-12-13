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
@WebServlet("/UA") // 这个区分大小写
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
			output = "<h1>这是PC端的首页</h1>";
		} else if (userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("Android") != -1
				|| userAgent.indexOf("iPad") != -1) {
			output = "<h1>这是移动端的首页</h1>";
		}
		response.getWriter().println(output);
	}

}
