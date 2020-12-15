package com.me.employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		if (context.getAttribute("employees") == null) {
			List<Employee> list = new ArrayList<>();
			Employee emp = new Employee(7731, "刘志敏", "市场部", "客户代表", 10000f);
			list.add(emp);
			list.add(new Employee(8871, "张倩", "研发部", "运维工程师", 8000f));
			context.setAttribute("employees", list);
		}
		request.getRequestDispatcher("/employee.jsp").forward(request, response);
	}

}
