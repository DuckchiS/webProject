<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 읽기</title>
<link rel="stylesheet" href="/static/css/blog.css">
</head>
<body>
<div class="header">
    DuckchiS의 커뮤니티 게시판
</div>
<nav>
	<div class="nav-buttons">
	    <a href="/">홈</a>
	    <a href="/board/list?category=${category}">글 리스트</a>
	    <sec:authorize access="isAuthenticated()">
	        <a href="/board/write?category=${category}">새글 쓰기</a>
	        <a href="/member/logout">로그아웃 하기</a>
	    </sec:authorize>
	    <sec:authorize access="!isAuthenticated()">
	    	<a href="/board/write?category=${category}">새글 쓰기</a>
	        <a href="/member/login">로그인</a>
	    </sec:authorize>
    </div>
</nav>
<div class="container">
    <h2>글 읽기</h2>
    <div class="board-content-section">
        <h3>글제목: ${read.b_title}</h3>
		<div class="board-details">
			<p><span>글번호:</span> ${read.b_no}</p>
			<p><span>작성자:</span> ${read.m_nickname}</p>
			<p><span>조회수:</span> ${read.b_hit}</p>
			<p><span>작성시간:</span> ${read.b_datetime}</p>
			<p><span>수정시간:</span> ${read.b_updatetime}</p>
		</div>
        <div class="board-content">
            <p>${read.b_content}</p>
        </div>
        <div class="board-actions">
            <c:if test="${read.m_nickname == currentUsername}">
                <a href="/board/del?b_no=${read.b_no}&category=${category}">글 삭제</a>
                <a href="/board/modify?b_no=${read.b_no}&category=${category}">글 수정</a>
            </c:if>
        </div>
    </div>
	<sec:authorize access="isAuthenticated()">
	    <!-- 로그인한 경우에만 댓글 작성 폼을 표시 -->
	    <div class="reply-section">
	        <h3>댓글 작성</h3>
	        <form action="/board/reply" method="post" class="reply-form">
	            <input type="hidden" name="b_no" value="${read.b_no}">
	            <div>
	                <label for="r_content">내용:</label>
	                <textarea name="r_content" id="r_content" required></textarea>
	            </div>
	            <input type="hidden" name="m_nickname" value="${pageContext.request.userPrincipal.name}">
	            <button type="submit">댓글 작성</button>
	        </form>
	    </div>
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
	    <!-- 로그인하지 않은 경우에는 댓글 작성 폼을 표시하지 않음 -->
	    <p>댓글을 작성하려면 <a href="/member/login">로그인</a>해주세요.</p>
	</sec:authorize>
    <div class="reply-list-section">
        <h3>댓글 목록</h3>
        <div class="reply-list">
            <c:forEach var="reply" items="${replies}">
                <div class="reply-item">
                    <p><span>작성자:</span> ${reply.m_nickname}</p>
                    <p> ${reply.r_content}</p>
                    <p><span>작성시간:</span> ${reply.r_datetime}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<footer class="footer">
    &copy; 2024 DuckchiS. All rights reserved.
</footer>
</body>
</html>
