<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="poly.com.model.Departments" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh sách Phòng ban</title>
</head>
<body>
    <h1>Danh sách Phòng ban</h1>

    <form action="departments1" method="get">
        <input type="hidden" name="action" value="search">
        <input type="text" name="searchId" placeholder="Nhập ID phòng ban">
        <button type="submit">Tìm</button>
    </form>

    <form action="departments1" method="post">
        <input type="hidden" name="action" value="${department != null ? 'update' : 'add'}">
        <label>ID: <input type="text" name="id" value="${department != null ? department.id : ''}" ${department != null ? 'readonly' : ''}></label>
        <label>Tên: <input type="text" name="name" value="${department != null ? department.name : ''}"></label>
        <label>Mô tả: <input type="text" name="description" value="${department != null ? department.description : ''}"></label>
        <button type="submit">${department != null ? 'Cập nhật' : 'Thêm'}</button>
    </form>

    <c:if test="${not empty message}">
        <div>${message}</div>
    </c:if>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Mô tả</th>
            <th>Hành động</th>
        </tr>
        <c:forEach var="department" items="${departments}">
            <tr>
                <td>${department.id}</td>
                <td>${department.name}</td>
                <td>${department.description}</td>
                <td>
                    <a href="departments1?action=edit&id=${department.id}">Sửa</a>
                    <a href="departments1?action=delete&id=${department.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
