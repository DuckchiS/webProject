<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <h2>글제목: ${read.b_title}</h2>
    <div class="board-content-section">
		<div class="board-details">
			<p><span>글번호:</span> ${read.b_no}</p>
			<p><span>작성자:</span> ${read.m_nickname}</p>
			<p><span>조회수:</span> ${read.b_hit}</p>
			<p><span>작성시간:</span> ${read.b_datetime}</p>
			<p><span>수정시간:</span> ${read.b_updatetime}</p>
		</div>
        <div class="board-content">
            <p>${read.b_content}</p>
            <!-- 업로드된 이미지 표시 -->
			<c:if test="${not empty read.getB_imageAsBase64()}">
			    <img src="data:image/jpeg;base64,${read.getB_imageAsBase64()}" alt="게시글 이미지" style="max-width: 100%; height: auto;" />
			</c:if>
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
	            <input type="hidden" name="replySubmitted" value="true"> <!-- 댓글 작성 여부 플래그 추가 -->
	            <div style="display: flex; align-items: center; justify-content: center;">
	                <label for="r_content">내용:</label>
	                <textarea name="r_content" id="r_content" required style="width: 300px; height: 20px; resize: none;"></textarea>
	            </div>
	            <input type="hidden" name="m_nickname" value="${pageContext.request.userPrincipal.name}">
	            <button type="submit" style="margin-top: 10px;">댓글 작성</button>
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
	                <p>${reply.r_content}</p>
	                <p><span>작성시간:</span> ${reply.r_datetime}</p>
	            </div>
	        </c:forEach>
	
	        <c:if test="${empty replies}">
	            <p>댓글이 없습니다.</p> <!-- 댓글이 없을 때 메시지 추가 -->
	        </c:if>
	    </div>
	
	    <!-- 페이징 버튼 -->
		<div class="pagination">
		    <c:if test="${currentPage > 1}">
		        <a href="/board/read?b_no=${read.b_no}&page=${currentPage - 1}&category=${category}">이전</a>
		    </c:if>
		
		    <c:forEach begin="1" end="${totalPages}" var="page">
		        <c:choose>
		            <c:when test="${page == currentPage}">
		                <span>${page}</span> <!-- 현재 페이지 표시 -->
		            </c:when>
		            <c:otherwise>
		                <a href="/board/read?b_no=${read.b_no}&page=${page}&category=${category}">${page}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		
		    <c:if test="${currentPage < totalPages}">
		        <a href="/board/read?b_no=${read.b_no}&page=${currentPage + 1}&category=${category}">다음</a>
		    </c:if>
		</div>
	</div>
</div>
<footer class="footer">
    &copy; 2024 DuckchiS. All rights reserved.
</footer>
</body>
</html>