<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쓰기</title>
</head>
<body>
	게시글 쓰기
	<hr>
	<a href="/">홈페이지</a>
	<a href="/board/list">게시글</a>
	<hr>
		<form action="/board/write" method="post">
		제목:<textarea rows="1" name='b_title'></textarea><br>
		내용:<textarea rows="3" name='b_content'></textarea><br>
		<input type="submit" value="글쓰기">
	</form>
</body>
</html>