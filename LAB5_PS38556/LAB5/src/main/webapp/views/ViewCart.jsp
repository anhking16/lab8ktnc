<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <!-- Nhúng Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1>Your Cart</h1>
            <form action="<c:url value='/ShoppingServletSession'/>" method="post">
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>No.</th>
                            <th>Item</th>
                            <th>Count</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${not empty sessionScope.CART.cart}">
                            <c:forEach var="entry" items="${sessionScope.CART.cart}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${entry.key}</td> <!-- Tên sản phẩm -->
                                    <td>${entry.value}</td> <!-- Số lượng sản phẩm -->
                                    <td><input type="checkbox" name="chk" value="${entry.key}" /></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
                <br>
                <button type="submit" name="action" value="Delete" class="btn btn-danger">Delete</button>
                <a href="<c:url value='/ShoppingServletSession?action=Back'/>" class="btn btn-secondary">Back</a>
            </form>
        </div>
        <!-- Nhúng Bootstrap JS và các thư viện phụ thuộc -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
