package com.me.employee;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 否则控制台打印和调试中文会乱码
		String empno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		String department = request.getParameter("department");
		String job = request.getParameter("job");
		String salary = request.getParameter("salary");
		System.out.println(empno);
		Employee emp = new Employee(Integer.parseInt(empno), ename, department, job, Float.parseFloat(salary));
		ServletContext context = request.getServletContext();
		List employees = (List) context.getAttribute("employees");
		employees.add(emp);
		context.setAttribute("employees", employees);
		request.getRequestDispatcher("/employee.jsp").forward(request, response);
	}

}
