<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Department Details</title>
    <style>
       body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            font-size: 2rem;
            color: #444;
            margin-bottom: 20px;
        }

        p {
            text-align: center;
            font-size: 1rem;
            color: #666;
        }

        .container {
            width: 80%;
            margin: 0 auto;
            padding-top: 50px;
        }

        .back-link {
            position: absolute;
            top: 20px;
            left: 20px;
            text-decoration: none;
            font-weight: bold;
            color: #4CAF50;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 15px;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        img {
            border-radius: 10px;
        }

        .list {
            margin-top: 40px;
        }
        span {
        	color: black;
        	font-weight: 600;
        	text-transform: uppercase;
        }
    </style>
</head>
<body>
    <div class="container">
    <a href="${pageContext.request.contextPath}/departments4" class="back-link">Quay lại</a>
        <h2>Chi tiết phòng ban</h2>
        <p>Mã phòng ban: <span>${department.id}</span></p>
        <p>Tên phòng ban: <span>${department.name}</span></p>
        <p>Mô tả: <span>${department.description}</span></p>

        <div class="list">
            <h2>Danh sách nhân viên</h2>
            <table>
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
                    <c:forEach var="employee" items="${employeesList}">
                        <tr>
                            <td>${employee.id}</td>
                            <td>${employee.fullname}</td>
                            <td>${employee.gender ? 'Male' : 'Female'}</td>
                            <td>${employee.birthday}</td>
                            <td>${employee.salary}</td>
                            <td>${employee.departmentId}</td>
                            <td><img src="${employee.photo}" alt="Photo" width="100"></td>
                            <td><img src="/Slide6_java3_fall2024/${employee.photo}" alt="Photo" width="100"></td>
                         
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
