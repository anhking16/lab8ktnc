<!-- header.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Thêm đường dẫn đến CSS nếu cần -->
</head>
<body>
<header>
    <h1>Welcome to Admin Panel</h1>
    <nav>
        <ul>
            <li><a href="departments?action=list">Departments</a></li>
            <li><a href="employees?action=list">Employees</a></li>
            <!-- Thêm các liên kết khác nếu cần -->
        </ul>
    </nav>
</header>
