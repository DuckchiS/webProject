<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쓰기</title>
<link rel="stylesheet" href="/static/css/blog.css">
</head>
<body>
<div class="header">
    DuckchiS의 커뮤니티 게시판
</div>
<nav>
	<div class="nav-buttons">
    	<a href="/">홈</a>
    	<a href="/board/list?category=${param.category}">게시글</a>
    </div>
</nav>
<div class="container">
    <div class="form-container">
        <h2>게시글 쓰기</h2>
        <form action="/board/write" method="post">
        	<!-- 숨겨진 필드로 카테고리 설정 -->
            <input type="hidden" name="b_category" value="${param.category}">
            
	        <label for="b_title">제목:</label>
	        <textarea id="b_title" name="b_title" rows="1" required style="width: 100%; height: 40px; resize: none;"></textarea><br>
	
	        <label for="b_content">내용:</label>
	        <textarea id="b_content" name="b_content" rows="3" required style="width: 100%; height: 150px; resize: none;"></textarea><br>

            <input type="submit" value="글쓰기">
        </form>
    </div>
</div>
<footer class="footer">
    &copy; 2024 DuckchiS. All rights reserved.
</footer>
</body>
</html>
