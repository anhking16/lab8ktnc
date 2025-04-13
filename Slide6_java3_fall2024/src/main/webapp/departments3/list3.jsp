<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="poly.com.model.Departments" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách Phòng ban</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Danh sách Phòng ban</h1>

        <!-- Form tìm kiếm -->
        <form action="/Slide6_java3_fall2024/departments3/search" method="get" class="form-inline mb-3">
            <input type="text" name="searchId" class="form-control" placeholder="Nhập ID phòng ban">
            <button type="submit" class="btn btn-secondary ml-2">Tìm</button>
        </form>

        <!-- Form thêm/chỉnh sửa phòng ban -->
        <form action="${department != null ? '/Slide6_java3_fall2024/departments3/sua' : '/Slide6_java3_fall2024/departments3/them'}" method="post" class="form-inline mb-3">
            <div class="form-group">
                <label for="id">ID</label>
                <input type="text" name="id" class="form-control ml-2" value="${department != null ? department.id : ''}" ${department != null ? 'readonly' : ''}>
            </div>
            <div class="form-group ml-3">
                <label for="name">Tên</label>
                <input type="text" name="name" class="form-control ml-2" value="${department != null ? department.name : ''}">
            </div>
            <div class="form-group ml-3">
                <label for="description">Mô tả</label>
                <input type="text" name="description" class="form-control ml-2" value="${department != null ? department.description : ''}">
            </div>
            <button type="submit" class="btn btn-primary ml-3">${department != null ? 'Cập nhật' : 'Thêm'}</button>
        </form>

        <c:if test="${not empty messagethem}">
            <div class="alert alert-info">${messagethem}</div>
        </c:if>

        <!-- Danh sách phòng ban -->
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên</th>
                    <th>Mô tả</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="department" items="${departments}">
                    <tr>
                        <td>${department.id}</td>
                        <td>${department.name}</td>
                        <td>${department.description}</td>
                        <td>
                            <!-- Nút sửa -->
                            <a href="/Slide6_java3_fall2024/departments3/sua?id=${department.id}" class="btn btn-warning">Sửa</a>

                            <!-- Nút xóa -->
                            <a href="/Slide6_java3_fall2024/departments3/xoa?id=${department.id}" class="btn btn-danger" 
                               onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
