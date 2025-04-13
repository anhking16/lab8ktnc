<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đổi mật khẩu</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Đổi mật khẩu</h2>
        <form method="POST" action="<%= request.getContextPath() %>/account/change-password">
            <% String step = request.getParameter("step"); %>
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <div class="alert alert-danger"><%= message %></div>
            <% } %>

            <% if (step == null || "sendOtp".equals(step)) { %>
                <input type="hidden" name="step" value="sendOtp">
                <button type="submit" class="btn btn-primary">Gửi mã OTP</button>
            <% } else if ("verifyOtp".equals(step)) { %>
                <input type="hidden" name="step" value="verifyOtp">
                <div class="mb-3">
                    <label for="otp" class="form-label">Nhập mã OTP đã được gửi đến email của bạn:</label>
                    <input type="text" id="otp" name="otp" class="form-control" placeholder="Mã OTP" required>
                </div>
                <div class="mb-3">
                    <label for="newPassword" class="form-label">Nhập mật khẩu mới:</label>
                    <input type="password" id="newPassword" name="newPassword" class="form-control" placeholder="Mật khẩu mới" required>
                </div>
                <button type="submit" class="btn btn-success">Xác nhận</button>
            <% } %>
        </form>
    </div>
    <!-- Nhúng Bootstrap JS và các thư viện phụ thuộc -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
