<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<h2>${amt }</h2>
	<!-- 3个0就有逗号 -->
	<h2>
		￥<fmt:formatNumber value="${amt }" pattern="0,000.00"></fmt:formatNumber>元
	</h2>
	<h2>null默认值：<c:out value="${nothing}" default="无"></c:out></h2>
	<h2><c:out value="${requestScope.html }" escapeXml="true"></c:out></h2>
	<!-- c:out解决在EL中null只显示空串的问题，也能解决转义问题 -->
	
	<!-- escapeXml="true"表示进行转义，这个<a>标签会当成字符串输出
	
	<h2><a href='index.html'>index</a></h2> 会变成
	
	<h2>&lt;a href=&#039;index.html&#039;&gt;index&lt;/a&gt;</h2>
	 -->
</body>
</html>