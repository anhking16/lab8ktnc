<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Người Dùng</title>
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
        <div class="alert alert-info text-center">
            ${message}
            <c:url var="url" value="/user" />
        </div>

        <!-- Form -->
        <div class="card shadow-sm mt-4">
            <div class="card-header">
                <h4 class="mb-0">Thông Tin Người Dùng</h4>
            </div>
            <div class="card-body">
                <form action="${url}/index" method="post">
                    <div class="row mb-3">
                        <div class="col">
                            <input type="number" class="form-control" value="${form.id}" name="id" placeholder="ID Người Dùng" readonly>
                        </div>
                        <div class="col">
                            <input type="password" class="form-control" value="${form.password}" name="password" placeholder="Mật Khẩu" required>
                        </div>
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" value="${form.username}" name="username" placeholder="Tên Người Dùng" required>
                    </div>
                    <div class="mb-3">
                        <input type="email" class="form-control" value="${form.email}" name="email" placeholder="Địa Chỉ Email" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Vai Trò:</label>
                        <div class="form-check form-check-inline">
                            <input ${form.isAdmin ? 'checked' : ''} class="form-check-input" type="radio" name="isAdmin" value="1">
                            <label class="form-check-label">Admin</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input ${form.isAdmin ? '' : 'checked'} class="form-check-input" type="radio" name="isAdmin" value="0">
                            <label class="form-check-label">Người Dùng</label>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Tình Trạng:</label>
                        <div class="form-check">
                            <input ${form.isActive ? 'checked' : ''} class="form-check-input" type="checkbox" name="isActive" value="1">
                            <label class="form-check-label">Kích Hoạt</label>
                        </div>
                    </div>
                    <div class="text-center">
                        <button class="btn btn-success me-2" formaction="${url}/create"><i class="fas fa-plus"></i> Tạo Mới</button>
                        <button class="btn btn-warning me-2" formaction="${url}/update"><i class="fas fa-edit"></i> Cập Nhật</button>
                        <button class="btn btn-danger me-2" formaction="${url}/delete"><i class="fas fa-trash"></i> Xóa</button>
                        <a class="btn btn-secondary" href="${url}/index"><i class="fas fa-undo"></i> Làm Mới</a>
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
                <form action="${url}/search" method="get" class="input-group mb-3">
                    <input type="text" name="search" class="form-control" placeholder="Tìm kiếm theo Tên Người Dùng">
                    <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
                </form>

                <table class="table table-bordered table-striped">
                    <thead class="table-primary">
                        <tr>
                            <th>ID</th>
                            <th>Password</th>
                            <th>Tên Người Dùng</th>
                            <th>Email</th>
                            <th>Vai Trò</th>
                            <th>Tình Trạng</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.password}</td> <!-- Đổi từ passwords thành password -->
                                <td>${item.username}</td>
                                <td>${item.email}</td>
                                <td>${item.isAdmin ? 'Admin' : 'Người Dùng'}</td>
                                <td>${item.isActive ? 'Kích Hoạt' : 'Không Kích Hoạt'}</td>
                                <td>
                                    <a class="btn btn-sm btn-info me-2" href="${url}/edit/${item.id}"><i class="fas fa-edit"></i> Chỉnh Sửa</a>
                                    <a class="btn btn-sm btn-danger" href="${url}/delete?id=${item.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này không?');">
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
