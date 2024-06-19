<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	회원가입
	<hr>
	<a href="/">홈페이지</a>
	<hr>
	<form action="/member/signupProc" method="post" name="signup">
		닉네임 : <input id="m_nickname" type="text" name="m_nickname" placeholder="nickname" /><br>
		아이디 : <input id="m_id" type="text" name="m_id" placeholder="id" /><br>
		비밀번호 : <input id="m_pw" type="password" name="m_pw" placeholder="password"><br>
		이름 : <input id="m_name" type="text" name="m_name" placeholder="name" /><br>
		이메일 : <input id="m_email" type="text" name="m_email" placeholder="email" /><br>
		전화번호 : <input id="m_number" type="text" name="m_number" placeholder="phoneNumber" /><br>
		<input type="submit" value="signup" />
	</form>
</body>
</html>