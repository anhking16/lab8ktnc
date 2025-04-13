<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Registration Management</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container mt-5">
	<h1 class="text-center">Registration Management</h1>
	<!-- Hiển thị thông báo lỗi nếu có -->
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>
	<!-- Form để thêm người dùng mới -->
	<form method="post" action="registration?action=register"
		class="border p-4 rounded bg-light mb-4">
		<h2 class="text-center">Register Page</h2>
		<div class="mb-3">
			<label for="username" class="form-label">Username</label> <input
				type="text" id="username" name="username" class="form-control"
				placeholder="Username" required>
		</div>
		<div class="mb-3">
			<label for="password" class="form-label">Password</label> <input
				type="password" id="password" name="password" class="form-control"
				placeholder="Password" required>
		</div>
		<div class="mb-3">
			<label for="lastname" class="form-label">Lastname</label> <input
				type="text" id="lastname" name="lastname" class="form-control"
				placeholder="Lastname" required>
		</div>
		<div class="form-check mb-3">
			<input type="checkbox" id="admin" name="admin"
				class="form-check-input"> <label for="admin"
				class="form-check-label">Admin</label>
		</div>
		<button type="submit" class="btn btn-primary w-100">Register</button>
	</form>
	<!-- Form tìm kiếm -->
	<form method="get" action="registration" class="mb-3">
		<div class="input-group">
			<input type="text" name="lastname" class="form-control"
				placeholder="Search by Lastname"> <input type="hidden"
				name="action" value="search">
			<button class="btn btn-primary" type="submit">Search</button>
		</div>
	</form>
	<table class="table table-striped">
		<thead class="table-dark">
			<tr>
				<th>Username</th>
				<th>Password</th>
				<th>Lastname</th>
				<th>Admin</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${registrations}" var="registration">
				<tr>
					<td>${registration.username}</td>
					<td>${registration.password}</td>
					<td>${registration.lastname}</td>
					<td>${registration.admin}</td>

					<td>
						<form method="get" action="registration" style="display: inline;">
							<input type="hidden" name="username"
								value="${registration.username}"> <input type="hidden"
								name="action" value="delete">
							<button type="submit" class="btn btn-danger">Delete</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
