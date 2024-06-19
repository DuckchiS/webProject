<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	로그인
	<hr>
	<a href="/">홈페이지</a>
	<a href="/member/signup">회원가입하러가기</a>
	<hr>
	<form action="loginProc" method="post" name="login">
	    <div>
	        <label for="username">아이디:</label>
	        <input type="text" id="username" name="username" required>
	    </div>
	    <div>
	        <label for="password">비밀번호:</label>
	        <input type="password" id="password" name="password" required>
	    </div>
		<input type="submit" value="login" />
	</form>
</body>
</html>