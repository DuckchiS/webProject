<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지</title>
</head>
<body>
DuckchiS의 커뮤니티 게시판 홈페이지
<hr>
<c:choose>
    <c:when test="${not empty username}">
        <p>Welcome, ${username}!</p>
        <a href="/member/logout">로그아웃 하기</a>
    </c:when>
    <c:otherwise>
        <a href="/member/login">로그인 하기</a>
        <hr>
        <a href="/member/signup">회원가입 하기</a>
    </c:otherwise>
</c:choose>
<hr>
<a href="/board/list">게시판 가기</a>
<hr>
</body>
</html>