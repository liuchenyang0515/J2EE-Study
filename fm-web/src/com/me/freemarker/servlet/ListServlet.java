package com.me.freemarker.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // uri要先访问一次list，不然读取employee_list为null
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> list = new ArrayList<>();
		list.add(new Employee(7731, "张三", "市场部", "客户代表", 8000f));
		list.add(new Employee(8871, "李四", "研发部", "运维工程师", 7000f));
		// freemarker在servlet读取数据依次在1.请求request 2.会话session 3.全局context中查找
//		request.setAttribute("employee_list", list);//两个都可以，随便选择一个
		request.getServletContext().setAttribute("employee_list", list);
		request.getRequestDispatcher("/employee.ftl").forward(request, response);
	}

}
