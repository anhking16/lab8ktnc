<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Count</title>
</head>
<body>
<c:choose>
	<c:when test="${empty sessionScope.user}">
		Welcome you
	</c:when>
	<c:when test="${not empty sessionScope.user}">
		Welcome ${sessionScope.user.fullname}	
		<a href="/Lab6_dv_java6/account/sign-out">Đăng xuất</a>
		<c:if test="${sessionScope.user.admin}">
			<a href="/Lab6_dv_java6/user/index">Quản trị</a>
		</c:if>	
	</c:when>
	
	<c:otherwise>		
		
	</c:otherwise>
</c:choose>
<br>

<h2 style="color: red">Visitors: ${applicationScope.visitors}</h2>
	${message}
	<form action="/SOF3011Slide6/account/sign-in" method="post">
		<input type="text" name="username" placeholder="Fullname?"><br><br>
		<input type="password" name="password" placeholder="Password?"><br><br>
		<button>Login</button>
	</form>
	<br>
	${param.error}
</body>
</html>