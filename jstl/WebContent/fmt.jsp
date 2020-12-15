<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setAttribute("amt", 1987654.326);
		request.setAttribute("now", new Date());
		request.setAttribute("html", "<a href='index.html'>index</a>");
		request.setAttribute("nothing", null);
	%>
	<h2>${now }</h2>
	<!-- format pattern
		yyyy - 四位年
		MM - 两位月
		dd - 两位日
		HH - 24小时制
		hh - 12小时制
		mm - 分钟
		ss - 秒数
		SSS - 毫秒
	 -->
	<h2>
		<fmt:formatDate value="${requestScope.now }"
			pattern="yyyy年MM月dd日 HH时mm分ss秒SSS毫秒 " />
	</h2>
</body>
</html>