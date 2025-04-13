<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
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
                <h4 class="mb-0">User Information</h4>
            </div>
            <div class="card-body">
                <form action="${url}/index" method="post">
                    <div class="row mb-3">
                        <div class="col">
                            <input type="number" class="form-control" value="${form.id}" name="id" placeholder="User ID" readonly>
                        </div>
                        <div class="col">
                            <input type="password" class="form-control" value="${form.passwords}" name="passwords" placeholder="Password" required>
                        </div>
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" value="${form.username}" name="username" placeholder="Username" required>
                    </div>
                    <div class="mb-3">
                        <input type="email" class="form-control" value="${form.email}" name="email" placeholder="Email Address" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Role:</label>
                        <div class="form-check form-check-inline">
                            <input ${form.admin ? 'checked' : ''} class="form-check-input" type="radio" name="admin" value="true">
                            <label class="form-check-label">Admin</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input ${form.admin ? '' : 'checked'} class="form-check-input" type="radio" name="admin" value="false">
                            <label class="form-check-label">User</label>
                        </div>
                    </div>
                    <div class="text-center">
                        <button class="btn btn-success me-2" formaction="${url}/create"><i class="fas fa-plus"></i> Create</button>
                        <button class="btn btn-warning me-2" formaction="${url}/update"><i class="fas fa-edit"></i> Update</button>
                        <button class="btn btn-danger me-2" formaction="${url}/delete"><i class="fas fa-trash"></i> Delete</button>
                        <a class="btn btn-secondary" href="${url}/index"><i class="fas fa-undo"></i> Reset</a>
                    </div>
                </form>
            </div>
        </div>

        <!-- Bảng thông tin -->
        <div class="card shadow-sm mt-4">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0">User List</h5>
            </div>
            <div class="card-body">
                <!-- Form tìm kiếm -->
                <form action="${url}/search" method="get" class="input-group mb-3">
                    <input type="text" name="search" class="form-control" placeholder="Search by Username">
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>

                <table class="table table-bordered table-striped">
                    <thead class="table-primary">
                        <tr>
                            <th>ID</th>
                            <th>Password</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.passwords}</td>
                                <td>${item.username}</td>
                                <td>${item.email}</td>
                                <td>${item.admin ? 'Admin' : 'User'}</td>
                                <td>
                                    <a class="btn btn-sm btn-info me-2" href="${url}/edit/${item.id}"><i class="fas fa-edit"></i> Edit</a>
                                    <a class="btn btn-sm btn-danger" href="${url}/delete?id=${item.id}" onclick="return confirm('Are you sure you want to delete this user?');">
                                        <i class="fas fa-trash"></i> Delete
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
