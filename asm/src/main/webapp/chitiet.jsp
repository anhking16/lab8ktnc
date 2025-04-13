<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết bài tin</title>
   <link href="ASM.css" rel="stylesheet" />
</head>
<body>
    <div class="tong">
    <header>
    
    </header>
    <nav>
    	  <a href="Login.jsp">Trang chủ</a>
            <a href="vanhoa.jsp">Văn hóa</a>
            <a href="Phapluat.jsp">Pháp luật</a>
            <a href="TheThao.jsp">Thể thao</a>
    </nav>
    <article>

        <!-- Tiêu đề bài tin -->
        <div class="title">Tiêu đề bài tin</div>
	<br>
        <!-- Nội dung và hình ảnh -->
        <div class="content">
             <div class="hinh">
                <jsp:include page="item.jsp">
                    <jsp:param value="images.jpg" name="img"/>
                </jsp:include>
            </div>
            <div class="text-content">
                Nội dung bản tin... Nội dung bản tin... Nội dung bản tin... Nội dung bản tin... Nội dung bản tin... 
                Nội dung bản tin... Nội dung bản tin... Nội dung bản tin... Nội dung bản tin... Nội dung bản tin... 
                Nội dung bản tin... Nội dung bản tin... Nội dung bản tin... Nội dung bản tin... Nội dung bản tin... 
            </div>
        </div>
		 <a href="Login.jsp">Quay Lại</a>
        <!-- Thông tin ngày đăng và tác giả -->
        <div class="meta-info">Ngày đăng | Tác giả</div>
    </article>
    <aside>
    	      <!-- Tin cùng loại -->
        <div class="related-news">
            <h3>Tin cùng loại</h3>
            <ul>
                <li><a href="#">Tiêu đề bản tin cùng loại</a></li>
                <li><a href="#">Tiêu đề bản tin cùng loại</a></li>
                <li><a href="#">Tiêu đề bản tin cùng loại</a></li>
                <li><a href="#">Tiêu đề bản tin cùng loại</a></li>
                <li><a href="#">Tiêu đề bản tin cùng loại</a></li>
            </ul>
        </div>
    </aside>
  	<footer>
  	
  	</footer>
    </div>
</body>
</html>
