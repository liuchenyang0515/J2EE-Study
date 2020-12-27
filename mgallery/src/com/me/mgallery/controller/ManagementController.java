package com.me.mgallery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.mgallery.service.PaintingService;
import com.me.mgallery.utils.PageModel;

/**
 * Servlet implementation class ManagementController
 */
@WebServlet("/management")
public class ManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PaintingService paintingService = new PaintingService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if (method.equals("list")) {
			this.list(request,response);
		} else if (method.equals("delete")) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p = request.getParameter("p");
		String r = request.getParameter("r");
		if (p == null) {
			p = "1";
		}
		if (r == null) {
			r = "6";
		}
		PageModel pageModel = paintingService.pagination(Integer.parseInt(p), Integer.parseInt(r));
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
	}

}
