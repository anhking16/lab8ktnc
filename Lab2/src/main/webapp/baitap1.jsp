<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TRANG CHỦ</title>
    <link href="lap2.css" rel="stylesheet"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="tong">
        <header>
         </header>
        <nav>
         <jsp:include page="menu.jsp"></jsp:include>
        </nav>
        <article> 
             <form action="baitap1" method="get">
             he so a <input type="text" name="txta">
             he so b<input type="text" name="txtb">
             <button> Tinh </button>
             </form>
		<h1> kết quả là = ${kq}</h1>
		 <h1> cách 2: <%= request.getAttribute("kq") %> </h1> 
      
        </article>
        <aside>
          <jsp:include page="menuaside.jsp"></jsp:include>
         </aside>
        
        <footer>
     <jsp:include page="menu.jsp"></jsp:include>
        </footer>
    </div>
</body>
</html>