<%@page import="com.example.web.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
<link rel="stylesheet" href="/static/css/blog.css">
</head>
<body>
<div class="header">
    DuckchiS의 커뮤니티 게시판
</div>
<nav>
    <div class="nav-buttons">
        <a href="/">홈</a>
        <a href="/board/list">글 리스트</a>
        <sec:authorize access="isAuthenticated()">
            <a href="/board/write">새글 쓰기</a>
            <a href="/member/logout">로그아웃 하기</a>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <a href="/member/login">로그인</a>
        </sec:authorize>
    </div>
</nav>
<div class="container">
    <h2>글 수정</h2>
    <div class="board-content-section">
        <h3>글제목: ${read.b_title}</h3>
        <div class="board-details">
            <p>글번호: ${read.b_no}</p>
        </div>
        <form action="/board/modify" method="post" class="modify-form">
            <input type="hidden" name="b_no" value="${read.b_no}">
            <div class="form-group">
                <label for="b_content">글내용:</label>
                <textarea name="b_content" id="b_content" rows="10" cols="50">${read.b_content}</textarea>
            </div>
            <div class="form-group">
                <input type="submit" value="수정하기">
            </div>
        </form>
    </div>
</div>
<footer class="footer">
    &copy; 2024 DuckchiS. All rights reserved.
</footer>
</body>
</html>