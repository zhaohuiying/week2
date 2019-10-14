<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
	<td>序号</td>
	<td>名称</td>
	<td>价格</td>
	<td>百分比</td>
<tr>
<c:forEach items="${list}" var="l">
<tr>
	<td>${l.id }</td>
	<td>${l.name }</td>
	<td>${l.price }</td>
	<td>${l.baifen }</td>
</tr>

</c:forEach>
</table>
<a href="finzetall?page=${prepage }">上一页</a>
<a href="finzetall?page=${nextpage }">下一页</a>
</body>
</html>