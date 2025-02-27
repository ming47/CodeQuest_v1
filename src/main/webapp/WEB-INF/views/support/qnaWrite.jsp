<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css"
	rel="stylesheet">
<head>
<meta charset="UTF-8">
<title>Q&A작성</title>
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
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

.container {
	flex-direction: column;
	align-items: center;
	display: flex;
	width: 100vw;
	height: 100%;
	justify-content: center;
	align-items: center;
	background-attachment: fixed;
	background: url('/images/밤.gif') no-repeat center;
	background-size: cover;
	justify-content: flex-end;
	position: relative;
	justify-content: center;
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
	width: 100vw;
}

.header {
	height: 80px;
	padding: 20px;
	width: 100%;
	position: fixed;
	top: 0;
	left: 0;
	height: 80px;
	z-index: 1000;
}

.footer {
	height: 60px;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 14px;
	margin-top: 50px;
	width: 100vw;
	bottom: 0px;
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

h1 {
	text-align: left;
	font-size: 28px;
	color: #ffffff;
	margin-bottom: 20px;
	text-transform: uppercase;
	font-weight: bold;
	letter-spacing: 2px;
	margin-top: 20px;
	text-shadow: 0 1px 0 black, -1px 2px 0 black, 1px 4px 0 black, 0 6px 0
		black;
}

.body {
	font-family: 'DungGeunMo';
	width: 70%;
	background-attachment: fixed;
	background-size: cover;
	align-items: center;
	justify-content: center;
}

.body>div {
	margin-top: 100px;
}

.logbox-container {
	right: 10px;
	top: 100px;
	position: fixed;
}

table {
	width: 100%;
	border-spacing: 10px;
}

table td {
	padding: 15px;
}

input[type="text"], input[type="file"], textarea {
	width: 100%;
	padding: 14px;
	border: 2px solid #e0dfed;
	border-radius: 8px;
	margin-top: 10px;
	font-size: 16px;
	color: black;
	background-color: #fafbf4;
}

textarea {
	height: 500px;
	resize: none;
}

.boardbox {
	border-radius: 10px;
	padding: 20px;
	margin-bottom: 20px;
	color: black;
	background-color: #fafbf4;
}

button {
	padding: 15px 20px;
	background-color: #666666;
	color: white;
	border: 3px solid white;
	letter-spacing: 5px;
	font-weight: bold;
	position: relative;
	transition: all 0.4s;
	overflow: hidden;
	border-radius: 5px;
	font-family: 'DungGeunMo';
	border-radius :5px;
	
}

button:hover {
background-color: #3c3b39;
	transform: scale(1.1);
}

}
.card {
	border-radius: 10px;
	padding: 20px;
	margin-bottom: 20px;
	color: black;
	background-color: #fafbf4;
}

.card-header {
	font-size: 18px;
	font-weight: bold;
	color: #ffffff;
	margin-bottom: 10px;
	text-transform: uppercase;
	letter-spacing: 1px;
	color: black;
}

.card-body {
	flex-direction: column;
}

.card-header {
	font-size: 18px;
	font-weight: bold;
	color: #ffffff;
	margin-bottom: 10px;
	text-transform: uppercase;
	letter-spacing: 1px;
	color: black;
}

.card-body {
	flex-direction: column;
}

.card-body input {
	margin-bottom: 15px;
}

.card-body textarea {
	margin-bottom: 15px;
}

#buttonbox {
	display: flex;
	justify-content: flex-end;
	gap: 20px;
	margin-top: 20px;
}

#writebtn, #listbtn {
	width: 150px;
}

#buttonbox a {
	text-decoration: none;
}

.note-editable {
	background-color: white !important;
	color: black !important;
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


		<c:if test="${member.loginId != null}">
			<div class="logbox-container">
				<!-- <%@ include file="/logbox.jsp"%> -->
			</div>
		</c:if>

		<div class="body">
			
			<div class="card">
				<h1>Q & A 작성</h1>
				<div class="boardbox">
				
					<form action="/member/qna/add.do" method="post">
						<input type="hidden" name="memberId" value="${member.memberId}">
						<div class="card">
							<div class="card-header"></div>
							<input type="hidden" name="contents" id="input-contents">
							<div class="card-body" id="contents"></div>
						</div>
						<div id="buttonbox">
							<button class="button" id="writebtn" type="submit">작성완료</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>
	</div>

	<script>
		$('#contents').summernote(setSummerNote());
		function setSummerNote(target) {
			console.log('서머노트 세팅');
			return {
				placeholder : '내용을 입력하십시오',
				height : 700,
				minHeight : null, // set minimum height of editor
				maxHeight : null, // set maximum height of editor
				lang : 'ko-KR',
				toolbar : [
						[ 'fontname', [ 'fontname' ] ],
						[ 'fontsize', [ 'fontsize' ] ],
						[
								'style',
								[ 'bold', 'italic', 'underline',
										'strikethrough', 'clear' ] ],
						[ 'color', [ 'forecolor', 'color' ] ],
						[ 'table', [ 'table' ] ],
						[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
						[ 'height', [ 'height' ] ],
						[ 'insert', [ 'picture', 'link', 'video' ] ] ],
				fontNames : [ 'Arial', 'Arial Black', 'Comic Sans MS',
						'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체' ],
				fontSizes : [ '8', '9', '10', '11', '12', '14', '16', '18',
						'20', '22', '24', '28', '30', '36', '50', '72' ],
				callbacks : { //여기 부분이 이미지를 첨부하는 부분
					onImageUpload : function(files) {
						console.log(files[0], this);
						uploadImage(files[0], this);
					},

					onPaste : function(e) {
						console.log(e);

						var clipboardData = e.originalEvent.clipboardData;
						if (clipboardData && clipboardData.items
								&& clipboardData.items.length) {
							var item = clipboardData.items[0];
							if (item.kind === 'file'
									&& item.type.indexOf('image/') !== -1) {
								e.preventDefault();

							}
						}
					}
				}
			};
		}

		function uploadImage(file, editor) {
			let formData = new FormData();
			formData.append('file', file);
			formData.append('request', 'board');

			$.ajax({
				url : '/file/image/upload.do',
				data : formData,
				type : 'POST',
				//dataType:"multipart/form-data", 
				contentType : false,
				processData : false,
				error : function(request, status, error) {
					console.log("code: " + request.status)
					console.log("message: " + request.responseText)
					console.log("error: " + error);
				}
			}).done(function(data) {

				$(editor).summernote('insertImage', data.path);
			});
		}

		$('form').on('submit', function() {
			$('#input-contents').val($('#contents').summernote('code'));
		});

		$("#listbtn").on("click", function() {
			let last_cpage = sessionStorage.getItem("last_cpage");
			location.href = "/board/list.do?cpage=" + last_cpage;
		});
	</script>
</body>
</html>