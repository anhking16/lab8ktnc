<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TRANG CHỦ</title>
    <link href="dinhdang.css" rel="stylesheet"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="container">
        <header><center>CÔNG TY ABC</center> </header>
        <nav>
         <jsp:include page="menu.jsp"></jsp:include>
        </nav>
        <article> 
              <div class="item">
					<div class="item_item">
						<jsp:include page="item.jsp" >
							<jsp:param value="Anh 1" name="name"/>
							<jsp:param value="anh3.PNG" name="img"/>
						</jsp:include>
					</div>
		      </div>
		      
				<div class="item">
					<div class="item_item">
						<jsp:include page="item.jsp" >
							<jsp:param value="ANh 2" name="name"/>
							<jsp:param value="fpoly2.jpg" name="img"/>
						</jsp:include>
					</div>
				</div>
				<div class="item">
					<div class="item_item">
						<jsp:include page="item.jsp" >
							<jsp:param value="Anh 3" name="name"/>
							<jsp:param value="fpoly.jpg" name="img"/>
						</jsp:include>
					</div>
				</div>
				<div class="item">
					<div class="item_item">
						<jsp:include page="item.jsp" >
							<jsp:param value="Product 4" name="name"/>
							<jsp:param value="fpoly1.jpg" name="img"/>
						</jsp:include>
					</div>
				</div>  
				
				<div class="item">
					<div class="item_item">
						<jsp:include page="item.jsp" >
							<jsp:param value="Product 4" name="name"/>
							<jsp:param value="fpoly2.jpg" name="img"/>
						</jsp:include>
					</div>
				</div>            
		
        </article>
        <aside>
          <jsp:include page="menuphai.jsp"></jsp:include>
         </aside>
        
        <footer>
     <jsp:include page="menu.jsp"></jsp:include>
        </footer>
    </div>
</body>
</html>