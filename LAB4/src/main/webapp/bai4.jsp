<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url value="/bai4" var="url"/> 
<form action="${url}" method="post" enctype="multipart/form-data"> 
    <input name="photo" type="file"><br> 
    <button>Upload</button> 
</form> 
	
</body>
</html>