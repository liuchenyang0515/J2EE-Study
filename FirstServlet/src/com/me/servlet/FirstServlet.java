package com.me.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

	public FirstServlet() {
		System.out.println("���ڴ���FirstServlet");
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������Ĳ���
		String name = request.getParameter("name");
		String html = "<h1 style='color:red'>hi," + name + "!</h1><hr/>";
		System.out.println("���ظ����������Ӧ����Ϊ��" + html);
		PrintWriter out = response.getWriter();
		out.println(html); // ��html���ͻ������
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("���ڳ�ʼ��FirstServlet����---init");
	}

	public void destroy() {
		System.out.println("��������FirstServlet����");
	}
}