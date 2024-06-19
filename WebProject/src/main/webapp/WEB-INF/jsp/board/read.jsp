<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>읽기</title>
</head>
<body>
	글 읽기
	<hr>
	글제목:${read.b_title}
	<hr>
	글번호:${read.b_no}
	아이디:${read.m_nickname}
	조회수:${read.b_hit} 
	작성시간:${read.b_datetime}
	수정시간:${read.b_updatetime}
	<hr>
	글내용:${read.b_content}
	<hr>
	<a href="/board/del?b_no=${read.b_no}">글 삭제</a>
	<a href="/board/modify?b_no=${read.b_no}">글 수정</a>
	<a href="/board/list">글 리스트</a>
</body>
</html>