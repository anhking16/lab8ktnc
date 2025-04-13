<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Employee</title>
</head>
<body>
    <h1>Search Employee</h1>

    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>

    <c:if test="${not empty foundEmployee}">
        <h2>Employee Details</h2>
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
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${foundEmployee.id}</td>
                    <td>${foundEmployee.fullname}</td>
                    <td>${foundEmployee.gender ? 'Male' : 'Female'}</td>
                    <td>${foundEmployee.birthday}</td>
                    <td>${foundEmployee.salary}</td>
                    <td>${foundEmployee.departmentId}</td>
                    <td><img src="${foundEmployee.photo}" alt="Photo" width="100"></td>
                </tr>
            </tbody>
        </table>
    </c:if>

    <br>
    <a href="${pageContext.request.contextPath}/employees">Back to Employee Management</a>
</body>
</html>
