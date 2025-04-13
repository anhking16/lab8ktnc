<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Count</title>
<!-- Nhúng Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <div class="alert alert-primary" role="alert">
                Welcome you
            </div>
        </c:when>
        <c:when test="${not empty sessionScope.user}">
            <div class="alert alert-success" role="alert">
                Welcome ${sessionScope.user.fullname}
                
                <c:if test="${sessionScope.user.admin}">
                    <a href="/Lab6_dv_java6/user/index" class="btn btn-link">Quản trị</a>
                	<a href="/Lab6_dv_java6/account/sign-out" class="btn btn-link">Đăng xuất</a>
                
                </c:if>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-warning" role="alert">
                <!-- Nội dung khác nếu cần -->
            </div>
        </c:otherwise>
    </c:choose>
    <br>
    <h2 class="text-danger">Visitors: ${applicationScope.visitors}</h2>
    <div class="alert alert-info" role="alert">
        ${message}
    </div>
    <form action="/Lab6_dv_java6/account/sign-in" method="post">
        <div class="form-group">
            <input type="text" name="username" class="form-control" placeholder="Email?">
        </div>
        <div class="form-group">
            <input type="password" name="password" class="form-control" placeholder="Password?">
        </div>
        <a href="${pageContext.request.contextPath}/account/forgot-password" class="text-info mb-4">Forgot Password</a> <br>
        <br>
        
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <br>
    <div class="text-danger">
        ${param.error}
    </div>
</div>
<!-- Nhúng Bootstrap JS và các thư viện phụ thuộc -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
