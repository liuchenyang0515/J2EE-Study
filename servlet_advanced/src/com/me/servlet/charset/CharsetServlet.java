package com.me.servlet.charset;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 * Servlet implementation class CharsetServlet
 */
@WebServlet("/charset/process")
public class CharsetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CharsetServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 对于Tomcat8.x以上的版本，默认get请求发送中文就是UTF-8格式，因此无需转换
		String ename = request.getParameter("ename");
		String address = request.getParameter("address");
		System.out.println(ename + ":" + address);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(ename + ":" + address);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将请求体种字符集转为utf-8，对get请求无效，get请求没有请求体
		request.setCharacterEncoding("utf-8");
		String ename = request.getParameter("ename");
		String address = request.getParameter("address");
//		String utf8Ename = new String(ename.getBytes("iso-8859-1"), "utf-8");
//		String utf8Address = new String(address.getBytes("iso-8859-1"), "utf-8");
		System.out.println(ename + ":" + address);
	}

}
