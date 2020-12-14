package com.me.servlet.servletcontext;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletContextInitServlet
 */
@WebServlet("/servletcontext/init")
public class ServletContextInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletContextInitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
//		context.setAttribute("copyright", "@ 2018 lcy.com ��ICP����");
//		context.setAttribute("title", "�ҵĸ��˲���");
		// ��ȡ�����ļ�web.xml�ֵ�ȫ�ֲ������Ժ󷽱�ֱ���������ļ��޸ģ������޸Ĵ���
		String copyright = context.getInitParameter("copyright");
		context.setAttribute("copyright", copyright);
		String title = context.getInitParameter("title");
		context.setAttribute("title", title);
		response.getWriter().println("init success");
	}

}
