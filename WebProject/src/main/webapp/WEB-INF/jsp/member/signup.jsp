<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/static/css/blog.css">
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
    <div class="signup-container">
        <h2>회원가입</h2>
        <form action="/member/signupProc" method="post" name="signup">
            <table>
                <tr>
                    <td><label for="m_nickname">닉네임:</label></td>
                    <td><input id="m_nickname" type="text" name="m_nickname" placeholder="nickname" required style="width: 275px; margin-right: 5px;"></td>
                </tr>
                <tr>
                    <td><label for="m_id">아이디:</label></td>
                    <td><input id="m_id" type="text" name="m_id" placeholder="id" required style="width: 275px; margin-right: 5px;"></td>
                </tr>
                <tr>
                    <td><label for="m_pw">비밀번호:</label></td>
                    <td><input id="m_pw" type="password" name="m_pw" placeholder="password" required style="width: 275px; margin-right: 5px;"></td>
                </tr>
                <tr>
                    <td><label for="m_name">이름:</label></td>
                    <td><input id="m_name" type="text" name="m_name" placeholder="name" required style="width: 275px; margin-right: 5px;"></td>
                </tr>
                <tr>
                    <td><label for="m_email">이메일:</label></td>
                    <td>
                        <input id="m_email" type="text" name="m_email" placeholder="Email" required style="width: 160px; margin-right: 5px;">
                        <select id="email_domain" name="email_domain" required>
                            <option value="">선택</option>
                            <option value="@daum.net">@daum.net</option>
							<option value="@gmail.com">@gmail.com</option>
                            <option value="@hanmail.com">@hanmail.net</option>
                            <option value="@kakao.com">@kakao.com</option>
                            <option value="@naver.com">@naver.com</option>
                            <option value="@nate.com">@nate.com</option>
                            <!-- 추가적인 도메인을 여기서 선택할 수 있습니다 -->
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="m_number">전화번호:</label></td>
                    <td><input id="m_number" type="text" name="m_number" placeholder="phoneNumber" required oninput="formatPhoneNumber(this)" style="width: 275px; margin-right: 5px;"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="회원가입">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<script>
function formatPhoneNumber(input) {
    let value = input.value.replace(/\D/g, ''); // 숫자 외의 문자를 제거
    let formattedNumber = '';

    // 전화번호의 길이에 따라 형식을 조정
    if (value.length > 10) {
        // 11자리 전화번호 포맷 (010-1112-3333)
        formattedNumber = value.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
    } else if (value.length > 7) {
        // 8자리 전화번호 포맷 (010-111-3333)
        formattedNumber = value.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
    } else if (value.length > 3) {
        // 4자리 이상 전화번호 포맷 (010-111)
        formattedNumber = value.replace(/(\d{3})(\d{3})/, '$1-$2');
    } else {
        formattedNumber = value; // 3자리 이하는 그대로 두기
    }

    input.value = formattedNumber; // 포맷된 전화번호를 입력 필드에 반영
}
</script>
<footer class="footer">
    &copy; 2024 DuckchiS. All rights reserved.
</footer>
</body>
</html>