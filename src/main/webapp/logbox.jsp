<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap" rel="stylesheet">
<title>logbox</title>
<style>
.logbox {
	display: flex;
	width: 100%;
	background: #dddddd;
	color: black;
	padding: 5px 15px;
	font-size: 20px;
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	border-radius: 10px;
}

.logbox-buttons a {
	margin-left: 15px;
	color: #d2ddaf;
	text-decoration: none;
	background: #717171;
	padding: 5px 10px;
	border-radius: 5px;
	font-size: 17px;
}

.logbox-buttons a:hover {
	background: #003f7f;
}

.logbox-time {
	margin-left: auto;
	padding-left: 20px;
}
</style>
</head>
<body>


<%-- ✅ 로그인 상태라면 sessionStorage에 시작 시간 저장 --%>
<c:if test="${not empty member}">
    <script>
        if (!sessionStorage.getItem("login-start-time")) {
            sessionStorage.setItem("login-start-time", new Date().getTime());
        }
    </script>
</c:if>

<%-- ✅ 로그인 상태에 따라 로그박스를 표시 --%>
<c:if test="${not empty member}">
	<div class="logbox">
		<span>${member.loginId} 님 환영합니다</span>
		<div class="logbox-buttons">
			<a href="/member/mypage.do">마이페이지</a> 
			<a href="/member/logout.do" id="logoutBtn">로그아웃</a>
		</div>
		<div class="logbox-time">
			총 접속시간: <span id="sessionTime">0초</span>
		</div>
	</div>
</c:if>

<script>
$(document).ready(function() {
    function updateSessionTime() {
        let startTime = sessionStorage.getItem("login-start-time");

        if (!startTime) {
            $("#sessionTime").text("0초");
            return;
        }

        let elapsedTime = Math.floor((new Date().getTime() - startTime) / 1000); // 초 단위 변환
        let hours = Math.floor(elapsedTime / 3600);
        let minutes = Math.floor((elapsedTime % 3600) / 60);
        let seconds = elapsedTime % 60;

        let timeString = hours + '시간 ' + minutes + '분 ' + seconds + '초';
        $("#sessionTime").text(timeString);
    }

    // ✅ 로그인한 경우에만 타이머 실행
    if (sessionStorage.getItem("login-start-time")) {
        setInterval(updateSessionTime, 1000);
        updateSessionTime();
    }

    // ✅ 로그아웃 버튼 클릭 시 sessionStorage 초기화
    $("#logoutBtn").click(function() {
        sessionStorage.removeItem("login-start-time");
        location.reload(); // ✅ 새로고침하여 로그인 상태 반영
    });
});
</script>

</body>
</html>
