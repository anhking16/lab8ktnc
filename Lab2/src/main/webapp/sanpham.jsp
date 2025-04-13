<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="lap2.css" rel="stylesheet" />
</head>
<body>
<div class="tong">
		<header>

        </header>

        <nav>
	<jsp:include page="menu.jsp"></jsp:include>
        </nav>

        <article>
  			<div class="item">
  		<div class="hinh">
  			<jsp:include page="item.jsp">
  				<jsp:param value="Anh 1" name="name"/>
  				<jsp:param value="3.png" name="img"/>
  				</jsp:include>
  		</div>
  	</div>
  	
  		<div class="item">
  		<div class="hinh">
  			<jsp:include page="item.jsp">
  				<jsp:param value="Anh 1" name="name"/>
  				<jsp:param value="3.png" name="img"/>
  				</jsp:include>
  		</div>
  	</div>
  	
  		<div class="item">
  		<div class="hinh">
  			<jsp:include page="item.jsp">
  				<jsp:param value="Anh 1" name="name"/>
  				<jsp:param value="3.png" name="img"/>
  				</jsp:include>
  		</div>
  	</div>
  	
  		<div class="item">
  		<div class="hinh">
  			<jsp:include page="item.jsp">
  				<jsp:param value="Anh 1" name="name"/>
  				<jsp:param value="3.png" name="img"/>
  				</jsp:include>
  		</div>
  	</div>
  	
  	<div class="item">
  		<div class="hinh">
  			<jsp:include page="item.jsp">
  				<jsp:param value="Anh 1" name="name"/>
  				<jsp:param value="3.png" name="img"/>
  				</jsp:include>
  		</div>
  	</div>
        </article>

        <aside>
	<jsp:include page="menuaside.jsp"></jsp:include>
        </aside>

        <footer>

        </footer>
        </div>
</body>
</html>