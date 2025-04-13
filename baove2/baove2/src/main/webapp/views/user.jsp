<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<style>
tr:hover {
	background-color: #f5f5f5;
}
</style>
</head>
<body>
	<div class="container">
		<!-- Thông báo -->
		<div class="alert alert-info mt-4">
			${message}
			<c:url var="url" value="/user" />
		</div>

		<!-- Form -->
		<div class="card mt-4">
			<div class="card-header">
				<h4>LAB2</h4>
			</div>
			<div class="card-body">
				<form action="${url}/index" method="get">
					<div class="mb-3">
						<input class="form-control" value="${form.id}" name="id"
							placeholder="Id?" required>
					</div>
					<div class="mb-3">
						<input class="form-control" type="password"
							value="${form.password}" name="password" placeholder="Password?"
							required>
					</div>
					<div class="mb-3">
						<input class="form-control" value="${form.fullname}"
							name="fullname" placeholder="Fullname?" required>
					</div>
					<div class="mb-3">
						<input class="form-control" value="${form.email}" name="email"
							placeholder="Email Address?" required>
					</div>
					<div class="mb-3">
						<label class="form-label">Role:</label><br> <input
							${form.admin ? 'checked' : ''} name="admin" type="radio"
							value="true"> Admin <input ${form.admin ? '' : 'checked'}
							name="admin" type="radio" value="false"> User
					</div>
					<div class="mb-3">
						<button class="btn btn-primary" formaction="${url}/create">
							<i class="fas fa-plus"></i> Create
						</button>
						<button class="btn btn-warning" formaction="${url}/update">
							<i class="fas fa-edit"></i> Update
						</button>
						<button class="btn btn-danger" formaction="${url}/delete">
							<i class="fas fa-trash"></i> Delete
						</button>
						<a class="btn btn-secondary" href="${url}/index"> <i
							class="fas fa-undo"></i> Reset
						</a>
					</div>
				</form>
			</div>
		</div>

		<!-- Bảng -->
		<table class="table table-bordered mt-4">
			<thead class="table-light">
				<tr>
					<th>ID</th>
					<th>Password</th>
					<th>Fullname</th>
					<th>Email</th>
					<th>Admin</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<!-- Form tìm kiếm -->
				<form action="${url}/search" method="get" class="input-group mt-4">
					<input type="text" name="search" class="form-control"
						placeholder="Search by Name">
					<button type="submit" class="btn btn-primary">Search</button>
				</form>

				<c:forEach var="item" items="${items}">
					<tr>
						<td>${item.id}</td>
						<td>${item.password}</td>
						<td>${item.fullname}</td>
						<td>${item.email}</td>
						<td>${item.admin ? 'Admin' : 'User'}</td>
						<td><a class="btn btn-sm btn-info"
							href="${url}/edit/${item.id}"> <i class="fas fa-edit"></i>
								Edit
						</a> <a class="btn btn-sm btn-danger"
							href="${url}/delete?id=${item.id}"
							onclick="return confirm('muốn đá nó hả');"> <i
								class="fas fa-trash"></i> Delete
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
