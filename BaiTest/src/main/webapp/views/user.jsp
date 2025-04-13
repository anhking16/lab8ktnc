<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .custab {
            border: 1px solid #ccc;
            padding: 10px;
            margin: 20px 0;
            box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.1);
            transition: all 0.3s;
        }
        .custab:hover {
            box-shadow: 3px 3px 15px rgba(0, 0, 0, 0.3);
        }
        .form-container {
            max-width: 500px;
            margin: 0 auto;
        }
        .form-control {
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <c:url var="url" value="/user" />

    <!-- CRUD USER Section -->
    <h3 class="text-center mb-4">User Management</h3>

    <!-- Search Form -->
    <form action="${url}/search" method="get" class="d-flex justify-content-center mb-4">
        <div class="input-group w-50">
            <input type="text" name="keyword" class="form-control" placeholder="Search by Fullname or Email" required>
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>

    <!-- CRUD Form -->
    <div class="form-container">
        <form action="${url}/index" method="post">
            <input type="text" class="form-control mb-3" name="id" value="${form.id}" placeholder="ID">

            <input type="text" class="form-control mb-3" name="fullname" value="${form.fullname}" placeholder="Fullname">
            <input type="email" class="form-control mb-3" name="email" value="${form.email}" placeholder="Email Address">

            <div class="d-flex mb-3">
                <div class="form-check me-3">
                    <input type="radio" class="form-check-input" name="admin" value="true" ${form.admin ? 'checked' : ''}>
                    <label class="form-check-label">Admin</label>
                </div>
                <div class="form-check">
                    <input type="radio" class="form-check-input" name="admin" value="false" ${!form.admin ? 'checked' : ''}>
                    <label class="form-check-label">User</label>
                </div>
            </div>

            <div class="d-flex justify-content-center">
                <button formaction="${url}/create" class="btn btn-success me-2">Create</button>
                <button formaction="${url}/update" class="btn btn-warning me-2">Update</button>
                <button formaction="${url}/delete" class="btn btn-danger me-2">Delete</button>
                <a href="${url}/index" class="btn btn-secondary">Reset</a>
            </div>
        </form>
    </div>

    <hr>

    <!-- Display User Table -->
    <table class="table table-striped custab table-hover">
        <thead class="table-primary">
            <tr>
                <th>no</th>
              
                <th>Fullname</th>
              
                <th>Role</th>
                <th class="text-center">Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.id}</td>
               
                <td>${item.fullname}</td>
                
                <td>${item.admin ? 'Admin' : 'User'}</td>
                <td class="text-center">
                    <a href="${url}/edit/${item.id}" class="btn btn-info btn-sm">Edit</a>
                    <a href="${url}/delete?id=${item.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty items}">
            <tr>
                <td colspan="6" class="text-center">No users found</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
