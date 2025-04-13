<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản Lý Người Dùng</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
    tr:hover { background-color: #e8f5e9; }
    .card-header { background-color: #007bff; color: #fff; }
    .table-striped > tbody > tr:nth-of-type(odd) { background-color: #f9f9f9; }
</style>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <!-- Thông báo -->
        <c:if test="${not empty message}">
            <div class="alert alert-info text-center">
                ${message}
            </div>
        </c:if>

        <!-- Form -->
        <div class="card shadow-sm mt-4">
            <div class="card-header">
                <h4 class="mb-0">Thông Tin Người Dùng</h4>
            </div>
            <div class="card-body">
                <form method="post">
                    <div class="mb-3">
                        <input type="text" class="form-control" name="username" placeholder="Tên Người Dùng" value="${form.username}" required>
                    </div>
                    <div class="mb-3">
                        <input type="password" class="form-control" name="password" placeholder="Mật Khẩu" value="${form.password}" required>
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" name="lastname" placeholder="Họ Người Dùng" value="${form.lastname}" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Vai Trò:</label>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="isAdmin" value="true" ${form.isAdmin ? 'checked' : ''}>
                            <label class="form-check-label">Admin</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="isAdmin" value="false" ${form.isAdmin ? '' : 'checked'}>
                            <label class="form-check-label">Người Dùng</label>
                        </div>
                    </div>
                    <div class="text-center">
                        <button class="btn btn-success me-2" formaction="user/create"><i class="fas fa-plus"></i> Tạo Mới</button>
                        <button class="btn btn-warning me-2" formaction="user/update"><i class="fas fa-edit"></i> Cập Nhật</button>
                        <button class="btn btn-danger me-2" formaction="user/delete" onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này không?');"><i class="fas fa-trash"></i> Xóa</button>
                        <a class="btn btn-secondary" href="user/index"><i class="fas fa-undo"></i> Làm Mới</a>
                    </div>
                </form>
            </div>
        </div>

        <!-- Bảng Thông Tin -->
        <div class="card shadow-sm mt-4">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0">Danh Sách Người Dùng</h5>
            </div>
            <div class="card-body">
                <!-- Form tìm kiếm -->
                <form action="user/search" method="get" class="input-group mb-3">
                    <input type="text" name="search" class="form-control" placeholder="Tìm kiếm theo tên hoặc họ người dùng" value="${param.search}">
                    <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
                </form>

                <table class="table table-bordered table-striped">
                    <thead class="table-primary">
                        <tr>
                            <th>Tên Người Dùng</th>
                            <th>Mật Khẩu</th>
                            <th>Họ</th>
                            <th>Vai Trò</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}">
                            <tr>
                                <td>${item.username}</td>
                                <td>${item.password}</td>
                                <td>${item.lastname}</td>
                                <td>${item.isAdmin ? 'Admin' : 'Người Dùng'}</td>
                                <td>
                                    <a class="btn btn-sm btn-info" href="user/edit/${item.username}"><i class="fas fa-edit"></i> Chỉnh Sửa</a>
                                    <a class="btn btn-sm btn-danger" href="user/delete?username=${item.username}" onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này không?');">
                                        <i class="fas fa-trash"></i> Xóa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
