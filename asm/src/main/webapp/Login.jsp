<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>News Reader</title>
  <link href="ASM.css" rel="stylesheet" />
</head>
<body>
<div class="tong">

    <!-- Header -->
    <header>
      
    </header>
    
    <nav>
  <a href="Login.jsp">Trang chủ</a>
            <a href="vanhoa.jsp">Văn hóa</a>
            <a href="Phapluat.jsp">Pháp luật</a>
            <a href="TheThao.jsp">Thể thao</a>
    </nav>
    
    <article>
        <br>
        <div class="item">
            <div class="hinh">
                <jsp:include page="item.jsp">
                    <jsp:param value="images.jpg" name="img"/>
                </jsp:include>
            </div>
            <div class="thongtin">
                <h3>Thông tin chi tiết</h3>
                <p>Một số mô tả ngắn gọn về tin tức này...</p>
                <!-- Sử dụng ID bài viết truyền qua URL -->
                <a href="chitiet.jsp?id=1">Đọc tiếp</a>
            </div>
        </div>
        <hr>
         <div class="item">
            <div class="hinh">
                <jsp:include page="item.jsp">
                    <jsp:param value="images.jpg" name="img"/>
                </jsp:include>
            </div>
            <div class="thongtin">
                <h3>Thông tin chi tiết</h3>
                <p>Một số mô tả ngắn gọn về tin tức này...</p>
                <a href="chitiet.jsp?id=2">Đọc tiếp</a>
            </div>
        </div>
        <hr>
         <div class="item">
            <div class="hinh">
                <jsp:include page="item.jsp">
                    <jsp:param value="images.jpg" name="img"/>
                </jsp:include>
            </div>
            <div class="thongtin">
                <h3>Thông tin chi tiết</h3>
                <p>Một số mô tả ngắn gọn về tin tức này...</p>
                <a href="chitiet.jsp?id=3">Đọc tiếp</a>
            </div>
        </div>
    </article>
    
    <aside>
        <!-- Nội dung thay đổi theo trang -->
        <section>
            <h3>5 bản tin được xem nhiều nhất</h3>
            <ul>
                <li>Tin 1</li>
                <li>Tin 2</li>
                <li>Tin 3</li>
                <li>Tin 4</li>
                <li>Tin 5</li>
            </ul>
        </section>

        <section>
            <h3>5 bản tin mới nhất</h3>
            <ul>
                <li>Tin 6</li>
                <li>Tin 7</li>
                <li>Tin 8</li>
                <li>Tin 9</li>
                <li>Tin 10</li>
            </ul>
        </section>

        <section>
            <h3>5 bản tin đã xem gần đây nhất</h3>
            <ul>
                <li>Tin 11</li>
                <li>Tin 12</li>
                <li>Tin 13</li>
                <li>Tin 14</li>
                <li>Tin 15</li>
            </ul>
        </section>
    </aside>
    
    <!-- Footer -->
    <footer>
        <p>Thiết kế tự do</p>
    </footer>
</div>
</body>
</html>
