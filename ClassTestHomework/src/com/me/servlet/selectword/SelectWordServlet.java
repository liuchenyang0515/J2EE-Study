package com.me.servlet.selectword;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SelectWordServlet
 */
@WebServlet("/select/word")
public class SelectWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public HashMap<String, String> hashmap = new HashMap<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectWordServlet() {
        super();
        // TODO Auto-generated constructor stub
        hashmap.put("apple", "苹果");
        hashmap.put("pear", "梨子");
        hashmap.put("banana", "香蕉");
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String word = request.getParameter("word");
		if(hashmap.containsKey(word)) {
			request.setAttribute("selectResult", hashmap.get(word));
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		} else {
			response.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession();
			session.setAttribute("selectResult", "没有找到对应的单词解释");
			response.sendRedirect("/ClassTestHomework/fail.jsp");// 表单提交和重定向都要添加项目名这个上下文
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
