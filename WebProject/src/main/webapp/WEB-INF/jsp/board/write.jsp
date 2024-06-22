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
    	<a href="/board/list">게시글</a>
    </div>
</nav>
<div class="container">
    <div class="form-container">
        <h2>게시글 쓰기</h2>
        <form action="/board/write" method="post">
            <label for="b_title">제목:</label>
            <textarea id="b_title" name="b_title" rows="1" required></textarea><br>
            <label for="b_content">내용:</label>
            <textarea id="b_content" name="b_content" rows="3" required></textarea><br>
            <input type="submit" value="글쓰기">
        </form>
    </div>
</div>
<footer class="footer">
    &copy; 2024 DuckchiS. All rights reserved.
</footer>
</body>
</html>
