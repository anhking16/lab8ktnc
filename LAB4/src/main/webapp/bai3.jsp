<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <h2>Đăng Ký</h2>
    <form action="dangky" method="post">
        <table>
            <tr>
                <td>Tên đăng nhập:</td>
                <td><input type="text" name="username" value="nghiemn" /></td>
            </tr>
            <tr>
                <td>Mật khẩu:</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>Giới tính:</td>
                <td>
                    <input type="radio" name="gender" value="Nam" checked> Nam
                    <input type="radio" name="gender" value="Nữ"> Nữ
                </td>
            </tr>
            <tr>
                <td>Đã có gia đình:</td>
                <td><input type="checkbox" name="family" /></td>
            </tr>
            <tr>
                <td>Quốc tịch:</td>
                <td>
                    <select name="country">
                        <option value="United States">United States</option>
                        <option value="Vietnam">Vietnam</option>
                        <!-- Thêm các tùy chọn khác nếu cần -->
                    </select>
                </td>
            </tr>
            <tr>
                <td>Sở thích:</td>
                <td>
                    <input type="checkbox" name="hobby" value="Đọc sách"> Đọc sách
                    <input type="checkbox" name="hobby" value="Du lịch"> Du lịch
                    <input type="checkbox" name="hobby" value="Âm nhạc"> Âm nhạc
                    <input type="checkbox" name="hobby" value="Khác"> Khác
                </td>
            </tr>
            <tr>
                <td>Ghi chú:</td>
                <td><textarea name="note">Đang tìm bạn gái</textarea></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Đăng ký" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>