<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판리스트</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">

<style>
@font-face {
	font-family: 'DungGeunMo';
	src:
		url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/DungGeunMo.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

html, body {
	width: 100%;
	height: 100vh;
}

.container {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	color: white;
	font-family: 'DungGeunMo';
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
	width: 100%;
	height: 80px;
	background: #1e201d;
	display: flex;
	align-items: center;
	justify-content: space-between;
	position: fixed;
}

.body {
	margin: 0;
	width: 100%;
	background-attachment: fixed;
	background: url('/images/allback.jpg') no-repeat center;
	background-size: cover;
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 100vh;
}

.footer {
	height: 60px;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 14px;
}

.navi {
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.logo {
	font-size: 20px;
}

.navi ul {
	list-style: none;
	display: flex;
	gap: 20px;
}

.navi ul li a {
	padding: 10px 15px;
	background: #717171;
	color: white;
	border-radius: 5px;
	cursor: pointer;
	text-shadow: 0 1px 0 black, -1px 2px 0 black, 1px 4px 0 black, 0 3px 0
		black;
	text-decoration: none;
}

.navi ul li:hover a {
	background: #3c3b39;
	color: white;
}

.logbox {
	color: #f4f4f4;
	font-weight: bold;
	font-size: 16px;
}

.logbox-container {
	position: fixed;
	right: 10px;
	bottom: -35px;
	top: 80px
}

.tablebox {
	border: none;
	background-color: rgb(255, 255, 255, 0.5);
	border-radius: 16px;
	box-shadow: inset 0 0 8px #424242;
	width: 73vw;
	padding: 20px;
	margin-top: 150px;
	margin-bottom: 50px;
}

table {
	border-collapse: collapse;
	width: 70vw;
	font-size: 19px;
	border-spacing: 3px;
	border-collapse: separate;
	background-color: transparent;
}

table th, table td {
	padding: 10px;
	text-align: center;
	border-radius: 1px;
	border: none;
	font-family: 'DungGeunMo';
	color: black;
	background-color: #fafbf4;
	height: 58px;
}

table tr {
	border-radius: 1px;
	font-family: 'DungGeunMo';
	height: 58px;
}

td {
	color: black;
	border-radius: 1px;
	font-family: 'DungGeunMo';
}

table a {
	color: #0e0326;
	text-decoration: none;
	font-weight: bold;
	color: black;
	font-color: white;
}

table a:hover {
	text-decoration: underline;
}

.paging {
	cursor: pointer;
	border: 1px solid #0e0326;
	width: 30px;
	height: 30px;
	display: inline-block;
	text-align: center;
	line-height: 30px;
	margin: 0 5px;
	background-color: #2b2d42;
	transition: background-color 0.3s ease;
}

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
	margin-right: 75px;
}

.loginbox h2 {
	font-family: "Jua", serif;
	font-weight: 400;
	font-style: normal;
	margin: 10px;
	color: #2f2b2b;
}

.paging:hover {
	background-color: #2e0f73;
	color: white;
}

select {
	width: 100%;
	padding: 8px 20px 5px 7px;
	border: 2px solid #ccc;
	outline: none;
	font-size: 16px;
	transition: 0.3s;
	border-radius: 5px;
	font-family: 'DungGeunMo';
}

input {
	width: 100%;
	padding: 10px 40px 10px 15px;
	border: 2px solid #ccc;
	outline: none;
	font-size: 16px;
	transition: 0.3s;
	border-radius: 5px;
	font-family: 'DungGeunMo';
}

input:focus {
	border-color: gray;
	box-shadow: 0 0 8px rgba(128, 128, 128, 0.5);
}

.writebtn {
	margin-top: 10px;
	padding: 20px;
	margin-left: -17px;
}

button {
	padding: 10px 20px;
	background-color: #3c3b39;
	color: white;
	border: none;
	letter-spacing: 5px;
	font-weight: bold;
	position: relative;
	transition: all 0.4s;
	overflow: hidden;
	border-radius: 5px;
	font-family: 'DungGeunMo';
}

#searchbar form {
	display: flex;
	justify-content: flex-start;
	align-items: center;
	gap: 10px;
}

.writebtn {
	margin-top: 15px;
}

.contents {
	text-align: left;
	padding-left: 30px;
}

button:hover {
	background: #66635f;
	transform: scale(1.1);
	color: white;
}

button:focus {
	outline: none;
}

#name, #title, #number, #buttonbox {
	height: 50px;
	color: white;
}

#buttonbox {
	display: flex;
	justify-content: flex-start;
	align-items: center;
	padding: 20px;
	border: none;
}

.notice {
	background-color: 'red';
}
</style>
</head>


<body>

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
	</div>

	<div class="container">

		<div class="body">

			<c:if test="${member.loginId != null}">
				<div class="logbox-container">

					<%@ include file="/logbox.jsp"%>

				</div>
			</c:if>
			<div class="tablebox">
				<table>
					<tr id="name">
						<td colspan="8"
							style="width: 100%; font-size: 35px; padding: 45px;">자 유 게 시
							판</td>
					</tr>
					<tr id="title">
						<th style="width: 12%;">번호</th>
						<th style="width: 42%;">제목</th>
						<th style="width: 17%;">작성자</th>
						<th style="width: 17%;">날짜</th>
						<th style="width: 12%;">조회</th>
					</tr>
					<c:forEach var="dto" items="${noticeList}">
						<tr>
							<td>${dto.boardId}</td>
							<td class="contents notice">공지 📢> <a
								href="/board/detail.do?id=${dto.boardId}"> ${dto.title} </a> <span
								class="reply-count">[${dto.replyCount}]</span>
							</td>

							<td>${dto.writer}</td>
							<td>${dto.regDate}</td>
							<td>${dto.viewCount}</td>
						</tr>
					</c:forEach>
					<c:forEach var="dto" items="${list}">
						<tr>
							<td>${dto.boardId}</td>
							<td class="contents"><a
								href="/board/detail.do?id=${dto.boardId}"> ${dto.title} </a> <span
								class="reply-count">[${dto.replyCount}]</span></td>
							<td>${dto.writer}</td>
							<td class="relative-date" data-timestamp="${dto.regDate.time}">${dto.regDate}</td>
							<td>${dto.viewCount}</td>
						</tr>
					</c:forEach>

					<tr id="number">
						<td colspan="8" align="center"></td>
					</tr>

					<tr id="searchbar">
						<td colspan="5">
							<form method="get" name="search" action="/board/search.do">
								<select class="form-control" name="searchField"
									style="width: 120px;">
									<option value="schTitle">제목</option>
									<option value="schWriter">작성자</option>
								</select> <input type="text" class="form-control" placeholder="검색어 입력"
									name="searchText" maxlength="100" style="width: 300px;">
								<button type="submit" class="btn btn-success">검색</button>
							</form>
						</td>
					</tr>

				</table>

				<div colspan="3" id="buttonbox">
					<c:if test="${dto == null}">
						<a href="/board/addform.do" method="post">
							<button class="writebtn">작성하기</button>
						</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>
</body>
</html>

<script>
	var now = new Date();
	$('.relative-date').each(function () {
		var timestamp = parseInt($(this).data('timestamp'), 10);
		var postDate = new Date(timestamp);
		var diffMinutes = Math.floor((now - postDate) / (1000 * 60));
	
		if (diffMinutes < 1) {
			$(this).text("방금 전");
		} else if (diffMinutes < 60) {
			$(this).text(diffMinutes + "분 전");
		} else if (diffMinutes < 720) {
			var diffHours = Math.floor(diffMinutes / 60);
			$(this).text(diffHours + "시간 전");
		}
	});	

	$(function() {
	
		$(".page").on("click", function() {
			let pageNum = $(this).attr("page");
			sessionStorage.setItem("last_cpage", pageNum);
		});
	});

	function makePageNavi(url) {
		const pageNavi = $('<div>');
		
		function makeSpan(content, index) {
			const span = $('<span>').html(content).addClass('page').attr('page', index);

			span.on('click', function () {
				location.href = url + index;
			});


			return span;
		}


		if (${!page.isFirst}) {
			pageNavi.append(makeSpan('이전', ${page.startNavi - 1}));
		}


		for (let i = ${page.startNavi}; i <= ${page.endNavi}; i++) {
			pageNavi.append(makeSpan(i, i));
		}


		if (${!page.isEnd}) {
			pageNavi.append(makeSpan('다음', ${page.endNavi + 1}));
		}


		const indexCss = '.page {font-size: 20px; width: 50px; height: 50px; padding-left: 5px; padding-right: 5px;}'
		const hover = '.page:hover { cursor: pointer; }'


		$('style').append(hover).append(indexCss);

		return pageNavi;
	}

	$('#number>td').append(makePageNavi('${pageUrl}'));
	
	 $(document).ready(function() {
         $(".writebtn").on("click", function(event) {
             let isLoggedIn =  "${member.memberId}" !== ""; 
             let isBanned   = "${member.isbanned}" == "true";

             if (!isLoggedIn) {
                 if(confirm("회원만 글쓰기가 가능합니다.\n로그인 하러 가시겠습니까?")) {
                    location.href="/";
                 }
                 event.preventDefault(); 
                 return false;
                 
                 
             } else if(isBanned) {
                
                $.ajax({
                   url: '/service/member/ban/detail.do?id=${member.memberId}'
                }).done(function(data) {
                   data = JSON.parse(data);
                   
                   parseDate(data.endDate);
                   
                   let message = "현재 차단된 계정입니다.\n차단 이유: " + data.reason + "\n" 
                   + "차단 기간: " + parseDate(data.startDate) + " ~ " + parseDate(data.endDate);
                   alert(message);
                });
        
                 event.preventDefault();
                 return false;
             }
         });
     });
	 
	 function parseDate(timestamp) {
		 const date = new Date(timestamp);
		 return date.getFullYear() + '년 ' + Number(date.getMonth() + 1) + '월 ' + date.getDate() + '일 ' +  date.getHours() + 
				 '시 ' + date.getMinutes() + '분';		 
	 }

</script>