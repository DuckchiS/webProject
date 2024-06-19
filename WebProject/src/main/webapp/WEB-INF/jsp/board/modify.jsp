<%@page import="com.example.web.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>
	<%
	BoardDto read = (BoardDto)request.getAttribute("read");
	long bno = read.getB_no();
	String bTitle = read.getB_title();
	String btext = read.getB_content();
	%>	

	글번호:<%=bno %><br>
	<hr>
	<a href="/">홈페이지</a>
    <a href="/board/write">새글 쓰기</a>
	<hr>
	글제목:<%=bTitle %>
	<hr>
	글내용:	
	
	<form action="/board/modify" method="post">
	    <input type="hidden" name="b_no" value="${read.b_no}">
	    <textarea name="b_content" rows="10" cols="50">${read.b_content}</textarea>
	    <br>
	    <input type="submit" value="수정하기">
	</form>
</body>
</html>