<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%-- jsp内置对象在任意java代码块中可以直接调用
	具体可以见tomcat的work目录下的你的工程目录下的jsp
	D:\Tomcat9.0\work\Catalina\localhost\servlet_advanced\org\apache\jsp
	就可以看到jsp转换成的java文件和class文件，找到_jspService，里面什么都有
	 --%>
	<%
		String url = request.getRequestURL().toString(); // HttpServletRequest对象
		response.getWriter().println(url);// HttpServletResponse对象
	%>
	<% out.println("<br>ABCCC");
		session.setAttribute("user", "张三");
		out.println((String)session.getAttribute("user"));
	%>
	<%
		String cp = application.getInitParameter("copyright");
		out.println("<hr/>");
		out.println(cp);
	%>
</body>
</html>