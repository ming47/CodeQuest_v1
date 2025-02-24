<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="EUC-KR">
<title>����寃�����</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<style>
* {
   box-sizing: border-box;
   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

body {
   background-color: #0e0326;
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
   /* �대���� ���� 諛곌꼍 */
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
   /* �ㅻ�� 諛곌꼍 */
   padding: 10px 20px;
   border: 1px solid white;
}

.container>.navi {
   display: flex;
   height: 5%;
   justify-content: flex-end; /* ��紐⑸�ㅼ�� �ㅻⅨ履쎌�쇰� ���� */
   align-items: center;
   background-color: #2b2d42;
   /* �ㅻ�寃��댁�� 諛곌꼍 */
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
   justify-content: space-between; /* 醫��� ���� */
   align-items: center;
   background-color: #2b2d42;
   padding: 10px 20px;
   border: 1px solid white;
}

.header ul {
   list-style: none;
   display: flex;
   gap: 15px;
   justify-content: flex-end; /* �ㅻⅨ履� ���� */
   margin-left: auto; /* �쇱そ �щ갚�� �����쇰� 留��ㅼ�댁�� �ㅻⅨ履쎌�쇰� 遺��� */
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
   /* 吏��� �� 諛곌꼍�� */
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
   border: none; /* 寃쎄��� ��嫄� */
   border-radius: 5px;
   cursor: pointer;
   transition: background-color 0.3s ease;
   margin-top: 20px; /* 踰��쇱�� ����濡� ���곌린 */
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
   justify-content: flex-start; /* 踰��쇱�� �쇱そ�쇰� ���� */
   align-items: center;
   padding: 20px;
   border: none;
}

.notice {
	background-color: 'red';
}


/* 諛����� ������ �ㅼ�� */
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
      // ���댁��ㅼ�댁�� �대┃ �대깽��
      $(".page").on("click", function() {
         let pageNum = $(this).attr("page");
         sessionStorage.setItem("last_cpage", pageNum);
      });
   });
</script>

	<div class="container">
		<!-- �ㅻ�� -->
		<div class="header">
			<div class="logo">Team CodeQuest</div>
			<ul>
				<li onclick="location.href='/'">Home</li>
				<li>Game</li>
				<li>Board</li>
				<li>Service</li>
			</ul>

			<div class="logbox">
				<span id="username"></span>
			</div>
		</div>

		<div class="navi">�� �����⑸����</div>

		<div class="body">
			<!-- 寃����� ���대� -->
			<table>
				<tr id="name">
					<td colspan="8">����寃�����</td>
				</tr>
				<tr id="title">
					<th style="width: 12%;">踰���</th>
					<th style="width: 42%;">��紐�</th>
					<th style="width: 17%;">���깆��</th>
					<th style="width: 17%;">��吏�</th>
					<th style="width: 12%;">議고��</th>
				</tr>
				<c:forEach var="dto" items="${noticeList}">
					<tr>
						<td>${dto.boardId}</td>
						<td class="contents notice"><span class="content-type">${(dto.role == 'user') ? '寃���湲�' : '怨듭�'}</span>
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
						<td class="contents"><span class="content-type">${(dto.role == 'user') ? '寃���湲�' : '怨듭�'}</span>
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

			</table>

			<div colspan="3" id="buttonbox">
				<c:if test="${dto == null}">

					<a href="/board/addform.do" method="post">
						<button>���깊��湲�</button>
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
			pageNavi.append(makeSpan('�댁��', ${page.startNavi - 1}));
		}

		for (let i = ${page.startNavi}; i <= ${page.endNavi}; i++) {
			pageNavi.append(makeSpan(i, i));
		}

		if (${!page.isEnd}) {
			pageNavi.append(makeSpan('�ㅼ��', ${page.endNavi + 1}));
		}

		const indexCss = '.page {font-size: 20px; width: 50px; height: 50px; padding-left: 5px; padding-right: 5px;}'
		const hover = '.page:hover { cursor: pointer; }'

		$('style').append(hover).append(indexCss);

		return pageNavi;
	}

	$('#number>td').append(makePageNavi('/board/list.do?cpage='));
</script>

