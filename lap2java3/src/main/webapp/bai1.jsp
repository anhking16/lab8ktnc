<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>website bán hàng</title>
</head>
<body>
<img src="logo1.PNG"/>
<hr/>
<a href="bai1">Bài 1 </a> ||<a href="bai2">Bài 2 </a> || <a href="bai3">Bài 3 </a>|| <a href="bai4">Bài 4 </a>
<hr/>
<h1>${message}</h1>
<h2>${now.date}</h2>
<hr>
<jsp: include page="user-info.jsp"></jsp:include>
</body>
</html>