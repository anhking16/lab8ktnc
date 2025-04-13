<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Management - CRUD</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<div class="container">
	<div class="form-container">
		<h2>User Management - CRUD</h2>
		
		
		
	<div class="search-container">
    <input type="text" id="searchInput" placeholder="Tìm kiếm theo User ID">
    <button type="button" onclick="searchUsers()" class="search-button">Tìm</button>
</div>
		
		

		
		<hr>
		
		
		
		
		<form id="actionForm" method="post">
	<div class="input-group">
		<label for="userId"></label> <input type="text" id="userId"
						name="id" placeholder="UserID" value="${user.id}">
	</div>
	<div class="input-group">
		<label for="password"></label> <input type="password"
			id="password" name="password" placeholder="Password"
			value="${user.password}">
	</div>
		<div class="input-group">
			<label for="fullname"></label> <input type="text"
					id="fullname" name="fullname" placeholder="Full-name"
					value="${user.fullname}">
	</div>
		<div class="input-group">
			<label for="email"></label> <input type="email"
					id="email" name="email" placeholder="E-mail Address"
					value="${user.email}">
	</div>
		<div class="input-group role-group">
			<label>Role:</label> <input type="radio" id="admin" name="role"
					value="true" ${user.admin ? 'checked' : ''}> <label
					for="admin">Admin</label> <input type="radio" id="user"
					name="role" value="false" ${!user.admin ? 'checked' : ''}>
			<label for="user">User</label>
	</div>

				<div class="text-center">
    <button type="button" onclick="submitForm('USERS/create')" class="user-form-button add">Thêm</button>
    <button type="button" onclick="submitForm('USERS/delete')" class="user-form-button delete">Xóa</button>
    <button type="button" onclick="submitForm('USERS/update')" class="user-form-button update">Cập Nhật</button>
    <button type="button" onclick="resetForm()" class="user-form-button reset">Làm mới</button>
</div>

			</form>
		</div>

		<!-- Table displaying the list of users -->
		<table class="user-table">
			<thead>
				<tr>
					<th>No</th>
					<th>User ID</th>
					<th>Password</th>
					<th>Full-name</th>
					<th>Email</th>
					<th>Role</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<!-- Lặp qua danh sách người dùng -->
				<c:forEach var="user" items="${list}">
					<tr
						onclick="selectUser('${user.id}', '${user.password}', '${user.fullname}', '${user.email}', '${user.admin}')">
						<td>${user.id}</td>
						<td>${user.id}</td>
						<td>${user.password}</td>
						<td>${user.fullname}</td>
						<td>${user.email}</td>
						<td>${user.admin ? 'Admin' : 'User'}</td>
						<td><a href="javascript:void(0)"
							onclick="selectUser('${user.id}', '${user.password}', '${user.fullname}', '${user.email}', '${user.admin}')">Edit</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script>
		// Select user function - Fills the form when a row is clicked
		function selectUser(id, password, fullname, email, admin) {
			document.getElementById('userId').value = id;
			document.getElementById('password').value = password;
			document.getElementById('fullname').value = fullname;
			document.getElementById('email').value = email;

			// Cập nhật vai trò
			const adminRadio = document.getElementById('admin');
			const userRadio = document.getElementById('user');
			if (role === 'true' || role === true) {
				adminRadio.checked = true;
			} else {
				userRadio.checked = true;
			}
		}

		// Reset the form fields
		function resetForm() {
			document.getElementById("actionForm").reset();
		}

		// Form submission logic to change the action path dynamically
		function submitForm(actionPath) {
			var form = document.getElementById("actionForm");
			var role = document.querySelector('input[name="role"]:checked');

			// Kiểm tra xem có role nào được chọn không
			if (role) {
				console.log("Selected role: " + role.value); // Kiểm tra giá trị role
			} else {
				console.log("No role selected");
			}

			// Đổi đường dẫn action của form
			form.action = '${pageContext.request.contextPath}/' + actionPath;
			form.submit();
		}
		
		
		
		
		
		function searchUsers() {
		    const input = document.getElementById('searchInput');
		    const filter = input.value.toLowerCase();
		    const table = document.querySelector('.user-table tbody');
		    const rows = table.getElementsByTagName('tr');

		    for (let i = 0; i < rows.length; i++) {
		        const cells = rows[i].getElementsByTagName('td');
		        let found = false;

		        // Kiểm tra từng ô trong hàng
		        for (let j = 0; j < cells.length; j++) {
		            if (cells[j].textContent.toLowerCase().includes(filter)) {
		                found = true;
		                break;
		            }
		        }

		        // Hiện hoặc ẩn hàng dựa trên kết quả tìm kiếm
		        rows[i].style.display = found ? '' : 'none';
		    }
		}
		
		
		
		
		
		
	</script>
</body>
</html>
