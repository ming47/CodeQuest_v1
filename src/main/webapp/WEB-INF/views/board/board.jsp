<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판리스트</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">
<style>
* {
	box-sizing: border-box;
   }
   
html, body {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
}

.container {
	display: flex;
	flex-direction: column; /* 헤더를 위에 두기 위해 column 방향으로 설정 */
	align-items: center;
	max-height: 2000px;
	width: 100%;
	background: url('/images/allback.jpg') no-repeat center;
	background-size: cover;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	color: white;
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
	height: 80px;
	padding: 20px;
	position: relative;
	font-family: "Press Start 2P", serif;
	font-weight: 400;
	font-style: normal;
}

.footer {
	height: 60px;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 14px;
	margin-top: 40px;
	font-family: "Press Start 2P", serif;
	font-weight: 400;
	font-style: normal;
}
.navi {
	display: flex;
	align-items: center;
	width: 100%;
	justify-content: space-between;
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

table {
	max-width: 1500px;
	border-collapse: collapse;
	margin-top: 20px;
	min-width: 60%;
	background-color: #868686;
	border: 1px solid #fff;
}

table th, table td {
	padding: 10px;
	text-align: center;
	color: white;
	border: 1px solid #fff;
}

table th {
	background-color: #0e0326;
	color: #fff;
	empty-cells: hide;
}

table a {
	color: #0e0326;
	text-decoration: none;
	font-weight: bold;
	color: white;
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

.paging:hover {
	background-color: #2e0f73;
	color: white;
}

button {
	padding: 10px 20px;
	background-color: #0e0326;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	margin-top: 20px;
}
.writebtn {
	margin:0;
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
	justify-content: flex-start;
	align-items: center;
	padding: 20px;
	border: none;
}

.notice {
	background-color: 'red';
}

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

		$(".page").on("click", function() {
			let pageNum = $(this).attr("page");
			sessionStorage.setItem("last_cpage", pageNum);
		});
	});
</script>

	<div class="container">


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





		<div class="body">

			<c:if test="${member.memberId != null}">
				<div class="logbox-container">

					<%@ include file="/logbox.jsp"%>

				</div>
			</c:if>
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
				<c:forEach var="dto" items="${noticeList}">
					<tr>
						<td>${dto.boardId}</td>
						<td class="contents notice"><span class="content-type">${(dto.role == 'user') ? '게시글' : '공지'}</span>
							<a href="/board/detail.do?id=${dto.boardId}"> ${dto.title} </a> <span
							class="reply-count">[${dto.replyCount}]</span></td>
						<td>${dto.writer}</td>
						<td>${dto.regDate}</td>
						<td>${dto.viewCount}</td>
					</tr>
				</c:forEach>
				<c:forEach var="dto" items="${list}">
					<tr>
						<td>${dto.boardId}</td>
						<td class="contents"><span class="content-type">${(dto.role == 'user') ? '게시글' : '공지'}</span>
							<a href="/board/detail.do?id=${dto.boardId}"> ${dto.title} </a> <span
							class="reply-count">[${dto.replyCount}]</span></td>
						<td>${dto.writer}</td>
						<td>${dto.regDate}</td>
						<td>${dto.viewCount}</td>
					</tr>
				</c:forEach>


				<tr id="number">
					<td colspan="8" align="center"></td>
				</tr>


				<form method="get" name="search" action="/board/search.do">

					<tr>
						<td><select class="form-control" name="searchField">
								<option class="choice" value="0">선택</option>
								<option value="schTitle">제목</option>
								<option value="schWriter">작성자</option>
						</select></td>
						<td class="searchtd"><input type="text" class="form-control"
							placeholder="검색어 입력" name="searchText" maxlength="100"></td>
						<td><button type="submit" class="btn btn-success">검색</button></td>
						<td></td>
						<td></td>
					</tr>
				</form>

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

	$('#number>td').append(makePageNavi('/board/list.do?cpage='));
	
	 $(document).ready(function() {
	        $('form[name="search"]').submit(function(event) {
	            let searchField = $('select[name="searchField"]').val(); 

	            if (searchField === "0") {
	                alert("옵션을 선택해주세요!"); 
	                event.preventDefault(); // 폼 제출 방지
	             
	            }
	        });
	    });
	 $(document).ready(function() {
		    $(".writebtn").on("click", function(event) {
		        let isLoggedIn = "${member.loginId}" !== ""; 
		        let isBanned   = "${member.isbanned}" == "true";

		        if (!isLoggedIn) {
		            alert("회원만 글쓰기가 가능합니다.");
		            event.preventDefault(); // 페이지 이동 방지
		            return false;
		        } else if(isBanned) {
		        	alert("글쓰기가 제한된 계정입니다.");
		            event.preventDefault();
		            return false;
		        }
		    });
		});

</script>