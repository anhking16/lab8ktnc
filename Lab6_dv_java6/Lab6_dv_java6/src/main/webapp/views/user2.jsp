<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
	.custab{
	    border: 1px solid #ccc;
	    padding: 5px;
	    margin: 5% 0;
	    box-shadow: 3px 3px 2px #ccc;
	    transition: 0.5s;
    }
	.custab:hover{
	    box-shadow: 3px 3px 0px transparent;
	    transition: 0.5s;
    }
 
	body{padding-top:20px;}

</style>
</head>
<body>
<div class="container">

<c:choose>
	<c:when test="${empty sessionScope.user}">
		Welcome you
	</c:when>
	<c:when test="${not empty sessionScope.user}">
		Welcome ${sessionScope.user.fullname}	
		<a href="/SOF3011Slide6/account/sign-out">Đăng xuất</a>
		<c:if test="${sessionScope.user.admin}">
			<a href="/SOF3011Slide6/user/index">Quản trị</a>
		</c:if>	
	</c:when>
	
	<c:otherwise>		
		
	</c:otherwise>
</c:choose>
<br>
Request scope: ${requestScope.reqUser.fullname} 
<div class="container">
<!-- Thông báo -->
${message}
<c:url var="url" value="/user"/>
<!-- Form -->
<h3>CRUD USER</h3>
	<form action="${url}/index" method="post">
		<input class="form-control" value="${form.id}" name="id" placeholder="Id?"><br>
		<input class="form-control" value="${form.password}" name="password" placeholder="Password?"><br>
		<input class="form-control" value="${form.fullname}" name="fullname" placeholder="Fullname?"><br>
		<input class="form-control" value="${form.email}" name="email" placeholder="Email Address?"><br>
        
		<div class="form-check">
			<input ${form.admin?'checked':''} name="admin" type="radio" value="true" class="form-check-input">
			<label class="form-check-label">Admin</label>
			<input ${form.admin?'':'checked'} name="admin" type="radio" value="false" class="form-check-input">
			<label class="form-check-label">User</label>
		</div>
		<br> 
		<button formaction="${url}/create" class="btn btn-primary">Create</button>
		<button formaction="${url}/update" class="btn btn-warning">Update</button>
		<button formaction="${url}/delete" class="btn btn-danger">Delete</button>
		<a href="${url}/index" class="btn btn-info">Reset</a>
		<br>	<hr>
	</form>
    <div class="row col-md-6 col-md-offset-2 custyle">
    <table class="table table-striped custab table-hover">
    <thead class="table-primary">
    	<a href="#" class="btn btn-primary btn-xs pull-right"><b>+</b> Add new categories</a>
        <tr>
            <th>ID</th>
            <th>Password</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>isAdmin</th>
            <th class="text-center">Action</th>
        </tr>
    </thead>
    	<c:forEach var="item" items="${items}">
		<tr>
			<td>${item.id}</td>
			<td>${item.password}</td>
			<td>${item.fullname}</td>
			<td>${item.email}</td>
			<td>${item.admin?'Admin':'User'}</td>
			<td class="text-center">
				<a href="${url}/edit/${item.id}" class='btn btn-info btn-xs'><span class="glyphicon glyphicon-edit"></span> Edit</a> 
				<a href="${url}/delete?id=${item.id}" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Del</a>
			</td>
		</tr>
		</c:forEach>         
    </table>
    </div>
</div>
</div>
</body>
</html>