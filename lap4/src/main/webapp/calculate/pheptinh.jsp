<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<IDOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../menu.jsp"></jsp:include>
<hr/>
<c:url value="calculate" var="cal"/>
<form method="post">
<input name="a"><br>
<input name="b"><br>
<button formaction="/lap4/${cal}/add">+</button>
<button formaction="/lap4/${cal}/sub"> -</button>
</form>
${message}
</body
elhtmls