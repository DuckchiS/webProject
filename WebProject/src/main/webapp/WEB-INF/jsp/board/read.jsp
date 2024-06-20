<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
	<!-- Spring Security를 이용한 로그인 상태 확인 후 댓글 작성 폼 보여주기 -->
	<sec:authorize access="isAuthenticated()">
	    <!-- 댓글 작성 폼 -->
	    <h3>댓글 작성</h3>
	    <form action="/board/reply" method="post">
	        <input type="hidden" name="b_no" value="${read.b_no}">
	        <div>
	            <label for="r_content">내용:</label>
	            <textarea name="r_content" id="r_content" required></textarea>
	        </div>
	        <!-- 작성자는 현재 로그인된 사용자의 이름으로 자동 입력 -->
	        <input type="hidden" name="m_nickname" value="${pageContext.request.userPrincipal.name}">
	        <button type="submit">댓글 작성</button>
	    </form>
	    <hr>
	</sec:authorize>
    
    <!-- 댓글 목록 표시 -->
    <h3>댓글 목록</h3>
    <c:forEach var="reply" items="${replies}">
        <div>
            <p>작성자: ${reply.m_nickname}</p>
            <p>내용: ${reply.r_content}</p>
            <p>작성시간: ${reply.r_datetime}</p>
            <hr>
        </div>
    </c:forEach>
</body>
</html>