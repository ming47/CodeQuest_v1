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
	text-decoration: none;
}

.container {
	flex-direction: column;
	align-items: center;
	display: flex;
	width: 100%;
	height: 100%;
	justify-content: center;
	align-items: center;
	background: url('/images/밤.gif') no-repeat center;
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

.sidebar li {
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
	height: 80%;
}

.gamemenu {
	width: 15%;
	height: 200px;
	max-height: auto;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-left: 10px;
	margin-right: 50px;
	margin-top: 280px;
}

.sidebar {
	width: 305px;
	height: 70vh;
	border-radius: 10px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	background: url('/images/테마.gif') no-repeat center;
	background-size: cover;
}

.sidebar h2 {

    font-size: 17px;
    color: white;
    font-weight: bold;
    margin-bottom: 3px;

    padding: 20px;
    margin-left: 3px;
    margin-top: 47px;
    text-align: center;
}

.sidebar ul {
	list-style: none;
	padding: 0;
}

.sidebar ul li {
	padding: 20px;
	font-size: 16px;
	cursor: pointer;
	transition: background 0.3s;
}

.sidebar ul li:hover {
background: rgb(134, 61, 224, 0.3);
color: white;
}

.gamedetail {
	margin: -29px;
	margin-top: 22px;
	height: 800px;
}

#game-thumbnail {
	margin: 30px;
	width: 40%;
	height: 70vh;
}

.gameheader {
	width: 100%;
	height: 80vh;
	display: flex;
}

.gamepractice {
	margin-top: 40px;
	margin-left: 80px;
	margin-right: -27px;
	width: 35vw;
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

#gama_btn {
	padding: 40px;
}

.logbox-container {
	position: absolute;
	top: 80px;
	right: 20px;
	z-index: 1000;
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
					<li><a href="/game/list.do?id=800001">Game</a></li>
					<li><a href="/board/list.do">Board</a></li>
					<li><a href="/service/qna/addForm.do">Service</a></li>
				</ul>
			</div>

			<c:if test="${member.loginId != null}">
				<div class="logbox-container">
					<jsp:include page="/logbox.jsp" />

				</div>
			</c:if>
		</div>
		<div class="gamecontents">
			<div class="gamemenu">
				<div class="sidebar">
					<h2>👾Game List👾</h2>
					<ul>
						<c:forEach var="dto" items="${list}">
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
						<div class="practicebody"><img src="${game.gameDescript}"></div>
						<div class="practicebutton">
							<button id="game_btn">Game Start</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>
	</div>

	<script>
		$(document)
				.ready(
						function() {
							$('.sidebar ul li')
									.on(
											'click',
											function() {
												let gameId = $(this).attr(
														'value'); // 선택한 게임 ID 가져오기

												$
														.ajax(
																{
																	url : '/game/call.do?gameId='
																			+ gameId,
																	method : 'GET',
																	dataType : 'json'
																		
																})
														.done(
																function(data) {
																	if (!data)
																		return;

																	// gameheader 이미지 변경
																	$(
																			'.gameheader img')
																			.attr(
																					'src',
																					data.thumbnailImgPath);

																	// practicehead 업데이트 (게임 소개)
																	$(
																			'.practicehead')
																			.text(
																					data.introduce);

																	// practicebody 업데이트 (게임 설명)
																	$(
																			'.practicebody')
																			.text(
																					data.description);

																	// practicebutton 텍스트 변경
																	$(
																			'.practicebutton button')
																			.text(
																					data.buttonText);
																})
														.fail(
																function() {
																	console
																			.log('게임 정보를 불러오는 데 실패했습니다.');
																});
											});

							$("#game_btn")
									.on(
											"click",
											function(event) {
												let isLoggedIn = "${member.memberId}" !== "";
												let isBanned = "${member.isbanned}" == "true";
												if (!isLoggedIn) {
													if (confirm("회원만 게임하기가 가능합니다.\n로그인 하러 가시겠습니까?")) {
														location.href = "/";
													}
													event.preventDefault();
													return false;
												} else if (isBanned) {
													$
															.ajax(
																	{
																		url : '/service/member/ban/detail.do?id=${member.memberId}'
																	})
															.done(
																	function(
																			data) {
																		data = JSON
																				.parse(data);
																		parseDate(data.endDate);
																		let message = "현재 차단된 계정입니다.\n차단 이유: "
																				+ data.reason
																				+ "\n"
																				+ "차단 기간: "
																				+ parseDate(data.startDate)
																				+ " ~ "
																				+ parseDate(data.endDate);
																		alert(message);
																	});
													event.preventDefault();
													return false;
												}

												location.href = '/game/play.do?id=${game.gameId}';
											});
							function parseDate(timestamp) {
								const date = new Date(timestamp);
								return date.getFullYear() + '년 '
										+ Number(date.getMonth() + 1) + '월 '
										+ date.getDate() + '일 '
										+ date.getHours() + '시 '
										+ date.getMinutes() + '분';
							}
						});
	</script>
</body>
</html>
