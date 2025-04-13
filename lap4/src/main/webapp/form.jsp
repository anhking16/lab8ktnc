<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<IDOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add</title>
<style>
body {
font-family: Arial, sans-serif;
margin: 20px;
}
form {
max-width: 600px;
margin: auto;
}
label {
display: block;
margin-bottom: 5px;
}
input[type="text"],
input[type="password"],
select,
textarea {
width: 100%;
padding: 10px;
margin-bottom: 10px;
border: 1px solid #ccc;
border-radius: 5px;
}
input[type="radio"],
input[type="checkbox"] {
margin-right: 5px;
}
button {
padding: 10px 15px;
background-color: #28a745;
color: white;
border: none;
border-radius: 5px;
cursor: pointer;
}
button: hover {
background-color: #218838;
}
.form-group {
margin-bottom: 15px;
}
</style>
</head>
<body>
<center><h1>Information of staff</h1></center>
<form action="/lap4/add" method="post" enctype="multipart/form-data">
<div class="form-group">
<label>Tên Đang Nhập</label>
<input name="fullname" type="text"/>
</div>
<div class="form-group">
<label>Mật Khẩu</label>
<input type="password" name="password">
</div>

<div class="form-group">
<label>Anh đại diện</label>
<input type="file" name="photo_file">
</div>

<div class="form-group">
<label>Giới Tính</label>
<input type="radio" name="gender" value="True"> Male
<input type="radio" name="gender" value="False"> Female
</div>

<div class="form-group">
<input type="checkbox" name="married"> Đã có gia đình?
</div>
<div class="form-group">
<label>Quoc gia</label>
<select name="country">
<option selected disabled>Country</option>
<option value="Vietnamese">Vietnamese</option>
<option value="United States">United States</option>
<option value="United Kingdom"> United Kingdom</option>
</select>
</div>

<div class="form-group">
<label>Sở thích</label>
<input type="checkbox" name="hobbies" value="Coding"/> Coding
<input type="checkbox" name="hobbies" value="Travel"/> Travel
<input type="checkbox" name="hobbies" value="Music"/> Music
<input type="checkbox" name="hobbies" value="Other"/> Other
</div>
<div class="form-group">
<labe1>Ghi chú</label>
<textarea name="note"></textarea>
</div>

<button type="submit">Dang Ký</button>
</form>

<a href="/lap4">Trang chú</a>
</body>
</html>