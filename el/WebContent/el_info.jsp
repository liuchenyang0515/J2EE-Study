<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>姓名：${requestScope.student.name}</h1>
	<h2>手机：${requestScope.student.mobile}</h2>
	<h2>评级：${requestScope.grade}</h2>
</body>
</html>