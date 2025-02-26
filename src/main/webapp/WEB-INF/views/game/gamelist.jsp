<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">
<title>GameList</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.container {
	margin: auto;
	flex-direction: column;
	align-items: center;
	display: flex;
	width: 1660px;
	height: 1024px;
	justify-content: center;
	align-items: center;
	background: url('/allback.jpg') no-repeat center;
	background-size: cover;
	font-family: "Press Start 2P", serif;
}

.header, .footer {
	background: #1e201d;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0 20px;
	color: #b4c28a;
	font-family: "Press Start 2P", serif;
	font-weight: 400;
	font-style: normal;
	width: 100%;
}

.header {
	height: 80px;
	padding: 20px;
	position: relative;
}

.footer {
	height: 60px;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 14px;
	margin-top: 50px;
}

.header>.navi>.logo {
	font-size: 20px;
}

.navi {
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.menu {
	list-style: none;
	display: flex;
	gap: 20px;
}

.menu li a {
	padding: 10px 15px;
	background: #717171;
	color: white;
	border-radius: 5px;
	cursor: pointer;
	text-shadow: 0 1px 0 black, -1px 2px 0 black, 1px 4px 0 black, 0 3px 0
		black;
	text-decoration: none;
}

.menu li:hover a {
	background: #3c3b39;
	color: white;
}

.main-content {
	display: flex;
	width: 100%;
	justify-content: space-between;
	align-items: flex-start;
	margin-top: 20px;
}

.gamecontents {
	width: 100%;
	justify-content: space-evenly;
	display: flex;
	height: 100%;
}

.gamemenu {
	width: 15%;
	height: 500px;
	max-height: auto;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-right: 50px;
}

.sidebar {
	width: 230px;
	height: 310px;
	background: #f4f4f4;
	border-radius: 10px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.sidebar h2 {
	font-size: 33px;
	color: black;
	font-weight: bold;
	margin-bottom: 3px;
	border-bottom: 2px solid black;
	padding-bottom: 3px;
	margin-left: 3px;
}

.sidebar ul {
	list-style: none;
	padding: 0;
}

.sidebar ul li {
	padding: 10px;
	font-size: 16px;
	border-bottom: 1px solid #ddd;
	cursor: pointer;
	transition: background 0.3s;
}

.sidebar ul li:hover {
	background: #ddd;
}

.gamedetail {
	width: 75%;
	margin-right: 20px;
	height: 800px;
}

.gameheader {
	width: 100%;
	height: 800px;
	display: flex;
}

.gamepractice {
	margin-top: 50px;
	width: 500px;
	align-items: center;
	justify-content: center;
}

.practicehead {
	width: 100%;
	height: 20%;
	display: flex;
	align-items: center;
	justify-content: center;
	border: 1px solid black;
	background-color: white;
}

.practicebody {
	width: 100%;
	height: 60%;
	display: flex;
	align-items: center;
	justify-content: center;
	border: 1px solid black;
	background-color: white;
}

.practicebutton {
	height: 10%;
	display: flex;
	align-items: center;
	justify-content: center;
	border: 1px solid black;
	background-color: white;
}
</style>
</head>

<body>
	<div class="container">

		<div class="header">
			<div class="navi">
				<div class="logo">Team CodeQuest</div>
				<ul class="menu">
					<li><a href="/">Home</a></li>
					<li><a href="/game/list.do">Game</a></li>
					<li><a href="/board/list.do">Board</a></li>
					<li><a href="/service/list.do">Service</a></li>
				</ul>
			</div>
			<!-- ✅ 로그인 정보 -->
			<c:if test="${member.loginId != null}">
				<div class="logbox-container">
					<jsp:include page="/logbox.jsp" />

				</div>
			</c:if>
		</div>
		<div class="gamecontents">
			<div class="gamemenu">
				<div class="sidebar">
					<h2>Game List</h2>
					<ul>
					<c:forEach var="dto" items="${list }">
						<a href="/game/list.do?id=${dto.gameId}">
							<li>${dto.gameName}</li>
						</a>
					</c:forEach>
					</ul>
				</div>
			</div>
			<div class="gamedetail">
				<div class="gameheader">
					<img id="game-thumbnail" src="${game.gameThumb}">
					<div class="gamepractice">
						<div class="practicehead">${game.gameIntro}</div>
						<div class="practicebody">${game.gameDescript }</div>
						<div class="practicebutton">
							<a href="${game.gameGateway}"><button>Game Start</button></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ✅ 푸터 -->
		<div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>
	</div>

	<script>
		$(document).ready(function() {
			$('.sidebar ul li').on('click', function() {
				let gameId = $(this).attr('value'); // 선택한 게임 ID 가져오기

				$.ajax({
					url : '/game/call.do?gameId=' + gameId,
					method : 'GET',
					dataType : 'json'
				}).done(function(data) {
					if (!data)
						return;

					// gameheader 이미지 변경
					$('.gameheader img').attr('src', data.thumbnailImgPath);

					// practicehead 업데이트 (게임 소개)
					$('.practicehead').text(data.introduce);

					// practicebody 업데이트 (게임 설명)
					$('.practicebody').text(data.description);

					// practicebutton 텍스트 변경
					$('.practicebutton button').text(data.buttonText);
				}).fail(function() {
					console.log('게임 정보를 불러오는 데 실패했습니다.');
				});
			});
		});
	</script>

</body>

</html>
