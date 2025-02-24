<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="EUC-KR">
<title>마이페이지</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">

<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	background: #f4f7f8;
	overflow-y: auto;
}

.header {
	width: 100%;
	height: 80px;
	background: #1e201d;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0 20px;
	color: #b4c28a;
	font-family: "Press Start 2P", serif;
	font-size: 15px;
	position: fixed;
	top: 0;
	left: 0;
	z-index: 1000;
}

.navi {
	display: flex;
	align-items: center;
	justify-content: space-between;
	width: 100%;
}

.logo {
	font-size: 24px;
	color: #b4c28a;
}

.navi ul {
	list-style: none;
	display: flex;
}

.navi ul li {
	width: 130px;
	height: 30px;
	padding: 10px 15px;
	background: white;
	color: #b4c28a;
	border-radius: 5px;
	cursor: pointer;
	transition: 0.3s ease-in-out;
	margin: 10px;
	text-align: center;
	display: flex;
	align-items: center;
	justify-content: center;
}

.navi ul li a {
	color: #b4c28a;
	text-decoration: none;
	width: 100%;
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
}

.navi ul li:hover {
	background: #81a5bf;
	color: white;
}

.container {
	width: 100%;
	max-width: 1500px;
	background: white;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
	margin: 100px auto;
	padding: 20px;
	display: flex;
	margin-left: 350px;
}

.main-content {
	flex-grow: 1;
	display: flex;
	flex-direction: column;
	gap: 20px;
	margin-left: 20px;
}

.sidebar {
	width: 250px;
	height: 330px;
	background: #f4f4f4;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	left: 35px;
	position: fixed;
}

.sidebar h2 {
	font-size: 33px;
	color: black;
	font-weight: bold;
	margin-bottom: 15px;
	border-bottom: 2px solid black;
	padding-bottom: 10px;
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

.content {
	flex-grow: 1;
	margin-left: 20px;
	padding: 20px;
	background: white;
	border-radius: 10px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.content h2 {
	font-size: 40px;
	color: #333;
	margin-bottom: 10px;
}

fieldset {
	width: 65%;
	border: 3px solid #b4c28a;
	border-radius: 10px;
	padding: 15px;
	margin: auto;
	text-align: left;
	background: #fafafa;
}

legend {
	font-size: 35px;
	font-weight: bold;
	color: #b4c28a;
	padding: 0 10px;
}

.input-group {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-bottom: 10px;
	padding: 10px 10px;
	gap: 10px;
}

.input-group label {
	font-size: 18px;
	font-weight: bold;
	color: #444;
	width: 100px;
	text-align: left;
}

input {
	width: 100%;
	padding: 10px;
	margin-top: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 14px;
	transition: 0.3s;
}

input:focus {
	border: 1px solid #b4c28a;
	outline: none;
	box-shadow: 0 0 5px rgba(30, 144, 255, 0.5);
}

input[disabled] {
	background: #e9ecef;
}

.input-group input {
	flex-grow: 1;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 20px;
	transition: 0.3s;
	width: 100%;
	text-align: left;
}

.input-group input:focus {
	border: 3px solid #b4c28a;
	outline: none;
	box-shadow: 0 0 5px rgba(180, 194, 138, 0.5);
}

.input-group input[disabled] {
	background: #e9ecef;
}

.buttons {
	display: flex;
	justify-content: space-between;
	width: 51%;
	margin: auto;
	margin-top: 15px;
}

.buttons button, .postcode-btn, .id-check-btn {
	flex: 1;
	padding: 12px;
	margin: 5px;
	border: none;
	border-radius: 6px;
	font-size: 20px;
	cursor: pointer;
	transition: 0.3s;
}

.buttons button:first-child, .id-check-btn {
	background: #b4c28a;
	color: white;
}

#update_address {
	background: #b4c28a;
	color: white;
	border: none;
	border-radius: 6px;
	font-size: 20px;
	cursor: pointer;
	transition: 0.3s;
	width: 110px;
	height: 48px;
	margin-left: 10px;
}

#update_address:hover {
	background: #346e30;
}

.buttons button:first-child:hover, .id-check-btn:hover {
	background: #346e30;
}

.buttons button:last-child {
	background: lightgray;
	color: black;
}

.buttons button:last-child:hover {
	background: gray;
	color: white;
}

.postcode-btn {
	background: dodgerblue;
	color: white;
	padding: 12px;
	font-size: 14px;
	border-radius: 6px;
	border: none;
	cursor: pointer;
	white-space: nowrap;
	transition: 0.3s;
}

.postcode-btn:hover {
	background: #0056b3;
}

.posts-container {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	justify-content: flex-start;
}

.post-card {
	background: #fafafa;
	border: 2px solid #b4c28a;
	border-radius: 10px;
	padding: 15px;
	width: calc(33.33% - 20px); /* 3열 배치, 간격 고려 */
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	transition: transform 0.3s;
}

.post-card:hover {
	transform: translateY(-5px);
}

.post-header {
	display: flex;
	align-items: center;
	margin-bottom: 10px;
}

.post-number {
	font-weight: bold;
	color: #b4c28a;
	margin-right: 10px;
}

.post-title {
	font-size: 18px;
	color: #333;
	text-decoration: none;
}

.post-title:hover {
	text-decoration: underline;
}

.post-info {
	font-size: 14px;
	color: #666;
	display: flex;
	justify-content: space-between;
}

.recent-game-row {
	display: flex; /* 가로로 나열 */
	align-items: center; /* 세로 중앙 정렬 */
	border: 2px solid #b4c28a;
	border-radius: 10px;
	padding: 10px;
	margin-bottom: 10px;
	background: #fafafa;
}

.recent-game-row img {
	width: 60px;
	height: 60px;
	margin-right: 20px;
	object-fit: cover; /* 이미지 비율 맞춤 */
}

.recent-game-row .game-title {
	flex: 1; /* 남는 공간을 차지하도록 (제목 영역 크게) */
	font-size: 20px;
	font-weight: bold;
}

/* 링크 밑줄·파란색 제거 */
.recent-game-row .game-title a {
	text-decoration: none;
	color: #333;
}

.recent-game-row .game-title a:hover {
	text-decoration: underline;
}

/* 날짜/시간 칸 오른쪽 정렬 */
.recent-game-row .play-date, .recent-game-row .play-time {
	width: 150px;
	text-align: right;
	font-size: 14px;
	color: #666;
	margin-right: 10px;
}
</style>
</head>

<body>
	<div class="header">
		<div class="navi">
			<div class="logo">Team CodeQuest</div>
			<ul>
				<li><a href="/">Home</a></li>
				<li><a href="/game">Game</a></li>
				<li><a href="#">Board</a></li>
				<li><a href="#">Service</a></li>
			</ul>
		</div>
	</div>
	<div class="container">
		<div class="sidebar">
			<h2>MyPage</h2>
			<ul>
				<li data-target="edit">내 정보</li>
				<li data-target="game_records">게임기록</li>
				<li data-target="my_posts">게시글</li>
				<li data-target="my_qna">문의내역</li>
				<li>회원탈퇴</li>
			</ul>
		</div>

		<div class="main-content">
			<div class="content" id="edit">
				<h2 style="font-size: 50px;">내 정보</h2>
				<br>
				<fieldset>
					<legend>기본정보</legend>
					<form action="/member/update.do" method="post" id="frm">
						<input type="hidden" name="memberId" value=${member.memberId}>
						<div class="input-group">
							<label for="login_id">아이디</label> <input type="text"
								value="<c:choose><c:when test='${member.loginId == null}'>입력된 정보가 없습니다.</c:when><c:otherwise>${member.loginId}</c:otherwise></c:choose>"
								readonly>
						</div>
						<div class="input-group">
							<label for="name">이름</label> <input type="text" name="name"
								id="name" value=${member.name } readonly>
						</div>
						<div class="input-group">
							<label for="nickname">닉네임</label> <input type="text"
								name="nickName" id="nickName" value=${member.nickName } readonly>
						</div>
						<div class="input-group">
							<label for="ssn">주민번호</label> <input type="text" name="ssn"
								id="ssn" value=${member.ssn } readonly>
						</div>
						<div class="input-group">
							<label for="email">이메일</label> <input type="text" name="email"
								id="email" value=${member.email } readonly>
						</div>
						<div class="input-group">
							<label for="phone">전화번호</label> <input type="text" name="phone"
								id="phone" value=${member.phone } readonly>
						</div>
						<div class="input-group">
							<label for="zipcode">우편번호</label> <input type="text"
								name="zipCode" id="zipCode"
								value="<c:choose><c:when test='${member.zipCode == 0}'>입력된 정보가 없습니다.</c:when><c:otherwise>${member.zipCode}</c:otherwise></c:choose>"
								readonly>
							<button type="button" id="update_address"
								style="visibility: hidden;">검색</button>
						</div>
						<div class="input-group">
							<label for="address">주소</label> <input type="text" name="address"
								id="address"
								value="<c:choose><c:when test='${member.address == null}'>입력된 정보가 없습니다.</c:when><c:otherwise>${member.address}</c:otherwise></c:choose>"
								readonly>

						</div>
						<div class="input-group">
							<label for="detail_address">상세주소</label> <input type="text"
								name="detailAddress" id="detailAddress"
								value="<c:choose><c:when test='${member.detailAddress == null}'>입력된 정보가 없습니다.</c:when><c:otherwise>${member.detailAddress}</c:otherwise></c:choose>"
								readonly>

						</div>
						<div class="input-group">
							<label for="join_date">가입날짜</label> <input type="text"
								name="regDate" id="regDate" value=${member.regDate } readonly>
						</div>
						<div class="buttons">
							<button type="button" id="update_btn">수정하기</button>
							<button type="button" id="out_btn">회원탈퇴</button>
						</div>
					</form>
				</fieldset>
			</div>

			<div class="content" id="game_records">
				<h2 style="font-size: 50px;">게임기록</h2>
				<h3 style="font-size: 28px;">최근 플레이한 게임</h3>

				<c:forEach var="pt" items="${recentPlayTime}">
					<div class="recent-game-row">
						<!-- 1) 게임 이미지 -->
						<img src="/game.png" alt="게임 이미지" />

						<!-- 2) 게임 제목 (링크) -->
						<div class="game-title">
							<a href="/game/list.do?gameId=${pt.gameId}"> 게임제목 들어올 공간 </a>
						</div>

						<!-- 3) 플레이 날짜, 오른쪽 정렬 -->
						<div class="play-date">플레이 날짜: ${pt.regDate}</div>

						<!-- 4) 플레이 타임, 오른쪽 정렬 -->
						<div class="play-time">플레이 타임: ${pt.formatTime}</div>
					</div>
				</c:forEach>
			</div>




			<div class="content" id="my_posts">
				<h2 style="font-size: 50px; margin-bottom: 20px;">게시글</h2>
				<div style="margin: 10px;">
					<h3 style="font-size: 28px;">최근 작성한 게시글</h3>
				</div>
				<div class="posts-container">
					<c:forEach var="post" items="${recentPost}">
						<div class="post-card">
							<div class="post-header">
								<span class="post-number">#${post.boardId}</span> <a
									class="post-title" href="/board/detail.do?id=${post.boardId}">${post.title}</a>
							</div>
							<div class="post-info">
								<span class="post-date">${post.regDate}</span> <span
									class="post-view">${post.viewCount} 조회</span>
							</div>
						</div>
					</c:forEach>
				</div>

				<div style="margin: 10px;">
					<h3 style="font-size: 28px;">내가 최근에 본 게시글</h3>
				</div>
			</div>
			<div class="content" id="my_qna">
				<h2 style="font-size: 50px;">문의내역</h2>
			</div>
		</div>
	</div>
	<script>
        $(".sidebar ul li").on("click",function () {
            let targetId = $(this).attr("data-target");
            let targetElement = $("#" + targetId);
            if (targetElement.length) {
                $("html, body").animate(
                    { scrollTop: targetElement.offset().top - 100 }, 600
                );
            }
        });
        $("#update_btn").on("click",function() {
       		$("#nickName, #email, #phone, #address, #detailAddress").removeAttr("readonly");
       		
       		$("#update_btn").css("display","none");
       		$("#out_btn").css("display","none");
       		
   			let updateOk = $("<button>").attr({ id: "update_ok_btn", type: "submit" }).html("수정완료");
   			
   			let updateCancel = $("<button>").attr("type","button").html("취소");
   			   			
   			updateCancel.on("click", function() {
   				location.reload();
   			});
   			$(".buttons").append(updateOk,updateCancel);
   			$("#update_address").css("visibility", "visible"); 
   			
        });
		$("#update_address").on("click", function() {
			new daum.Postcode({
				oncomplete : function(data) {
					$("#zipCode").val(data.zonecode);
					$("#address").val(data.roadAddress);
					$("#detailAddress").focus();
				}
			}).open();
		});
		
   		$("#out_btn").on("click", function() {
   			if(confirm("정말 탈퇴하시겠습니까?") == true) {
        		location.href = "/member/out.do?id=${member.memberId}"; //삭제요청한 ID를 세션에서 꺼낸후 request요청
        		alert("회원탈퇴가 완료되었습니다.");
   			} else {
   				location.href = "/";
   			}
    	});
        
    </script>
</body>
</html>