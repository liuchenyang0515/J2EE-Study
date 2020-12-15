package com.me.jstl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JstlServlet
 */
@WebServlet("/jstl")
public class JstlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JstlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("score", 58);
		request.setAttribute("grade", "F");
		List<Company> list = new ArrayList<>();
		list.add(new Company("ÌÚÑ¶", "www.tencent.com"));
		list.add(new Company("°Ù¶È", "www.baidu.com"));
		list.add(new Company("Ä½¿Î", "www.imooc.com"));
		request.setAttribute("companies", list);
		request.getRequestDispatcher("/core.jsp").forward(request, response);
	}

}
