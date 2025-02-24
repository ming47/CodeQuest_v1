<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
<head>
<meta charset="UTF-8">
<title>게시글쓰기</title>
<style>
* {
   box-sizing: border-box;
   margin: 0;
   padding: 0;
   font-family: 'Arial', sans-serif;
}

body {
   background: #0e0326; /* 어두운 남색 그라데이션 배경 */
   display: flex;
   justify-content: center;
   padding: 50px;
}

.container {
   background-color: #2b2d42;
   box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
   width: 1000px;
   padding: 40px;
   color: white;
   height: 100%;
   max-width: 90%; /* 화면 너비에 따라 조정 */
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

h1 {
   text-align: left; /* 왼쪽 정렬 */
   font-size: 28px;
   color: #ffffff;
   margin-bottom: 20px;
   text-transform: uppercase;
   font-weight: bold;
   letter-spacing: 2px;
   margin-top: 20px;
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
   border: 2px solid #e0dfed; /* 선 색상 변경 */
   border-radius: 8px;
   margin-top: 10px;
   font-size: 16px;
   background-color: #1f2335; /* 어두운 배경 */
   color: white;
}

textarea {
   height: 500px;
   resize: none;
}

.button {
   background-color: #1d1f30; /* 남색 */
   color: white;
   border: none;
   padding: 14px 22px;
   border-radius: 8px;
   font-size: 16px;
   cursor: pointer;
   transition: background-color 0.3s ease;
   width: 48%;
   margin-top: 10px;
   text-align: center;
   display: inline-block; /* inline-block으로 버튼들이 가로로 정렬될 수 있도록 */
}

button:hover {
   background-color: #141829; /* 어두운 남색 */
}

a button {
   background-color: #1d1f30; /* 남색 */
}

a button:hover {
   background-color: #141829; /* 어두운 남색 */
}

.card {
   background-color: #2b2d42;
   border: 1px solid #e0dfed; /* 선 색상 변경 */
   border-radius: 10px;
   padding: 20px;
   margin-bottom: 20px;
}

.card-header {
   font-size: 18px;
   font-weight: bold;
   color: #ffffff;
   margin-bottom: 10px;
   text-transform: uppercase;
   letter-spacing: 1px;
}

.card-body {
   display: flex;
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
   justify-content: space-between;
   margin-top: 20px;
   gap: 10px; /* 버튼들 간의 간격을 추가 */
}

#buttonbox a {
   text-decoration: none;
}

#writebtn {
   width: 100%; /* 버튼이 100%의 너비를 가지도록 수정 */
}

#listbtn {
   width: 100%; /* 버튼이 100%의 너비를 가지도록 수정 */
}

.note-editable {
   background-color: #1f2335 !important; 
   color: white !important;
}

</style>
</head>
<body>

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
			<h1>게시글 작성하기</h1>

			<form action="/board/add.do" method="post"
				enctype="multipart/form-data">

				<div class="card">
					<div class="card-header">제목 입력</div>
					<div class="card-body">
						<input type="text" name="title" placeholder="제목을 입력해주세요" required>
					</div>


					<div class="card-header">파일 첨부</div>
					<div class="card-body">
						<input type="file" name="file2" accept="image/*, .pdf, .docx">

					</div>


					<div class="card-header">내용 입력</div>
					<input type="hidden" name="contents" id="input-contents">
					<div class="card-body" id="contents"></div>

				</div>
				<div id="buttonbox">

					<button class="button" id="writebtn" type="submit">작성완료</button>
					
	                   <a href="/list.board?cpage=1">

						<button class="button" id="listbtn" type="button">목록으로</button>
					  </a>
				


				</div>
			</form>
		</div>
	</div>

</body>
</html>
<script>

	$('#contents').summernote(setSummerNote());
	

	

	
	function setSummerNote(target) {
		console.log('서머노트 세팅');

		return {
			placeholder : '내용을 입력하십시오',
			height : 500,
			minHeight : null, // set minimum height of editor
			maxHeight : null, // set maximum height of editor
			lang : 'ko-KR',
			toolbar : [
					[ 'fontname', [ 'fontname' ] ],
					[ 'fontsize', [ 'fontsize' ] ],
					[
							'style',
							[ 'bold', 'italic', 'underline', 'strikethrough',
									'clear' ] ],
					[ 'color', [ 'forecolor', 'color' ] ],
					[ 'table', [ 'table' ] ],
					[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
					[ 'height', [ 'height' ] ],
					[ 'insert', [ 'picture', 'link', 'video' ] ] ],
			fontNames : [ 'Arial', 'Arial Black', 'Comic Sans MS',
					'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체' ],
			fontSizes : [ '8', '9', '10', '11', '12', '14', '16', '18', '20',
					'22', '24', '28', '30', '36', '50', '72' ],
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
	        location.href = "/board/list.do?cpage=" +last_cpage;});

  
   

</script>
