<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
            max-width: 500px; /* Adjust the width as needed */
            margin: 0 auto;  /* Centers the form horizontally */
        }
        .form-control {
            width: 100%; /* Ensures the inputs take up full width of the container */
        }
</style>
</head>
<body>
<div class="container mt-5">

    <c:url var="url" value="/user" />

    <!-- CRUD USER Section -->
    <h3 class="text-center mb-4">CRUD USER</h3>

   

    <!-- CRUD Form -->
     <div class="form-container">
     
      <!-- Search Form -->
    <form action="${url}/search" method="get" class="d-flex justify-content-center mb-4">
        <div class="input-group w-50">
            <input type="text" name="keyword" class="form-control" placeholder="Fullname or Email" required>
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>
     
    <form action="${url}/index" method="post">
    <input class="form-control" value="${form.id}" name="id" placeholder="Id"><br>
    <input class="form-control" value="${form.password}" name="password" placeholder="Password"><br>
    <input class="form-control" value="${form.fullname}" name="fullname" placeholder="Fullname"><br>
    <input class="form-control" value="${form.email}" name="email" placeholder="Email Address"><br>

    <div class="col-md-6 d-flex align-items-center">
                <div class="form-check me-3">
                    <input ${form.admin?'checked':''} name="admin" type="radio" value="true" class="form-check-input">
                    <label class="form-check-label">Admin</label>
                </div>
                <div class="form-check">
                    <input ${form.admin?'':'checked'} name="admin" type="radio" value="false" class="form-check-input">
                    <label class="form-check-label">User</label>
                </div>
            </div>
    <br>
    <div class="d-flex justify-content-center">
    <button formaction="${url}/create" class="btn btn-primary me-2">Create</button>
    <button formaction="${url}/update" class="btn btn-warning me-2">Update</button>
    <button formaction="${url}/delete" class="btn btn-danger me-2">Delete</button>
    <a href="${url}/index" class="btn btn-info">Reset</a>
    </div>
    <br> 
</form>
</div>
<hr>
    <!-- Display User Table -->
    <table class="table table-striped custab table-hover">
        <thead class="table-primary">
            <tr>
                <th>ID</th>
                <th>Password</th>
                <th>Fullname</th>
                <th>Email</th>
                <th>isAdmin</th>
                <th class="text-center">Action</th>
            </tr>
        </thead>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.id}</td>
                <td>${item.password}</td>
                <td>${item.fullname}</td>
                <td>${item.email}</td>
                <td>${item.admin?'Admin':'User'}</td>
                <td class="text-center">
                    <a href="${url}/edit/${item.id}" class='btn btn-info btn-sm'>
                        <i class="bi bi-pencil"></i> Edit
                    </a>
                    <a href="${url}/delete?id=${item.id}" class="btn btn-danger btn-sm">
                        <i class="bi bi-trash"></i> Del
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
