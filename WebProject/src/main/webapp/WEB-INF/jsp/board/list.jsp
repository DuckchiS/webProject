<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
    게시판
    <hr>
    <a href="/">홈페이지</a>
    <a href="/board/write">새글 쓰기</a>
    <hr>
    <table>
        <tr>
            <td>글번호</td>
            <td>아이디</td>
            <td>글제목</td>
            <td>조회수</td>
            <td>작성시간</td>
        </tr>
    <!-- jstl 로 처리하면 더 짧게 가능 -->
    <c:forEach var="board" items="${list}">
        <tr>
            <td>${board.b_no}</td>
            <td>${board.m_nickname}</td>
            <td><a href="/board/read?b_no=${board.b_no}">${board.b_title}</a></td>
            <td>${board.b_hit}</td>
            <td>${board.b_datetime}</td>
        </tr>
    </c:forEach>
    </table>
    <hr>
    <!-- 이전 블럭 링크 -->
    <c:if test="${hasBlockPrev}">
        <a href="/board/list?currentPage=${prevPage}">이전</a>
    </c:if>
    
    <!-- 페이지 링크 -->
    <c:forEach var="i" begin="${blockStartPage}" end="${blockEndPage}">
        [<a href="/board/list?currentPage=${i}">${i}</a>] 
    </c:forEach>
    
    <!-- 다음 블럭 링크 -->
    <c:if test="${hasBlockNext}">
        <a href="/board/list?currentPage=${nextPage}">다음</a>
    </c:if>
</body>
</html>