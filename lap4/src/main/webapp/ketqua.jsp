<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<IDOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
<h1>Kết quả đăng ký</h1>

<p><strong>Tên Đang Nhập: </strong> ${fullname}</p>
<p><strong>Mat Khau: </strong> ${password}</p>

<p><strong>Giới Tinh: </strong>
<c: choose>
<c:when test="${gender == 'True'}">Nam</c:when>
<c:otherwise>Nu</c:otherwise>
</c: choose>
</p>

<p><strong>Đã có gia đình: </strong> ${married}</p>

<p><strong>Quoc gia :< /strong> ${country}</p>

<p><strong>Sở thích: </strong>
<c:if test="${not empty hobbies}">
<ul>
<c:forEach var="hobby" items="${hobbies}">
<1i>${hobby}</li>
</c:forEach>
</ul>
</c:if>
<c:if test="${empty hobbies}">
Không co sở thích được chọn
</c:if>
</p>

<p><strong>Ghi chú: </strong>
<c:if test="${not empty note}">
${note}
</c:if>
<c:if test="${empty note}">
Không co ghi chu
</c:if>
</p>

<p><strong>Ánh đại diện: </strong></p>
<img src="${photoURL}" alt="Ảnh đại diện" style="max-width: 200px;"/>

<a href="/lap4">Trở lại trang chủ</a>
</body>
</html>