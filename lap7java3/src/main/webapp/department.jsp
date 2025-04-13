<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Department Management</title>
</head>
<body>
    <form method="post" action="${path}/create">
        <label>Id:</label><br>
        <input name="id" value="${item.id}" /><br>
        <label>Name:</label><br>
        <input name="name" value="${item.name}" /><br>
        <label>Description:</label><br>
        <textarea name="description" rows="3">${item.description}</textarea><br>
        <button formaction="${path}/create">Create</button>
        <button formaction="${path}/update">Update</button>
        <button formaction="${path}/delete">Delete</button>
        <button formaction="${path}/reset">Reset</button>
    </form>
    <hr />
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="d" items="${list}" varStatus="vs">
                <tr>
                    <td>${vs.count}</td>
                    <td>${d.id}</td>
                    <td>${d.name}</td>
                    <td>${d.description}</td>
                    <td><a href="${path}/edit/${d.id}">Edit</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
