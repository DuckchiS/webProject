<%@page import="com.example.web.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <a href="/board/write?category=${param.category}">새글 쓰기</a>
            <a href="/member/logout">로그아웃 하기</a>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <a href="/board/write?category=${param.category}">새글 쓰기</a>
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
        <form action="/board/modify?category=${param.category}" method="post" class="modify-form" enctype="multipart/form-data">
            <input type="hidden" name="b_no" value="${read.b_no}">
            <div class="form-group">
                <label for="b_content">글내용:</label>
                <textarea name="b_content" id="b_content" rows="10" cols="50" style="width: 100%; height: 200px; resize: none;">${read.b_content}</textarea>
            </div>
            <div class="form-group">
                <label for="b_image">이미지 업로드:</label>
                <input type="file" id="b_image" name="b_image" accept="image/*">
            </div>
            <c:if test="${not empty read.b_image}">
                <h3>기존 이미지:</h3>
                <img src="data:image/jpeg;base64,${read.getB_imageAsBase64()}" alt="게시글 이미지" style="max-width: 100%; height: auto;" />
            </c:if>
            <c:if test="${not empty param.error}">
                <div class="error-message">이미지 업로드 중 오류가 발생했습니다. 다시 시도해 주세요.</div>
            </c:if>
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