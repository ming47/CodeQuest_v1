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
<script>
  Kakao.init('f9db9ce16f96861764ec0a83c0470eff');
</script>


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
   display: flex;
   width: 100%;
   height: 100%;
   justify-content: center;
   align-items: center;
   background: url('/images/allback.jpg') no-repeat center;
   background-size: cover;
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
   height: 60px;
   padding: 20px;
   position: relative;
      display: flex;
   align-items: center;
   justify-content: center;
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
   position: fixed;
   top: 0;
   left: 0;
   background: #1e201d;
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

/* ✅ 오른쪽 영역 (로그인 박스 + 랭킹보드) */
.right-content {
   display: flex;
   flex-direction: column;
   align-items: center;
   justify-content: flex-start;
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
   width: 85%;
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

/* ✅ 로그인 전 랭킹보드 기본 크기 */
.rankingboard {
   width: 85%;
   height: 380px;
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
   margin-right: 80px;
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
   padding: 6px 2px;
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
    position: fixed;
    top: 50px;
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
   background: url('/images/gamebtn.png') no-repeat center;
   background-size: cover;
}

.game button:hover {
   transform: scale(1.2);
}

.boardlist {
   width: 90%;
   background: url('/images/board.jpg') no-repeat center;
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

table {
    width: 100%;
}
thead, tbody{
   width: 100%;

}

#title{
   width: 100%;
}

td, th {
   width:100%;
    padding: 8px; /* 여백 추가 */
    text-align: center; /* 가운데 정렬 */
}

</style>
<c:if test="${member.memberId != null}">
<style>
	#ranking {
	margin-top : 40px;
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

      <!-- ✅ 헤더 -->
      <div class="header">
         <div class="navi">
            <div class="logo">Team CodeQuest</div>
            <ul class="menu">
               <li><a href="/">Home</a></li>
               <li><a href="/game/list.do">Game</a></li>
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
         <!-- ✅ 로그인 정보 -->
         <c:if test="${member.memberId != null}">
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
                  <img src="/images/skipstone.png">
                  <h3>Skipping Stone</h3>
                  <p>Action</p>
                  <a href="/games/SkippingStone/main.html"><button>Play</button></a>
               </div>
               <div class="game">
                  <img src="/images/2048.png">
                  <h3>2048</h3>
                  <p>Puzzle</p>
                  <button>Play</button>
               </div>
               <div class="game">
                  <img src="/images/warplane.png">
                  <h3>World Of WarPlane</h3>
                  <p>Action</p>
                  <button>Play</button>
               </div>
               <div class="game">
                  <img src="/images/metro.png">
                  <h3>Mini Metro</h3>
                  <p>Strategy</p>
                  <button>Play</button>
               </div>
               <div class="game">
                  <img src="/images/tetris.png">
                  <h3>Tetris</h3>
                  <p>Puzzle</p>
                  <button>Play</button>
               </div>
               <div class="game">
                  <img src="/images/chess.jpg">
                  <h3>Chess</h3>
                  <p>Stretegy</p>
                  <button>Play</button>
               </div>
            </div>

            <div class="boardlist">
               <h3>📢 최근 게시물</h3>
               <table>
               <thead>
                  <tr id="title">
                     <th style="width: 12%;">번호</th>
                     <th style="width: 42%;">제목</th>
                     <th style="width: 17%;">작성자</th>
                     <th style="width: 17%;">날짜</th>
                     <th style="width: 12%;">조회</th>
                  </tr>
                  </thead>
                  <tbody id="latestboard">

                  </tbody>
               </table>
            </div>
            <div class="boardlist">
               <h3>📢 이번주 인기 게시글</h3>
               <table>
               <thead>
                  <tr id="title">
                     <th style="width: 12%;">번호</th>
                     <th style="width: 42%;">제목</th>
                     <th style="width: 17%;">작성자</th>
                     <th style="width: 17%;">날짜</th>
                     <th style="width: 12%;">조회</th>
                  </tr>
                  </thead>
                  <tbody id="hot-weekend-board">

                  </tbody>
               </table>
            </div>
         </div>

         <!-- ✅ 오른쪽 로그인 + 랭킹보드 -->

         <div class="right-content">
            <c:if test="${member.memberId==null}">
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

      <!-- ✅ 푸터 -->
      <div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>

   </div>

   <script>
      function loginWithKakao() {
             Kakao.Auth.authorize({
               redirectUri: 'http://10.5.5.14/KakaoLogin',
               scope: 'profile_nickname,profile_image,account_email',
             });
      }

      $("#pwFinder").on("click", function() {
         window.open("/member/pwResetForm.do", "", "width=550, height=300");
      });
      let urlParams = new URL(location.href).searchParams;
      let loginStatus = urlParams.get('login');
       if (loginStatus === 'fail') {
           alert("로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
       }

      $(document).ready(
            function() {
               if (sessionStorage.getItem("enteredGame") === "true") {
                  $(".starter").hide();
                  $(".container").show();
               }
              /* 
            	let memberSession = ${member} !== "";   
              	if (memberSession){ 
            	        $(".rankingboard").css("height", "2000px"); // 높이 조정
            	        $(".rankingboard").addClass("expanded"); // 추가적인 스타일 적용 가능
            	  }
               */
               function callLatestBoard() {
                   $.ajax({
                       url: "/board/mainlist.do",
                       type: "GET",
                       dataType: "json"
                   }).done(function(calld) {
                       console.log(calld);
                       let latestBoard = $('#latestboard');

                       // 기존 데이터 지우기
                       latestBoard.empty();

                       if (!calld || calld.length === 0) {
                           latestBoard.append("<tr><td colspan='5'>게시글이 없습니다.</td></tr>");
                           return;
                       }

                       // 게시글 데이터 추가
                       for (let i = 0; i < calld.length; i++) {
                           const tr = $('<tr>');

                           tr.append($('<td style="width: 12%;">').text(calld[i].boardId));
                           tr.append($('</td>'));
                           tr.append($('<td style="width: 42%;">').append(
                               $('<a>').attr('href', "/board/detail.do?id=" + calld[i].boardId).text(calld[i].title)
                           ));
                           tr.append($('</td>'));
                           tr.append($('<td style="width: 17%;">').text(calld[i].writer));
                           tr.append($('</td>'));
                           tr.append($('<td style="width: 17%;">').text(calld[i].regDate));
                           tr.append($('</td>'));
                           tr.append($('<td style="width: 12%;">').text(calld[i].viewCount));
                           tr.append($('</td>'));

                           latestBoard.append(tr);
                       }
                   }).fail(function(xhr, status, error) {
                       console.log("게시판 데이터 로딩 실패:", error);
                   });
               }
               
               callLatestBoard();
               
               $.ajax({
                  url: '/board/hotweek/list.do',
                  type: 'GET',
                  dataType: 'json'
               }).done(function(calld) {
                  console.log(calld);
                  let latestBoard = $('#hot-weekend-board');

                   // 기존 데이터 지우기
                   latestBoard.empty();

                   if (!calld || calld.length === 0) {
                       latestBoard.append("<tr><td colspan='5'>게시글이 없습니다.</td></tr>");
                       return;
                   }

                   // 게시글 데이터 추가
                   for (let i = 0; i < calld.length; i++) {
                       const tr = $('<tr>');

                       tr.append($('<td style="width: 12%;">').text(calld[i].boardId));
                       tr.append($('</td>'));
                       tr.append($('<td style="width: 42%;">').append(
                           $('<a>').attr('href', "/board/detail.do?id=" + calld[i].boardId).text(calld[i].title)
                       ));
                       tr.append($('</td>'));
                       tr.append($('<td style="width: 17%;">').text(calld[i].writer));
                       tr.append($('</td>'));
                       tr.append($('<td style="width: 17%;">').text(calld[i].regDate));
                       tr.append($('</td>'));
                       tr.append($('<td style="width: 12%;">').text(calld[i].viewCount));
                       tr.append($('</td>'));

                       latestBoard.append(tr);
                   }
               });

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