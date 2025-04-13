<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
        <!-- Nhúng Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1>Shopping Cart</h1>
            <p>Please choose your favorite:</p>
            <form action="${pageContext.request.contextPath}/ShoppingServletSession" method="post">
                <div class="form-group">
                    <select name="cboCard" class="form-control">
                        <option>Caffe</option>
                        <option>Duong</option>
                        <option>Tieu</option>
                        <option>Ca</option>
                        <option>Sua</option>
                        <option>Kem</option>
                        <option>Banh Mi</option>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" value="Add To Cart" name="action" class="btn btn-primary">Add To Cart</button>
                    <button type="submit" value="View Cart" name="action" class="btn btn-secondary">View Cart</button>
                </div>
            </form>
        </div>
        <!-- Nhúng Bootstrap JS và các thư viện phụ thuộc -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
