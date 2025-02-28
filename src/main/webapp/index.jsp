<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.4/kakao.min.js"
	integrity="sha384-DKYJZ8NLiK8MN4/C5P2dtSmLQ4KwPaoqAfyA/DfmEc1VDxu4yyC7wy6K1Hs90nka"
	crossorigin="anonymous"></script>

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

.container {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100%;
	height: 100%;
	justify-content: center;
	align-items: center;
	background: url('/images/밤.gif') no-repeat center;
	background-size: cover;
}

.header, .footer {
	background: #1e201d;
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
	position: fixed;
	left: 0;
	height: 80px;
	padding: 20px;
	display: flex;
	align-items: center;
	justify-content: center;
	background: #1e201d;
	top: 0;
	z-index: 10;
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
	margin-top: 15px;
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	position: fixed;
	top: 0;
	left: 0;
	padding: 15px 20px;
	z-index: 1000;
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

.right-content {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: flex-start;
	flex: 1;
	min-width: 300px;
}

.body {
	width: 70%;
	display: flex;
	flex-direction: column;
	align-items: center;
	position: relative;
	top: 60px;
}

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
	margin: 3px;
	border-radius: 10px;
	padding: 8px;
	transition: background 0.5s;
	font-weight: bold;
	background: #717171;
	color: white;
	font-family: "Jua", serif;
	font-size: 15px;
}

.login-links button:hover {
	background: #000000;
	transform: scale(1.1);
}

.login-links {
	margin: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 10px;
}

.loginbox {
	background-size: cover;
	padding: 10px;
	border-radius: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	font-family: "Jua", serif;
	background: url('/images/login.jpg') no-repeat center;
	background-size: cover;
	padding: 10px;
	border-radius: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	font-family: "Jua", serif;
	margin-bottom: 50px;
	position: relative;
	top: 150px;
	left:10%;
	margin-right: 30%;
}

.rankingboard {
	height: 445px;
	min-height: 300px;
	background-color: rgba(255, 255, 255, 0.9);
	padding: 51px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
	transition: all 0.5s ease-in-out;
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	font-size: 20px;
	position: relative;
	margin-right: 30%;
	top: 160px;
	left:10%;
}

.rankingboard h3 {
	font-size: 40px;
	margin-top: -10px;
}

.rankingboard li {
	margin: 12px;
}

.ranking-tabs {
	display: flex;
	justify-content: center;
	flex-wrap: wrap;
	gap: 8px;
	margin-bottom: 10px;
	margin-top: 10px;
	width: 100%;
	max-width: 100%;
	overflow: hidden;
}

.tab-btn {
	padding: 5px 8px;
	background: #ddd;
	border: none;
	cursor: pointer;
	border-radius: 5px;
	transition: 0.3s;
	font-family: "Press Start 2P", serif;
	font-size: 10px;
	margin-top: 5px;
	white-space: nowrap;
}

.tab-btn.active {
	background: #5e5d5a;
	color: white;
}

.loginbox h2 {
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	margin: 10px;
	color: #2f2b2b;
}

.hidden {
	display: none;
}

.loginBtn:hover {
	background: #919190
}

.logbox-container {
	position: absolute;
	position: fixed;
	top: 80px;
	right: 20px;
	z-index: 1000;
}

.rankingboard ul {
	list-style: none;
	padding: 0;
}

.rankingboard li {
	padding: 5px;
	font-size: 17px;
}

.gameList {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	gap: 25px;
	padding: 10px;
	width: 90%;
	font-family: "Press Start 2P", serif;
	margin-top: 58px;
}

.game {
	border: 3px;
	color: black;
	min-width: 280px;
	width: 300px;
	padding: 15px;
	border-radius: 10px;
	text-align: center;
	scroll-snap-align: start;
	transition: transform 0.3s ease-in-out;
	background-color: rgba(255, 255, 255, 0.5);
	margin: 10px;
	position: relative;
}

.game:hover {
	transform: scale(1.05);
}

.game img {
	border-radius: 10px;
	width: 250px;
	height: 200px;
	object-fit: cover;
	transition: opacity 0.5s ease; /* 부드러운 전환 */
	margin: 0 auto;
}

.game:hover img {
	opacity: 0;
	width: 250px;
	height: 200px;
	margin: 0 auto;
}

.game:hover .hover-img {
	opacity: 1;
	width: 250px;
	height: 200px;
	left: 23px;
	margin: 0 auto;
}

.hover-img {
	width: 250px;
	height: 200px;
	position: absolute;
	left: 23px;
	opacity: 0;
	margin: 0 auto;
}

.game h3 {
	margin: 10px;
	font-size: 15px;
}

.game p {
	margin: 10px;
	font-size: 10px;
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
	font-size: 15px;
	font-style: normal;
	background: url('/images/gamebtn.png') no-repeat center;
	background-size: cover;
}

.game button:hover {
	transform: scale(1.2);
}

.boardlist {
	width: 100%;
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
	margin: 0 auto;
}

.boardlist h3 {
	margin-bottom: 20px;
}

.boardlist li {
	margin-bottom: 10px;
}

table {
	border-radius: 5px;
	width: 70%;
	border: 1px;
	color: black;
	border-collapse: collapse;
	border-spacing: 4px;
	border-collapse: separate;
	font-size: 15px;
	justify-content: center;
	margin: 0 auto;
}

.bottombody {
	width: 100%;
	margin-top: 70px;
	height: 50%;
}

thead, tbody {
	width: 100%;
}

#title {
	width: 100%;
}

td, th {
	border-radius: 2px;
	border: none;
	width: 100%;
	heghit: 48px;
	text-align: center;
	font-family: 'DungGeunMo';
	font-weight: bold;
	background-color: rgba(255, 255, 255, 0.8);
}

th {
	font-size: 20px;
}

td a {
	text-decoration: none;
	color: black;
	transition: color 0.3s ease;
}

td.clicktitle:hover {
	transform: scale(1.1);
	background-color: #f0f0f0; table tr { border-radius : 1px;
	font-family: 'DungGeunMo';
	height: 48px;
}
</style>
<c:if test="${member.memberId != null}">
	<style>
#ranking {
	margin-top: 40px;
	width: 85%;
	height: 660px;
}
</style>
</c:if>
</head>


<body>
	<div class="starter" style="display: flex">
		<%@ include file="intro.jsp"%>
	</div>

	<div class="container" style="display: none">



		<div class="header">
			<div class="navi">
				<div class="logo">Team CodeQuest</div>
				<ul class="menu">
					<li><a href="/">Home</a></li>
					<li><a href="/game/list.do?id=800001">Game</a></li>
					<li><a href="/board/list.do">Board</a></li>
					<c:choose>
						<c:when test="${member.role == 'admin'}">
							<li><a href="/service/admin/main.do">Service</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/service/qna/addForm.do">Service</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>

			<c:if test="${member.memberId != null}">
				<div class="logbox-container">
					<%@ include file="logbox.jsp"%>
				</div>
			</c:if>
		</div>
		<div class="main-content">
			<div class="body">

				<div class="gameList">
					<div class="game">
						<img class="hover-img" src="/images/coin.gif" alt="호버 이미지">
						<img src="/images/skipstone.png">

						<h3>Skipping Stone</h3>
						<p>Action</p>
						<a href="/game/list.do?id=800001"><button>Insert Coin</button></a>
					</div>
					<div class="game">
						<img class="hover-img" src="/images/coin.gif" alt="호버 이미지">
						<img src="/images/2048.png">
						<h3>2048</h3>
						<p>Puzzle</p>
						<a href="/game/list.do?id=800002"><button>Insert Coin</button></a>
					</div>
					<div class="game">
						<img class="hover-img" src="/images/coin.gif" alt="호버 이미지">
						<img src="/images/warplane.png">
						<h3>WarPlane</h3>
						<p>Action</p>
						<a href="/game/list.do?id=800003"><button>Insert Coin</button></a>
					</div>
					<div class="game">
						<img class="hover-img" src="/images/coin.gif" alt="호버 이미지">
						<img src="/images/metro.png">
						<h3>Mini Metro</h3>
						<p>Strategy</p>
						<a href="/game/list.do?id=800004"><button>Insert Coin</button></a>
					</div>
					<div class="game">
						<img class="hover-img" src="/images/coin.gif" alt="호버 이미지">
						<img src="/images/tetris.png">
						<h3>Tetris</h3>
						<p>Puzzle</p>
						<a href="/game/list.do?id=800005"><button>Insert Coin</button></a>
					</div>
					<div class="game">
						<img class="hover-img" src="/images/coin.gif" alt="호버 이미지">
						<img src="/images/chess.jpg">
						<h3>Chess</h3>
						<p>Stretegy</p>
						<a href="/game/list.do?id=800006"><button>Insert Coin</button></a>
					</div>
				</div>
			</div>
			<div class="right-content">
				<c:if test="${member.memberId==null}">
					<div class="loginbox">
						<h2>로그인</h2>
						<form action="/member/login.do" method="post" id="frm">
							<div class="input-group">
								<input type="hidden" name="csrfToken" value="${csrfToken}"/>
								<input type="text" name="id" id="id" placeholder="아이디">
								<input type="password" name="pw" id="pw" placeholder="비밀번호">
								<button id="loginBtn">로그인</button>
							</div>
						</form>
						<div class="login-links">
							<a id="kakao-login-btn" href="javascript:loginWithKakao()"><button>간편
									로그인</button> </a> <a href="/member/addForm.do"><button>회원가입</button></a>
							<button type="button" id="pwFinder">비밀번호 재설정</button>
						</div>
					</div>
				</c:if>

				<div class="rankingboard" id="ranking">
					<h3>🏆 랭킹 보드</h3>
					<div class="ranking-tabs">
						<button class="tab-btn active" data-game="game1">Skipping</button>
						<button class="tab-btn" data-game="game2">2048</button>
						<button class="tab-btn" data-game="game3">Warplane</button>
						<button class="tab-btn" data-game="game4">Metro</button>
						<button class="tab-btn" data-game="game5">Tetris</button>
						<button class="tab-btn" data-game="game6">Chess</button>
					</div>

					<div class="ranking-content">
						<ul class="ranking-list" id="game1">

						</ul>
						<ul class="ranking-list hidden" id="game2">

						</ul>
						<ul class="ranking-list hidden" id="game3">

						</ul>
						<ul class="ranking-list hidden" id="game4">

						</ul>
						<ul class="ranking-list hidden" id="game5">

						</ul>
						<ul class="ranking-list hidden" id="game6">

						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="bottombody">

			<div class="boardlist">
				<h3>🌟 이번주 인기 게시글</h3>
				<table>
					<thead>
						<tr id="title">
							<th style="width: 12%;">번호</th>
							<th class="clicktitle" style="width: 42%;">제목</th>

							<th style="width: 17%;">작성자</th>
							<th style="width: 17%;">날짜</th>
							<th style="width: 12%;">조회</th>
						</tr>
					</thead>
					<tbody id="hot-weekend-board">

					</tbody>
				</table>
			</div>
			<div class="boardlist">
				<h3>📢 최근 게시물</h3>
				<table>
					<thead>
						<tr id="title">
							<th style="width: 12%;">번호</th>
							<th class="click-title" style="width: 42%;">제목</th>

							<th style="width: 17%;">작성자</th>
							<th style="width: 17%;">날짜</th>
							<th style="width: 12%;">조회</th>
						</tr>
					</thead>
					<tbody id="latestboard">
					</tbody>
				</table>
			</div>
		</div>
		<div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>
	</div>
	<input type="hidden" id="loginStatus" value="${login}" />
	<script>
		$("#pwFinder").on("click", function() {
			window.open("/member/pwResetForm.do", "", "width=550, height=500");
		});
		if ($("#loginStatus").val() == 'failed') {
			alert("로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
			window.history.replaceState(null, null, "/");
		}
		Kakao.init('f9db9ce16f96861764ec0a83c0470eff');
		function loginWithKakao() {
			Kakao.Auth.authorize({
				redirectUri : 'http://10.5.5.14/KakaoLogin',
				scope : 'profile_nickname,profile_image,account_email',
			});
		}
		$(document).ready(function() {
			if (sessionStorage.getItem("enteredGame") === "true") {
				$(".starter").hide();
				$(".container").show();
			}

			function callLatestBoard() {
				$.ajax({
					url: "/board/mainlist.do",
					type: "GET",
					dataType: "json"
				})
				.done(function(calld) {
					console.log(calld);
					let latestBoard = $('#latestboard');
					latestBoard.empty();

					if (!calld || calld.length === 0) {
						latestBoard.append("<tr><td colspan='5'>게시글이 없습니다.</td></tr>");
						return;
					}

					for (let i = 0; i < calld.length; i++) {
						const tr = $('<tr>');
						tr.append($('<td style="width: 12%;">').text(calld[i].boardId));
						tr.append($('<td class="clicktitle" style="width: 42%;">').append($('<a>').attr('href', "/board/detail.do?id=" + calld[i].boardId).text(calld[i].title)));
						tr.append($('<td style="width: 17%;">').text(calld[i].writer));
						tr.append($('<td style="width: 17%;">').text(calld[i].regDate));
						tr.append($('<td style="width: 12%;">').text(calld[i].viewCount));
						latestBoard.append(tr);
					}
				})
				.fail(function(xhr, status, error) {
					console.log("게시판 데이터 로딩 실패:", error);
				});
			}

			callLatestBoard();

			$.ajax({
				url: '/board/hotweek/list.do',
				type: 'GET',
				dataType: 'json'
			})
			.done(function(calld) {
				console.log(calld);
				let latestBoard = $('#hot-weekend-board');
				latestBoard.empty();

				if (!calld || calld.length === 0) {
					latestBoard.append("<tr><td colspan='5'>게시글이 없습니다.</td></tr>");
					return;
				}

				for (let i = 0; i < calld.length; i++) {
					const tr = $('<tr>');
					tr.append($('<td style="width: 12%;">').text(calld[i].boardId));
					tr.append($('<td class="clicktitle" style="width: 42%;">').append($('<a>').attr('href', "/board/detail.do?id=" + calld[i].boardId).text(calld[i].title)));
					tr.append($('<td style="width: 17%;">').text(calld[i].writer));
					tr.append($('<td style="width: 17%;">').text(calld[i].regDate));
					tr.append($('<td style="width: 12%;">').text(calld[i].viewCount));
					latestBoard.append(tr);
				}
			});

			function loadRanking(gameId) {
				console.log(gameId);

				$.ajax({
					url: "/game/score/list/game.do?id=" + gameId,
					type: "GET",
					dataType: "json"
				})
				.done(function(data) {
					console.log(data);
					let rankingList = $('.ranking-list');
					rankingList.html('');

					if (!data || data.length === 0) {
						rankingList.append("<li>랭킹 데이터 없음</li>");
						return;
					}

					for (let i = 0; i < data.length; i++) {
						console.log(data[i]);
						const li = $('<li>').html((i + 1) + '위 ' + data[i].user + '(' + data[i].score + '점)');
						rankingList.append(li);
					}
				})
				.fail(function(xhr, status, error) {
					console.log("랭킹 데이터 불러오기 실패:", error);
				});
			}

			let defaultGameId = "800001";
			loadRanking(defaultGameId);

			$(".tab-btn").click(function() {
				$(".tab-btn").removeClass("active");
				$(this).addClass("active");

				let gameId = $(this).data("game");
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