<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <jsp:include page="index.jsp"></jsp:include>
<hr/>
<ul> 
 <li>Title: ${fn:toUpperCase(item.title)}</li> 
 <li>Content:  
  <c:choose> 
	   <c:when test="${fn:length(item.content) > 100}"> 
	    ${fn:substring(item.content, 0, 100)}... 
	   </c:when> 
	   <c:otherwise>${item.content}</c:otherwise> 
  </c:choose>
<c:forTokens var="blood" items="A B AB O" delims=" ">
	<label> <input type="radio" Name="rdo"> ${blood}</label>
</c:forTokens>
 </li> 
</ul> 
</body>
</html>