<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="EUC-KR">
<title>회원게시판</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<style>
* {
	box-sizing: border-box;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

body {
	background-color: #0e0326;
	/* 어두운 보라색 배경 */
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	padding: 20px;
}

.container {
	width: 100%;
	max-width: 1000px;
	background-color: #2b2d42;
	/* 어두운 회색 배경 */
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	padding: 30px;
	color: white;
}

.container>.header {
	display: flex;
	height: 12%;
	justify-content: space-between;
	align-items: center;
	background-color: #2b2d42;
	/* 헤더 배경 */
	padding: 10px 20px;
	border: 1px solid white;
}

.container>.navi {
	display: flex;
	height: 5%;
	justify-content: flex-end; /* 항목들을 오른쪽으로 정렬 */
	align-items: center;
	background-color: #2b2d42;
	/* 네비게이션 배경 */
	padding: 10px 20px;
	border: 1px solid white;
}

.header>.logo {
	font-size: 24px;
	font-weight: bold;
	color: white;
}

.header {
	display: flex;
	justify-content: space-between; /* 좌우 정렬 */
	align-items: center;
	background-color: #2b2d42;
	padding: 10px 20px;
	border: 1px solid white;
}

.header ul {
	list-style: none;
	display: flex;
	gap: 15px;
	justify-content: flex-end; /* 오른쪽 정렬 */
	margin-left: auto; /* 왼쪽 여백을 자동으로 만들어서 오른쪽으로 붙음 */
}

.header ul li {
	padding: 10px 15px;
	background: #0e0326;
	
	color: white;
	border-radius: 5px;
	cursor: pointer;
	transition: background 0.3s ease;
	font-size: 18px;
}

.header ul li:hover {
	background: #2b2d42;
}

.logbox {
	color: #f4f4f4;
	font-weight: bold;
	font-size: 16px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table th, table td {
	padding: 10px;
	text-align: center;
	border: 1px solid #ddd;
	color: white;
}

table th {
	background-color: #0e0326;
	color: #fff;
}

table tr:nth-child(even) {
	background-color: #0e0326;
	/* 짝수 행 배경색 */
}

table a {
	color: #0e0326;
	text-decoration: none;
	font-weight: bold;
	color: white;
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

.paging:hover {
	background-color: #2e0f73;
	color: white;
}

button {
	padding: 10px 20px;
	background-color: #0e0326;
	color: white;
	border: none; /* 경계선 제거 */
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	margin-top: 20px; /* 버튼을 아래로 띄우기 */
}

button:hover {
	background: #2b2d42;
}

#name, #title, #number, #buttonbox {
	height: 50px;
	color: white;
}

}
#buttonbox {
	display: flex;
	justify-content: flex-start; /* 버튼을 왼쪽으로 정렬 */
	align-items: center;
	padding: 20px;
	border: none;
}

/* 반응형 디자인 설정 */
@media screen and (max-width: 768px) {
	.header ul {
		flex-direction: column;
		align-items: center;
	}
	.container {
		padding: 15px;
	}
	.logbox {
		font-size: 14px;
	}
	.paging {
		width: 25px;
		height: 25px;
		line-height: 25px;
	}
	button {
		width: 100%;
	}
}
</style>

</head>

<body>

<script>
	$(function() {
		// 페이지네이션 클릭 이벤트
		$(".page").on("click", function() {
			let pageNum = $(this).attr("page");
			sessionStorage.setItem("last_cpage", pageNum);
			location.href = "/list.board?cpage=" + pageNum;
		});
	});
</script>

	<div class="container">
		<!-- 헤더 -->
		<div class="header">
			<div class="logo">Team CodeQuest</div>
			<ul>
				<li onclick="location.href='index.jsp'">Home</li>
				<li>Game</li>
				<li>Board</li>
				<li>Service</li>
			</ul>

			<div class="logbox">
				<span id="username"></span>
			</div>
		</div>

		<div class="navi">님 환영합니다</div>

		<div class="body">
			<!-- 게시판 테이블 -->
			<table>
				<tr id="name">
					<td colspan="8">자유게시판</td>
				</tr>
				<tr id="title">
					<th style="width: 12%;">번호</th>
					<th style="width: 42%;">제목</th>
					<th style="width: 17%;">작성자</th>
					<th style="width: 17%;">날짜</th>
					<th style="width: 12%;">조회</th>
				</tr>

				<c:forEach var="dto" items="${list}">
					<tr>
						<td>${dto.boardId}</td>
						<td class="contents">
							<span class="content-type">${(dto.role == 'user') ? '게시글' : '공지'}</span>
							<a href="/board/detail.do?id=${dto.boardId}"> ${dto.title} </a>
							<span class="reply-count">[${dto.replyCount}]</span>
						</td>
						<td>${dto.writer}</td>
						<td>${dto.regDate}</td>
						<td>${dto.viewCount}</td>
					</tr>
				</c:forEach>

				<tr id="number">
					<td colspan="8" align="center"></td>
				</tr>

			</table>
			<div colspan="3" id="buttonbox">
				<a href="/board/addform.do" method="get">
					<button>작성하기</button>
				</a>
			</div>
		</div>
	</div>

</body>

</html>
						<script>
						
					    function makePageNavi(url) {
					        const pageNavi = $('<div>');

					        function makeSpan(content, index) {
					            const span = $('<span>').html(content).addClass('page').attr('page', index);

					            span.on('click', function () {
					                location.href = url + index;
					            });

					            return span;
					        }
					        <%= request.getAttribute("start") %>

					        if (${!page.isFirst}) {
					            page.append(makeSpan('이전', ${page.startNavi - 1}));
					        }

					        for (let i = ${page.startNavi}; i <= ${page.endNavi}; i++) {
					            pageNavi.append(makeSpan(i, i));
					        }

					        if (${!page.isEnd}) {
					            page.append(makeSpan('다음', ${page.endNavi + 1}));
					        }

					        const indexCss = '.page {font-size: 20px; width: 50px; height: 50px; padding-left: 5px; padding-right: 5px;}'
					        const hover = '.page:hover { cursor: pointer; }'

					        $('style').append(hover).append(indexCss);

					        return pageNavi;
					    }

					    $('#number>td').append(makePageNavi('/board/list.do?cpage='));
					</script>