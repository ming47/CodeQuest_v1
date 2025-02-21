<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">
<title>Responsive Game Portal</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	display: flex;
	width: 100%;
	height: 100%;
	justify-content: center;
	align-items: center;
	background: url('/allback.jpg') no-repeat center;
	background-size: cover;
}

.container {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
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

/* ✅ 오른쪽 영역 (로그인 박스 + 랭킹보드) */
.right-content {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: flex-start; /* ✅ 상단 정렬 문제 해결 */
	flex: 1;
	min-width: 300px;
}

/* ✅ 본문 (게임 리스트 + 게시판) */
.body {
	width: 70%;
	display: flex;
	flex-direction: column;
	align-items: center;
}

/* ✅ 로그인 박스 */
.loginbox {
	width: 80%;
	background: url('/login.jpg') no-repeat center;
	background-size: cover;
	padding: 10px;
	border-radius: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	font-family: "Jua", serif;
	margin-bottom: 50px;
	margin-top: 80px;
	background: url('/login.jpg') no-repeat center;
	background-size: cover;
	padding: 10px;
	border-radius: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	font-family: "Jua", serif;
	margin-bottom: 50px;
	margin-top: 80px;
	margin-right: 75px;
}

/* ✅ 로그인 버튼 및 입력 필드 배치 */
.loginbox h2 {
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	margin: 10px;
	color: #2f2b2b;
}

.loginbox input {
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	width: 70%;
	padding: 10px;
	margin: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

/* ✅ 아이디 / 비밀번호 입력 필드 + 로그인 버튼 정렬 */
.loginbox .input-group {
	display: flex;
	align-items: center;
	gap: 5px;
	justify-content: center;
}

.loginbox .input-group input {
	width: 60%;
	font-size: 13px;
}

.loginbox .input-group button {
	width: 30%;
	height: 40px;
	font-size: 16px;
	background: #5e5d5a;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background 0.3s;
}

.loginbox .input-group button:hover {
	background: #3c3b39;
}

.login-links button {
	margin: 10px;
	border-radius: 10px;
	padding: 8px;
	transition: background 0.3s;
	font-weight: bold;
	background: #717171;
	color: white;
	font-family: "Jua", serif;
	font-size: 17px;
}

.login-links button:hover {
	background: #003f7f;
	transform: scale(1.1);
}

.login-links {
	margin: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
}

/* ✅ 로그인 전 랭킹보드 기본 크기 */
.rankingboard {
	width: 80%;
	height: 500px;
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	transition: all 0.5s ease-in-out;
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	font-size: 20px;
	margin-top: 30px;
	margin-right: 80px;
}

/* ✅ 로그인 후 랭킹보드 크기 조정 (body의 절반) */
.rankingboard.expanded {
	width: 80%;
	height: 50%;
	padding: 30px;
	margin-top: 40px;
}

/* ✅ 랭킹 탭 버튼 스타일 */
.ranking-tabs {
	display: flex;
	justify-content: center;
	gap: 15px;
	margin-bottom: 10px;
	font-size: 17px;
}

.loginbox h2 {
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	margin: 10px;
	color: #2f2b2b;
}

.tab-btn {
	padding: 8px 12px;
	background: #ddd;
	border: none;
	cursor: pointer;
	border-radius: 5px;
	transition: 0.3s;
	font-family: "Press Start 2P", serif;
	font-weight: 400;
	font-style: normal;
	font-size: 10px;
	margin-top: 10px;
}

.tab-btn.active {
	background: #5e5d5a;
	color: white;
}

/* ✅ 각 게임별 랭킹 리스트 숨김 */
.hidden {
	display: none;
}

.loginBtn:hover {
	background: #919190
}

.logbox-container {
	position: absolute;
	right: 10px;
	bottom: -35px;
}

.rankingboard ul {
	list-style: none;
	padding: 0;
}

.rankingboard li {
	padding: 5px;
	font-size: 17px;
}

/* ✅ 게임 리스트 */
.gameList {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	gap: 25px;
	padding: 10px;
	width: 90%;
	font-family: "Press Start 2P", serif;
	margin-top: 30px;
	text-shadow: 0 1px 0 #a3a3a3, -1px 2px 0 #a3a3a3, 1px 4px 0 #a3a3a3, 0
		3px 0 #a3a3a3;
}

.game {
	min-width: 280px;
	background: white;
	padding: 15px;
	border-radius: 10px;
	text-align: center;
	scroll-snap-align: start;
	transition: transform 0.3s ease-in-out;
}

.game:hover {
	transform: scale(1.05);
}

.game img {
	width: 100%;
	height: 160px;
	border-radius: 10px;
}

.game h3 {
	margin: 10px;
}

.game p {
	margin: 10px;
}

.game button {
	width: 170px;
	height: 60px;
	padding: 10px;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background 0.3s;
	font-family: "Press Start 2P", serif;
	font-weight: 400;
	font-style: normal;
	background: url('/gamebtn.png') no-repeat center;
	background-size: cover;
}

.game button:hover {
	transform: scale(1.2);
}

.boardlist {
	width: 90%;
	background: url('/board.jpg') no-repeat center;
	background-size: cover;
	padding: 20px;
	border-radius: 10px;
	text-align: center;
	color: white;
	margin-top: 20px;
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	font-size: 20px;
	text-shadow: 0 1px 0 black, -1px 2px 0 black, 1px 6px 0 black, 0 3px 0
		black;
}

.boardlist h3 {
	margin-bottom: 20px;
}

.boardlist li {
	margin-bottom: 10px;
}
</style>
</head>


<body>
	<div class="starter" style="display: flex">
		<%@ include file="intro.jsp"%>
	</div>

	<div class="container" style="display: none">

		<!-- ✅ 헤더 -->
		<div class="header">
			<div class="navi">
				<div class="logo">Team CodeQuest</div>
				<ul class="menu">
					<li><a href="/">Home</a></li>
					<li><a href="/game/list.do">Service</a></li>
					<li><a href="/board/list.do">Board</a></li>
					<li><a href="/service/list.do">Service</a></li>
				</ul>
			</div>
			<!-- ✅ 로그인 정보 -->
			<c:if test="${member.loginId != null}">
				<div class="logbox-container">
					<%@ include file="logbox.jsp"%>
				</div>
			</c:if>
		</div>


		<!-- ✅ 좌우 배치 레이아웃 -->
		<div class="main-content">

			<!-- ✅ 본문 (게임 리스트 + 게시판) -->
			<div class="body">
				<div class="gameList">
					<div class="game">
						<img src="game1.jpg">
						<h3>Game 1</h3>
						<p>RPG</p>
						<button>Play</button>
					</div>
					<div class="game">
						<img src="game2.jpg">
						<h3>Game 2</h3>
						<p>FPS</p>
						<button>Play</button>
					</div>
					<div class="game">
						<img src="game3.jpg">
						<h3>Game 3</h3>
						<p>Action</p>
						<button>Play</button>
					</div>
					<div class="game">
						<img src="game4.jpg">
						<h3>Game 4</h3>
						<p>Adventure</p>
						<button>Play</button>
					</div>
					<div class="game">
						<img src="game5.jpg">
						<h3>Game 5</h3>
						<p>Strategy</p>
						<button>Play</button>
					</div>
					<div class="game">
						<img src="game6.jpg">
						<h3>Game 6</h3>
						<p>Sports</p>
						<button>Play</button>
					</div>
				</div>

				<div class="boardlist">
					<h3>📢 최근 게시물</h3>
					<ul>
						<li>게시글 1</li>
						<li>게시글 2</li>
						<li>게시글 3</li>
					</ul>
				</div>
			</div>

			<!-- ✅ 오른쪽 로그인 + 랭킹보드 -->

			<div class="right-content">
				<c:if test="${member.loginId==null}">
					<div class="loginbox">
						<h2>로그인</h2>
						<form action="/member/login.do" method="post" id="frm">
							<div class="input-group">
								<input type="text" name="id" id="id" placeholder="아이디">
								<input type="password" name="pw" id="pw" placeholder="비밀번호">
								<button id="loginBtn">로그인</button>
							</div>
						</form>

						<div class="login-links">
							<a href="/member/addForm.do"><button>회원가입</button></a><br>
							<button type="button" id="pwFinder">비밀번호 재설정</button>
						</div>
					</div>
				</c:if>
				<div class="rankingboard">
					<h3>🏆 랭킹 보드</h3>

					<div class="ranking-tabs">
						<button class="tab-btn active" data-game="game1">Game 1</button>
						<button class="tab-btn" data-game="game2">Game 2</button>
						<button class="tab-btn" data-game="game3">Game 3</button>
					</div>

					<div class="ranking-content">
						<ul class="ranking-list" id="game1">
							<li>1위 - 플레이어1 (1200점)</li>
							<li>2위 - 플레이어2 (1100점)</li>
							<li>3위 - 플레이어3 (1050점)</li>
						</ul>
						<ul class="ranking-list hidden" id="game2">
							<li>1위 - 플레이어A (2000점)</li>
							<li>2위 - 플레이어B (1800점)</li>
							<li>3위 - 플레이어C (1750점)</li>
						</ul>
						<ul class="ranking-list hidden" id="game3">
							<li>1위 - 플레이어X (900점)</li>
							<li>2위 - 플레이어Y (850점)</li>
							<li>3위 - 플레이어Z (800점)</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<!-- ✅ 푸터 -->
		<div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>

	</div>

	<script>
		$("#pwFinder").on("click", function() {
			window.open("/member/pwResetForm.do", "", "width=400, height=300");
		});

		$(document).ready(
				function() {

					if (sessionStorage.getItem("enteredGame") === "true") {
						$(".starter").hide();
						$(".container").show();
					}

					function loadRanking(gameId) {
						console.log(gameId);

						$.ajax({
							url : "/score/list/game.do?id=" + gameId,
							type : "GET",
							dataType : "json"
						}).done(
								function(data) {
									console.log(data);
									let rankingList = $('.ranking-list');

									rankingList.html('');
									if (!data || data.length === 0) {
										rankingList
												.append("<li>랭킹 데이터 없음</li>");
										return;
									}

									for (let i = 0; i < 10; i++) {
										console.log(data[i]);

										const li = $('<li>').html(
												i + 1 + '위 ' + data[i].user
														+ '(' + data[i].score
														+ '점)');
										$('.ranking-list').append(li);
									}
									/*
									 $.each(data, function(index, player) {
									 console.log(index, player);
									
									 const li = $('<li>').html(index + 1 + '위 ' + player.user + '(' + player.score + '점)');
									 $('.ranking-list').append(li);
									 // rankingList.append(`<li>${index + 1}위 - ${player.user} (${player.score}점)</li>`);
									 });
									 */
								}).fail(function(xhr, status, error) {
							console.log("랭킹 데이터 불러오기 실패:", error);
						});
					}

					// ✅ 초기에 첫 번째 게임 랭킹 불러오기
					let defaultGameId = "800001"; // ✅ 초기값 설정
					loadRanking(defaultGameId);

					// ✅ 랭킹 탭 클릭 시 해당 게임 랭킹 로드
					$(".tab-btn").click(function() {
						$(".tab-btn").removeClass("active");
						$(this).addClass("active");

						let gameId = $(this).data("game");

						// ✅ "game1" → "1"로 변환
						if (gameId.startsWith("game")) {
							gameId = gameId.replace("game", "");
							gameId = Number(80000 + gameId);
						}
						loadRanking(gameId);
					});
				});
	</script>

</body>
</html>