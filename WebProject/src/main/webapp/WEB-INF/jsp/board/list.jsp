<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="/static/css/blog.css">
</head>
<body>
<div class="header">
    DuckchiS의 커뮤니티 게시판
</div>
<nav>
    <div class="nav-buttons">
        <a href="/">홈</a>
        <sec:authorize access="isAuthenticated()">
            <a href="/board/write?category=${param.category}">쓰기</a>
            <a href="/member/logout">로그아웃 하기</a>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
        	<a href="/board/write?category=${param.category}">쓰기</a>
            <a href="/member/login">로그인</a>
        </sec:authorize>
    </div>
</nav>
<div class="container">
    <h2>게시판</h2>
    <hr>
    <table>
        <thead>
            <tr>
                <th>글번호</th>
                <th>아이디</th>
                <th>글제목</th>
                <th>조회수</th>
                <th>작성시간</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${list}">
            <tr>
                <td>${board.b_no}</td>
                <td>${board.m_nickname}</td>
                <td><a href="/board/read?b_no=${board.b_no}&category=${param.category}">${board.b_title}</a></td>
                <td>${board.b_hit}</td>
                <td>${board.b_datetime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    <!-- 검색 폼 추가 -->
    <form action="/board/list?category=${param.category}" method="get" class="search-form">
        <input type="text" name="keyword" placeholder="검색어를 입력하세요">
        <button type="submit">검색</button>
    </form>
    <hr>
    <div class="pagination">
        <!-- 이전 블럭 링크 -->
        <c:if test="${hasBlockPrev}">
            <a href="/board/list?currentPage=${prevPage}&category=${param.category}">이전</a>
        </c:if>
        
        <!-- 페이지 링크 -->
        <c:forEach var="i" begin="${blockStartPage}" end="${blockEndPage}">
            [<a href="/board/list?currentPage=${i}&category=${param.category}">${i}</a>] 
        </c:forEach>
        
        <!-- 다음 블럭 링크 -->
        <c:if test="${hasBlockNext}">
            <a href="/board/list?currentPage=${nextPage}&category=${param.category}">다음</a>
        </c:if>
    </div>
</div>
<footer class="footer">
    &copy; 2024 DuckchiS. All rights reserved.
</footer>
</body>
</html>