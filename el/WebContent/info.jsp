<%@page import="com.me.el.Student"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Student stu = (Student) request.getAttribute("student");
		String grade = (String) request.getAttribute("grade");
		out.println("<h1>姓名：" + stu.getName() + "</h1>");
		out.println("<h2>手机：" + stu.getMobile() + "</h2>");
		out.println("<h2>教师评级：" + grade + "</h2>");
	%>
</body>
</html>