<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Management</title>
</head>
<body>
    <h1>Employee Management</h1>

    <h2>Add New Employee/update</h2>
  <form action="${pageContext.request.contextPath}/employees" method="post" enctype="multipart/form-data">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id" required value="${editid.id}"> <!-- Sử dụng EL để lấy giá trị -->
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required value="${editid.password}"> <!-- Sửa lỗi ở đây -->
    <br>
    <label for="fullname">Full Name:</label>
    <input type="text" id="fullname" name="fullname" required value="${editid.fullname}"> <!-- Sử dụng EL để lấy giá trị -->
    <br>
    <label for="gender">Gender:</label>
    <input type="checkbox" id="gender" name="gender" ${editid.gender ? 'checked' : ''}> <!-- Đánh dấu checkbox nếu là nam -->
    <br>
    <label for="birthday">Birthday:</label>
    <input type="date" id="birthday" name="birthday" value="${editid.birthday}"> <!-- Sử dụng EL để lấy giá trị -->
    <br>
    <label for="salary">Salary:</label>
    <input type="number" id="salary" name="salary" required value="${editid.salary}"> <!-- Sử dụng EL để lấy giá trị -->
    <br>
    <label for="departmentId">Department ID:</label>
    <input type="text" id="departmentId" name="departmentId" required value="${editid.departmentId}"> <!-- Sử dụng EL để lấy giá trị -->
    <br>
    <label for="photo">Photo:</label>
    <input type="file" id="photo" name="photo" accept="image/*" > <!-- Không cần giá trị mặc định cho file upload -->
    <br>
    
    <!-- Hai nút Add và Update -->
    <input type="submit" name="action" value="Add Employee">
    <input type="submit" name="action" value="Update Employee">
    <input type="submit" name="action" value="Delete Employee">
</form>

</form>

    <h2>Search Employee</h2>
    <form action="${pageContext.request.contextPath}/employees/search" method="get" style="display:inline;">
        <label for="searchId">Employee ID to Search:</label>
        <input type="text" id="searchId" name="id" required>
        <input type="submit" value="Search">
    </form>


    <h2>Employee List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Gender</th>
                <th>Birthday</th>
                <th>Salary</th>
                <th>Department ID</th>
                <th>Photo</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${employeesList}">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.fullname}</td>
                    <td>${employee.gender ? 'Male' : 'Female'}</td>
                    <td>${employee.birthday}</td>
                    <td>${employee.salary}</td>
                    <td>${employee.departmentId}</td>
                    <td><img src="/Slide6_java3_fall2024/${employee.photo}" alt="Photo" width="100"></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/employees/edit" method="get" style="display:inline;" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="${employee.id}">
                            <input type="submit" value="Edit">
                        </form>
                        <form action="${pageContext.request.contextPath}/employees/delete" method="get" style="display:inline;">
                            <input type="hidden" name="id" value="${employee.id}">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>