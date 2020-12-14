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
		// ����Tomcat8.x���ϵİ汾��Ĭ��get���������ľ���UTF-8��ʽ���������ת��
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
		// �����������ַ���תΪutf-8����get������Ч��get����û��������
		request.setCharacterEncoding("utf-8");
		String ename = request.getParameter("ename");
		String address = request.getParameter("address");
//		String utf8Ename = new String(ename.getBytes("iso-8859-1"), "utf-8");
//		String utf8Address = new String(address.getBytes("iso-8859-1"), "utf-8");
		System.out.println(ename + ":" + address);
	}

}
