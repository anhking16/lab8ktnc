<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý người dùng</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Danh sách người dùng</h1>

    <!-- CRUD Form -->
    <form action="UserServlet" method="post" class="mb-4">
        <div class="row">
            <div class="col-md-3">
                <input type="text" class="form-control" name="username" placeholder="Username" required>
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control" name="firstname" placeholder="Firstname" required>
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control" name="lastname" placeholder="Lastname" required>
            </div>
            <div class="col-md-3">
                <button type="submit" name="action" value="create" class="btn btn-success w-100">Thêm</button>
            </div>
        </div>
    </form>

    <!-- User Table -->
    <table class="table table-bordered table-hover">
        <thead class="table-primary">
        <tr>
            <th>Username</th>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
                <td>
                    <a href="UserServlet?action=edit&username=${user.username}" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="UserServlet?action=delete&username=${user.username}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này không?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty users}">
            <tr>
                <td colspan="4" class="text-center">Không có người dùng nào.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
