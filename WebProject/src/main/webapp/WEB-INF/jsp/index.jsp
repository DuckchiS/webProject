<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지</title>
<link rel="stylesheet" href="/static/css/blog.css" type="text/css">
</head>
<body>
<div class="header">
    DuckchiS의 커뮤니티 게시판
</div>
<nav>
    <div class="flex-container">
        <c:choose>
            <c:when test="${not empty username}">
                <div class="welcome-message">Welcome, ${username}!</div>
                <div class="nav-buttons">
                    <a href="/member/logout">로그아웃 하기</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="nav-buttons">
                    <a href="/member/login">로그인 하기</a>
                    <a href="/member/signup">회원가입 하기</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</nav>
<div class="container">
    <h2>커뮤니티 광장</h2>
    <hr>
    <a href="/board/list">게시판 가기</a>
</div>
<footer class="footer">
    &copy; 2024 DuckchiS. All rights reserved.
</footer>
</body>
</html>
</html>