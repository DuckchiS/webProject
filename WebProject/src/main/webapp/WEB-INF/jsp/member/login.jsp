<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/static/css/blog.css">
<script>
function validateForm() {
    var username = document.forms["login"]["username"].value;
    var password = document.forms["login"]["password"].value;
    if (username == "" || password == "") {
        alert("아이디와 비밀번호를 모두 입력하세요.");
        return false;
    }
    return true;
}
</script>
</head>
<body>
<div class="header">
    DuckchiS의 커뮤니티 게시판
</div>
<nav>
    <div class="nav-buttons">
        <a href="/">홈페이지</a>
    </div>
</nav>
<div class="container">
    <div class="login-container">
        <h2>로그인</h2>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <form action="loginProc" method="post" name="login" onsubmit="return validateForm()">
            <table>
                <tr>
                    <td><label for="username">아이디:</label></td>
                    <td><input type="text" id="username" name="username" required></td>
                </tr>
                <tr>
                    <td><label for="password">비밀번호:</label></td>
                    <td><input type="password" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="로그인"></td>
                </tr>
                <tr>
                    <td colspan="2"><a href="/member/signup">회원가입하러가기</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<footer class="footer">
    &copy; 2024 DuckchiS. All rights reserved.
</footer>
</body>