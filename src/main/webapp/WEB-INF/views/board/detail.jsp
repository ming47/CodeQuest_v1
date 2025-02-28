<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>게시글 상세 보기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
	
<script src="/images/emoji-button-3.0.3.min.js"></script>
	
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

html, body {
	width: 100%;
	height: 100vh;
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

li {
	list-style-type: none;
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
	position: absolute;
	right: 10px;
	bottom: -35px;
	top: 80px;
	height: fit-content;
}

.body {
	margin: 0;
	width: 100%;
	background-attachment: fixed;
	background: url('/images/밤.gif') no-repeat center;
	background-size: cover;
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
}

.container {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	color: white;
	font-family: 'DungGeunMo';
	
	padding: 0;
}

.body-inner {
	border: none;
	background-color: rgb(255, 255, 255, 0.5);
	border-radius: 16px;
	box-shadow: inset 0 0 8px #424242;
	padding: 20px;
	margin-top: 40px;
	margin-bottom: 50px;
	width: 50vw;
}

.top {
	text-align: center;
	margin-bottom: 20px;
}

.header h1 {
	font-size: 28px;
	color: #333;
}

table {
	border-collapse: collapse;
	width: 48vw;
	font-size: 19px;
	border-spacing: 3px;
	border-collapse: separate;
	background-color: transparent;
}

table th, table td {
	padding: 10px;
	border-radius: 1px;
	border: none;
	font-family: 'DungGeunMo';
	color: black;
	background-color: #fafbf4;
}

table tr {
	border-radius: 1px;
	font-family: 'DungGeunMo';
}

td {
	color: black;
	border-radius: 1px;
	font-family: 'DungGeunMo';
}

td#contents {
	height: 200px;
	word-wrap: break-word;
	white-space: pre-wrap;
}

.bottom {
	text-align: right;
	margin-top: 20px;
	position: relative;
	height: 50px;
}
/*
.bottom button {
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
*/
.button {
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

#back {
	position : absolute;
	left: 20px;
	margin-right: 300px;
}

.bottom a:hover {
	background-color: #45a049;
}

#commentSection {
	margin-top: 20px;
	padding: 15px;
	background-color: rgb(255, 255, 255, 0.5);
	border-radius: 10px;
	position: relative;
}

#commentInput {
	display: flex;
	flex-direction: column;
	gap: 10px;
	float: left;
	color: black;
}

#input {
	width: 100%;
	height: 60px;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	resize: none;
}

.pageNavi>span {
	color: white;
	margin: 5px;
	border: 3px solid white;
	background-color: #3c3b39;
	border-radius: 10px;
}

.pageNavi>span:hover {
	background: #3c3b39;
}

/* 댓글 내용 */
.comment-content {
	flex-grow: 1;
	margin-top: 20px;
	padding-left: 10px;
	height: 50px;
}

#commentList {
	margin: 10px;
}

.comment-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	color: #666;
	margin-bottom: 5px;
	padding-left: 10px;
	background-color: #fafbf4;
}

.comment-item {
	margin-top: 30px;
	font-size: 20px;
}
/* 삭제 버튼 스타일 */
.comment-delete {
	background: none;
	border: none;
	color: #ff5555;
	cursor: pointer;
	font-size: 14px;
	transition: color 0.2s;
}

#commentInputContainer {
	display: flex;
	align-items: center;
	gap: 10px;
	margin-top: 10px;
}

.buttonContainer {
	height: 50px;
	position: relative;
}

.buttonContainerInner {
	position: absolute;
	right: 30px;
}

/* 입력창 스타일 */
#commentInput {
	flex: 1;
	height: 50px;
	padding: 12px;
	border: 1px solid #ddd;
	border-radius: 8px;
	font-size: 14px;
	margin-bottom: 10px;
	width: 50px;
}

.emoji-btn {
	cursor: pointer;
	font-size: 1.5em;
	padding: 20px;
	display: inline-block;
}

.updatebtn, .deletebtn {
	margin: 10px;
	padding: 10px;
	background-color: #3c3b39;
	border: none;
	border-radius: 8px;
}

.emoji-btn:hover {
	transform: scale(1.2);
	transition: transform 0.2s;
}

.emoticon {
	display: grid;
	grid-template-columns: repeat(6, 1fr);
	gap: 10px;
	padding: 10px;
	background: white;
	border: 1px solid #ddd;
	border-radius: 5px;
}

.pageNaviForm {
	display: flex;
	justify-content: center;
   	align-items: center;
}

.relative-date{
	padding:5px;
}

table th {
	width: 10vw;
}

#board_contents {
  overflow: auto;
  word-wrap: break-word;
  width: 95%;
  height: 95%;
}

.text-center {
	text-align: center;
}

.updateCancel {
  color: #ddd;
  background-color: #3c3b39;
  float: right;
  margin-left: 10px; /* 이 값을 늘려 간격 확보 */
  margin-top : 10px;
  border: 1px solid #555;
  border-radius: 4px;
  cursor: pointer;
  padding: 3px 5px;
  font-size::15;
}

.updateOK {
  color: white;
  background-color: #3c3b39;
  float: right;
  margin-left: 15px; /* 간격 확보 */
  margin-top : 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  padding: 3px 5px;
  font-size::15;
}

/* 호버 효과 추가 */
.updateCancel:hover {
  	background: #66635f;
	transform: scale(1.1);
	color: white;
}

.updateOK:hover {
	background: #66635f;
	transform: scale(1.1);
	color: white;
}
#board-updateOK{
  color: white;
  background-color: #3c3b39;
  float: right;
  margin-left: 15px; /* 간격 확보 */
  border: none;
  border-radius: 4px;
  cursor: pointer;
  padding:8px 12px;
  font-size::15;
}
#updateCancel{
  color: #ddd;
  background-color: #3c3b39;
  float: right;
  margin-left: 10px; /* 이 값을 늘려 간격 확보 */
  border: 1px solid #555;
  border-radius: 4px;
  cursor: pointer;
  font-family: 'DungGeunMo';
  padding:8px 12px;
  font-size::15;
}
#board-updateOK:hover{
	background: #66635f;
	transform: scale(1.1);
	color: white;
}
#updateCancel:hover{
	background: #66635f;
	transform: scale(1.1);
	color: white;
}

</style>
</head>
<body>
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

	<c:if test="${member.memberId != null}">
		<div class="logbox-container">
			<%@ include file="../../../logbox.jsp"%>
		</div>
	</c:if>
	<div class="container">
		<div class="body">
			<div class="body-inner">
				<div class="top">
					<h1>게시글 상세 보기</h1>
				</div>
				<table>
					<tr>
						<th>제목</th>
						<td class="change text-center" id="board_title" colspan="3">${dto.title}</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td class="text-center" colspan="3">${dto.writer}</td>
					</tr>
					<tr>
						<th width="20%">글 번호</th>
						<td class="text-center" width="10%">${dto.boardId}</td>
						<th width="20%">작성 날짜</th>
						<td class="text-center" width="50%">${dto.regDate}</td>
					</tr>
					<tr>
						<th>첨부 파일:</th>
						<td class="text-center" colspan="3"><c:forEach var="i" items="${filelist}">
								<a href="/file/download.do?filename=${i.sysname}&oriname=${i.oriname}">${i.oriname}
								</a>
								<br>
							</c:forEach></td>
					</tr>
					<tr>
						<!-- 내용 부분에 높이를 4배로 설정 -->
						<td colspan="5" height="500px">
							<div class="change" id="board_contents">
								${dto.contents}
							</div>
						</td>
					</tr>
				</table>

				<div class="commentSection">
					<!-- 댓글 목록 -->
					<div id="commentInputContainer">
						<input id="commentInput" name="contents" placeholder="댓글을 입력하세요">
						<div class="emoticons" style="display: none;">
							<div class="emoticon">
							</div>
						</div>
					</div>
					<div class="buttonContainer">
						<span class="buttonContainerInner">
						<button class="button" id="emojiBtn" type="button">😀</button>
						<button class="button" id="inputbtn" type="button">등록</button>
						</span>
					</div>
					<div id="comments">
						<ul id="commentList"></ul>
						<!-- AJAX로 댓글이 추가될 부분 -->
					</div>
					<div class="pageNaviForm"></div>
				</div>
				<form action="/board/update.do" method="post" id="update-form">
					<input id="id" type="hidden" name="id" value="${dto.boardId}">
					<input name="title" type="hidden" id="hdtitle"> <input
						name="contents" type="hidden" id="hdcontents">
					<div class="bottom">
						<button class="button" type="button" id="back">목록으로</button>
						<!-- 여기 게시글 수정 삭제 버튼  -->
						<c:if
							test="${dto.getMemberId() == member.getMemberId() || member.role == 'admin'}">
							<td class="reply_button_area-${item.id}">
								<button class="button" id="update" type="button">수정하기</button>
								<button class="button" id="delete" type="button">삭제하기</button>
							</td>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="footer">© 2025 Team CodeQuest. All rights reserved.</div>
</body>
<script>
   function makePageNavi(dto, className) {
       $('.' + className).remove();
       $('.' + className).off('click');
    
       console.log(dto);

       const pageNavi = $('<div>').addClass('pageNavi');
       pageNavi.css({
           'width': 'fit-content',
           'height': 'fit-content'
       });

       function makeSpan(content, index) {
           const span = $('<span>').html(content).addClass('page');
           span.attr('value', index);
           return span;
       }

       if (!dto.isFirst) {
           pageNavi.append(makeSpan('이전', dto.startNavi - 1).addClass(className).attr('tag', 'prev'));
       }

       for (let i = dto.startNavi; i <= dto.endNavi; i++) {
           pageNavi.append(makeSpan(i, i).addClass(className));
       }

       if (!dto.isEnd) {
           pageNavi.append(makeSpan('다음', dto.endNavi + 1).addClass(className).attr('tag', 'next'));
       }

       const indexCss = '.page {font-size: 20px; width: 50px; height: 50px; padding-left: 5px; padding-right: 5px;}'
       const hover = '.page:hover { cursor: pointer; }'

       $('style').append(hover).append(indexCss);

       return pageNavi;
   }

      function makeCommentItem(data){
        let UserName = "${member.nickName}";   //작성자
        let Master = "${member.role}";   // 관리자
        
        $("#commentList").empty();

        for (let i = 0; i < data.length; i++) {
           let commentItem = $("<li>").addClass("comment-item").attr("data-id", data[i].replyId);
               
            let contentDiv = $("<div>").addClass("comment-content writerdiv").html(data[i].contents).attr("data-original", data[i].contents);
            let commentHeader = $("<div>").addClass("comment-header").text(data[i].writer);
            commentHeader.append($('<span>').html(data[i].regDate).addClass('relative-date').attr('data-timestamp', Date.parse(data[i].regDate)));
               
            let btnBox = $("<div>").addClass("btnbox buttonContainer");

            if (data[i].writer === UserName || Master === "admin") {   //관리자이거나 작성자일 경우 보이게하기
               let updateBtn = $("<button>").addClass("updatebtn").text("수정");
                let deleteBtn = $("<button>").addClass("deletebtn").text("삭제");
                
                btnBox.append($('<span>').addClass('buttonContainerInner').append(updateBtn, deleteBtn));
            }
            commentItem.append(commentHeader, contentDiv, btnBox);
                  
            $("#commentList").append(commentItem);
      }
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
           
        // 댓글 수정 기능
        $(".updatebtn").on("click", function() {
           let commentItem = $(this).closest(".comment-item");
            let contentDiv = commentItem.find(".writerdiv");
               
            // 기존 내용 저장
            contentDiv.attr("data-original", contentDiv.html());
               
            // 수정 가능하도록 설정
            contentDiv.attr("contentEditable", "true").focus();

            // 기존 버튼 숨기기
            commentItem.find(".updatebtn, .deletebtn").hide();

            // 수정완료 & 취소 버튼 추가
            let updateOK = $("<button>").addClass("updateOK button").text("수정완료");
            let updateCancel = $("<button>").addClass("updateCancel button").text("취소");
            commentItem.find(".btnbox").append(updateOK, updateCancel);

            // 수정완료 버튼 클릭
            updateOK.on("click", function() {
               let updatedContent = contentDiv.html();
                let replyId = commentItem.attr("data-id");

                // 서버로 수정 요청
                $.ajax({
                   url: "/reply/update.do",
                    type: "post",
                    data: { id: replyId, contents: updatedContent },
                    success: function(response) {
                       // 성공하면 수정된 내용 유지
                        if(response){
                           contentDiv.attr("contentEditable", "false");
                            contentDiv.attr("data-original", updatedContent);
                           
                              // 버튼 복구
                             commentItem.find(".updatebtn, .deletebtn").show();
                             updateOK.remove();
                             updateCancel.remove();
                        }else{
                           alert("수정을 못했습니다.");
                        }
                      }
                  });
              });

              // 취소 버튼 클릭
              updateCancel.on("click", function() {
              // 원래 내용으로 되돌리기
              contentDiv.html(contentDiv.attr("data-original"));
              contentDiv.attr("contentEditable", "false");

              // 버튼 복구
              commentItem.find(".updatebtn, .deletebtn").show();
              updateOK.remove();
              updateCancel.remove();
              });
      });

        // 댓글 삭제 기능
        $(".deletebtn").on("click", function() {
           let commentItem = $(this).closest(".comment-item");
            let replyId = commentItem.attr("data-id");

            if (confirm("정말 삭제하시겠습니까?")) {
               $.ajax({
                   url: "/reply/delete.do",
                    type: "post",
                    data: { id: replyId, boardId : ${dto.boardId}},
                    success: function(response) {
                       // 삭제 성공하면 해당 댓글을 화면에서 제거
                        if(response) {                           
                           commentItem.remove();
                        } else {
                           alert("삭제하지 못했습니다.");
                        }
                    }
                  });
              }
       });
    }
      
      function makeCommentPageNavi(pageNavi, className) {
        $('.pageNaviForm').append(makePageNavi(pageNavi, className));
        
        $('.replyPageNavi').on('click', function() {
           const clicked = $(this);   
           
          $.ajax({
             url: '/reply/list.do?boardId=${dto.boardId}&page=' + clicked.attr('value')
          }).done(function(data) {
             data = JSON.parse(data);
             
             console.log(data);
             makeCommentItem(data.list);
             
            if(typeof clicked.attr('tag') != 'undefined') {
               console.log('걸림');
               makeCommentPageNavi(data.pageNavi, className);
         }
          });
       });
      }
   
   function commentInput($commentInput, $inputBtn) {   //키보드 이벤트
      let commentText = $("#commentInput").val().trim();
       if (commentText == "") {
          alert("댓글을 입력하세요")
          return;
      } else {
         let isLoggedIn = "${member.memberId}" !== ""; 
          let isBanned   = "${member.isbanned}" == "true";

          if (!isLoggedIn) {
              alert("회원만 글쓰기가 가능합니다.");
              event.preventDefault(); // 페이지 이동 방지
              return false;
          } else if(isBanned) {
                $.ajax({
                   url: '/service/member/ban/detail.do?id=${member.memberId}',
                   type: 'GET'
                }).done(function(data) {
                   data = JSON.parse(data);
                
                   let message = "현재 차단된 계정입니다.\n차단 이유: " + data.reason + "\n" 
                      + "차단 기간: " + parseDate(data.startDate) + " ~ " + parseDate(data.endDate);
                   alert(message);
                });
     
              event.preventDefault();
              return false;
          }

           $.ajax({
              url: '/reply/add.do',
              type: 'POST',
              data: {
                    boardId: ${dto.boardId},
                    contents: $commentInput.val()
              } 
          }).done(function(data) {
        	  console.log("123");
              alert('댓글이 등록되었습니다.');
              $.ajax({
                  url: "/reply/list.do",
                     data: { 'boardId': ${dto.boardId},
                              'page': 1},
                     type: "get"
                 }).done(function(data) {
                     try{
                         data = JSON.parse(data);}
                     catch (e) {
                         console.error("Error parsing JSON: ", e);
                         return;
                     }
                   
                     console.log(data);
                     makeCommentItem(data.list);
                     makeCommentPageNavi(data.pageNavi, 'replyPageNavi');
                  }); 
              $commentInput.val("");
          });  
      }
       
       let updatecontents = $("<div>").addClass("comment-box");
       $("#comments").append(updatecontents);
       console.log("rjffla");
        
       validInput($inputBtn);
   }
   
   function validInput($inputBtn) {   //등록 버튼 이벤트
      if ($("#commentInput").val().trim() === "") {
           $inputBtn.prop("disabled", true);

        } else {
              $inputBtn.prop("disabled", false);
              $inputBtn.css({
                 "cursor": "pointer"
              });
        }
   }

   //페이지 로딩이 완료되었을때, 서버에서 현재 글의 댓글 목록을 받아와 화면에 동적으로 구성하기
   $(document).ready(function() {
       const $commentInput = $("#commentInput");
       const $inputBtn = $("#inputbtn");
          
       // 초기상태 버튼 비활성화
       $inputBtn.prop("disabled", true);
       
       $commentInput.on('keyup', function(event) {
          if(event.key == "Enter") {             
            commentInput($commentInput, $inputBtn);
          }
          validInput($inputBtn);
       });


         // 입력창 이벤트 리스너
       $commentInput.on("input", function() {
          validInput($inputBtn);
       });

      $inputBtn.on("click",function() {
         commentInput($commentInput, $inputBtn);
      });
      
      // 댓글 목록 불러오기
      $.ajax({
       url: "/reply/list.do",
          data: { 'boardId': ${dto.boardId},
                   'page': 1},
          type: "get"
      }).done(function(data) {
          try{
              data = JSON.parse(data);}
          catch (e) {
              console.error("Error parsing JSON: ", e);
              return;
          }
        
          makeCommentItem(data.list);
          makeCommentPageNavi(data.pageNavi, 'replyPageNavi');
       }); 
   });
   

    // 게시물 삭제하기 눌렀을때
    $("#delete").on("click", function() {                 
       if (confirm("정말 삭제하시겠습니까")) {
           location.href = "/board/delete.do?id=${dto.boardId}";
        }                 
    }); 

    // 게시물 수정하기 눌렀을때 
    $("#update").on("click", function() {
       $(".change").attr("contentEditable", "true");
        //$('#board_contents').summernote(setSummerNote());
            
          $('#board_title').focus();
        $("#update,#delete").hide();
        
        //기존에 있던 버튼 숨기기 
        let updateOK = $("<button>");
        updateOK.html("수정완료").attr("id", "board-updateOK").addClass("button");

        let updateCancel = $("<button>");
        updateCancel.html("취소").attr("id","updateCancel").addClass("button");            
        updateCancel.attr("type", "button");
        
        updateCancel.on("click", function() {
           location.reload();
      });
                    
       $(".bottom").append(updateOK, updateCancel);
    });
   
              
    $('#update-form').on('submit', function() {
       $('#hdtitle').val($('#board_title').html());
        $("#hdcontents").val($("#board_contents").html());
      });// 게시물 수정 
               
    $("#frm").on("submit", function() {
       $("#hdtitle").val($("#board_title").html())
        $("#hdcontents").val($("#board_contents").html())
    })//댓글 수정 

    $("#back").on("click", function() {
       let last_cpage = sessionStorage.getItem("last_cpage");
        location.href = "/board/list.do?cpage=" +last_cpage;  });
            
        let isEmoticonPanelOpen = false;	//이모티콘이 닫혔다고 알려주는 논리 변수
        
        const picker = new EmojiButton({
            i18n: {
                search: 'Search emojis...',
                categories: {
                    recents: 'Recent Emojis',
                    smileys: 'Smileys & Emotion',
                    people: 'People & Body',
                    animals: 'Animals & Nature',
                    food: 'Food & Drink',
                    activities: 'Activities',
                    travel: 'Travel & Places',
                    objects: 'Objects',
                    symbols: 'Symbols',
                    flags: 'Flags'
                },
                notFound: 'No emojis found'
                },
                showSearch: false,
                autoFocusSearch: false,
                theme: 'dark'
        });
        picker.on('emoji', emoji => {
            document.querySelector('#commentInput').value += emoji;
            validInput($("#inputbtn"));
          });
        
        const button = document.querySelector('#emojiBtn');
        button.addEventListener('click', () => {
               picker.togglePicker(button);             
         });
        
         $(".emoji-btn").on("click", function(){

               let emotion = $(this).text();
               let currentText = $('#commentInput').val();
               
               $('#commentInput').val(currentText + emotion);   //입력창에 이모티콘 넣기
               $("#commentInput").focus();
               validInput($('#inputbtn'));

               $("#emojiBtn").text("😀");
         });
        
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
                      for(let i = 0; i < files.length; i++) {
	                      uploadImage(files[i], this);
                      }
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
          
          function parseDate(timestamp) {
            const date = new Date(timestamp);
            return date.getFullYear() + '년 ' + Number(date.getMonth() + 1) + '월 ' + date.getDate() + '일 ' +  date.getHours() + 
                  '시 ' + date.getMinutes() + '분';       
         }
      
</script>
</html>