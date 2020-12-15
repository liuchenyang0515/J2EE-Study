<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<!-- 忽略作用域对象，按照作用域从小到大依次获取，建议还是写上作用域，不然会从其他作用域尝试获取，会降低效率 -->
	<h1>姓名：${student.name}</h1>
	<h2>手机：${student.mobile}</h2>
	<!-- localhost:8080/el/info?teacher=andy，param内置对象能获取到这个参数值，
		这也是Servlet中request.getParameter的简化形式
	 -->
	<h2>讲师：${param.teacher }</h2>
	<h2>评级：${grade}</h2>
	<h2>概要：${student}</h2>
</body>
</html>