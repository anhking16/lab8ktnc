

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
                <a href="/LABB5/account/sign-out" class="btn btn-link">Đăng xuất</a>
                <c:if test="${sessionScope.user.admin}">
                    <a href="/LABB5/user/index" class="btn btn-link">Quản trị</a>
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
    <div class="alert alert-info" role="alert">
        Request scope: ${requestScope.reqUser.fullname}
    </div>
    <hr>
    <!-- Thông báo -->
    <div class="alert alert-info" role="alert">
        ${message}
    </div>
    <c:url var="url" value="/user" />
    <!-- Form -->
    <form action="${url}/LABB5/index" method="post">
        <div class="form-group">
            <input type="text" value="${form.id}" name="id" class="form-control" placeholder="ID?">
        </div>
        <div class="form-group">
            <input type="password" value="${form.password}" name="password" class="form-control" placeholder="Password?">
        </div>
        <div class="form-group">
            <input type="text" value="${form.fullname}" name="fullname" class="form-control" placeholder="Fullname?">
        </div>
        <div class="form-group">
            <input type="email" value="${form.email}" name="email" class="form-control" placeholder="Email Address?">
        </div>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="admin" value="true" ${form.admin?'checked':''}>
                <label class="form-check-label">Admin</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="admin" value="false" ${form.admin?'':'checked'}>
                <label class="form-check-label">User</label>
            </div>
        </div>
        <hr>
        <button formaction="${url}/create" class="btn btn-success">Create</button>
        <button formaction="${url}/update" class="btn btn-primary">Update</button>
        <button formaction="${url}/delete" class="btn btn-danger">Delete</button>
        <a href="${url}/index" class="btn btn-secondary">Reset</a>
    </form>
    <!-- Bảng -->
    <table class="table table-bordered mt-3">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Password</th>
                <th>Fullname</th>
                <th>Email</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.password}</td>
                    <td>${item.fullname}</td>
                    <td>${item.email}</td>
                    <td>${item.admin?'Admin':'User'}</td>
                    <td><a href="${url}/user/edit/${item.id}" class="btn btn-warning btn-sm">Edit</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<!-- Nhúng Bootstrap JS và các thư viện phụ thuộc -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
