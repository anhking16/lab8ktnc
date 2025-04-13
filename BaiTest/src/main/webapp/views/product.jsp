<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD Products</title>
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

    <c:url var="url" value="/product" />

    <!-- CRUD Products Section -->
    <h3 class="text-center mb-4">CRUD Products</h3>

    <!-- CRUD Form -->
    <div class="form-container">
        <!-- Search Form -->
        <form action="${url}/search" method="get" class="d-flex justify-content-center mb-4">
            <div class="input-group w-50">
                <input type="text" name="keyword" class="form-control" placeholder="Search by Name" required>
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>

        <form action="${url}/index" method="post">
            <input class="form-control" value="${form.code}" name="code" placeholder="Product Code"><br>
            <input class="form-control" value="${form.name}" name="name" placeholder="Product Name"><br>
            <input class="form-control" value="${form.price}" name="price" placeholder="Price" type="number" step="0.01"><br>

            <div class="d-flex justify-content-center">
                <button formaction="${url}/create" class="btn btn-primary me-2">Create</button>
                <button formaction="${url}/update" class="btn btn-warning me-2">Update</button>
                <button formaction="${url}/delete" class="btn btn-danger me-2">Delete</button>
                <a href="${url}/index" class="btn btn-info">Reset</a>
            </div>
        </form>
    </div>

    <hr>

    <!-- Display Product Table -->
    <table class="table table-striped custab table-hover">
        <thead class="table-primary">
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>Price</th>
                <th class="text-center">Action</th>
            </tr>
        </thead>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.code}</td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td class="text-center">
                    <a href="${url}/edit/${item.code}" class='btn btn-info btn-sm'>
                        <i class="bi bi-pencil"></i> Edit
                    </a>
                    <a href="${url}/delete?code=${item.code}" class="btn btn-danger btn-sm">
                        <i class="bi bi-trash"></i> Del
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
