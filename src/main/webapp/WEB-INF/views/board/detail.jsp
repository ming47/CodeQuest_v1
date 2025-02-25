<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>게시글 상세 보기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
}

body {
	background-color: #f4f7f6;
	display: flex;
	justify-content: center;
	padding: 20px;
}

.container {
	background: #ffffff;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 700px;
	padding: 30px;
	overflow: hidden;
}

.header {
	text-align: center;
	margin-bottom: 20px;
}

.header h1 {
	font-size: 28px;
	color: #333;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

table th, table td {
	padding: 12px;
	text-align: left;
	border: 1px solid #ddd;
}

table th {
	background-color: #f2f2f2;
	font-weight: bold;
}

table td {
	vertical-align: middle;
}

/* 내용 부분만 높이 4배로 설정 */
td#contents {
	height: 200px; /* 4배 높이 */
	word-wrap: break-word;
	white-space: pre-wrap;
}

.footer {
	text-align: right;
	margin-top: 20px;
}

.footer button {
	text-decoration: none;
	background-color: #4CAF50;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	font-size: 16px;
	transition: background-color 0.3s;
}

#back {
	margin-right: 300px;
}

.footer a:hover {
	background-color: #45a049;
}

#commentSection {
	margin-top: 20px;
	padding: 15px;
	background: #f9f9 border-radius: 10px;
}

/* 댓글 입력창 스타일 */
#commentInput {
	display: flex;
	flex-direction: column;
	gap: 10px;
	float: left;
}

#input {
	width: 100%;
	height: 60px;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	resize: none;
}

#inputbtn {
	align-self: flex-end;
	background-color: #4CAF50;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	font-size: 16px;
	transition: background-color 0.3s;
	border: none;
	cursor: pointer;
}

#inputbtn:hover {
	background-color: #45a049;
}

/* 프로필 아이콘 */
.comment-item .profile-icon {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	background: #4CAF50;
	color: white;
	display: flex;
	justify-content: center;
	align-items: center;
	font-weight: bold;
	font-size: 16px;
}

/* 댓글 내용 */
.comment-content {
	flex-grow: 1;
}

.comment-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	font-size: 14px;
	color: #666;
	margin-bottom: 5px;
}

.comment-body {
	font-size: 16px;
	color: #333;
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

.comment-delete:hover {
	color: #ff0000;
}
/* 댓글 입력창과 버튼을 가로로 배치 */
#commentInputContainer {
	display: flex;
	align-items: center;
	gap: 10px;
	margin-top: 10px;
}

/* 입력창 스타일 */
#commentInput {
	flex: 1;
	height: 50px;
	padding: 12px;
	border: 1px solid #ddd;
	border-radius: 8px;
	font-size: 14px;
}

/* 등록 버튼 스타일 */
#inputbtn {
	background-color: pink;
	color: white;
	padding: 12px 20px;
	border-radius: 8px;
	font-size: 16px;
	border: none;
	cursor: pointer;
	transition: background-color 0.3s;
}

#inputbtn:hover {
	background-color: white;
	color: pink;
}
/* 이모티콘 */
.emoji-btn {
    cursor: pointer;
    font-size: 1.5em;
    padding: 5px;
    display: inline-block;
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

</style>
<script>


window.onload = function(){
   //페이지 로딩이 완료되었을때, 서버에서 현재 글의 댓글 목록을 받아와 화면에 동적으로 구성하기 
   $(document).ready(function() {
	   
	   const $commentInput = $("#commentInput");
       const $inputBtn = $("#inputbtn");
       
       // 초기상태 버튼 비활성화
       $inputBtn.prop("disabled", true);
       $inputBtn.css({
           "background-color": "#ffd1dc",
           "cursor": "not-allowed"
       });

       // 입력창 이벤트 리스너
       $commentInput.on("input", function() {
           if ($(this).val().trim() === "") {
               $inputBtn.prop("disabled", true);
               $inputBtn.css({
                   "background-color": "#ffd1dc",
                   "cursor": "not-allowed"
               });
           } else {
               $inputBtn.prop("disabled", false);
               $inputBtn.css({
                   "background-color": "pink",
                   "cursor": "pointer"
               });
           }
       });

       $("#frm").on("submit", function(e) {
           e.preventDefault();
           
           let commentText = $commentInput.val().trim();
           if (commentText === "") {
               alert("댓글을 입력하세요");
               return false;
           }

           this.submit();
       });
	   
    // 댓글 목록 불러오기
    $.ajax({
        url: "/reply/ContentsAll.do",
        data: { 'boardId': ${dto.boardId} },
        type: "get"
    }).done(function(data) {
       try{
        data = JSON.parse(data);}
       catch (e) {
            console.error("Error parsing JSON: ", e);
            return;
        }
			let UserName = "${member.nickName}";	//작성자
			let Master = "${member.role}";	// 관리자

        for (let i = 0; i < data.length; i++) {
            let commentItem = $("<li>").addClass("comment-item").attr("data-id", data[i].replyId);
            
            let profileIcon = $("<div>").addClass("profile-icon").text(data[i].writer.charAt(0));
            let contentDiv = $("<div>").addClass("comment-content writerdiv").html(data[i].contents).attr("data-original", data[i].contents);
            let commentHeader = $("<div>").addClass("comment-header").text(data[i].writer + " · " + data[i].regDate);
            
            let btnBox = $("<div>").addClass("btnbox");


            if (data[i].writer === UserName || Master === "admin") {	//관리자이거나 작성자일 경우 보이게하기
                let updateBtn = $("<button>").addClass("updatebtn").text("수정");
                let deleteBtn = $("<button>").addClass("deletebtn").text("삭제");
                btnBox.append(updateBtn, deleteBtn);
            }
            commentItem.append(profileIcon, commentHeader, contentDiv, btnBox);
            $("#commentList").append(commentItem);
        }
        

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
            let updateOK = $("<button>").addClass("updateOK").text("수정완료");
            let updateCancel = $("<button>").addClass("updateCancel").text("취소");
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
    }); // 여기에 닫는 괄호 추가
    }); // $(document).ready 끝
}; // window.onload 끝





</script>
</head>
<body>

	<div class="container">
		<div class="header">
			<h1>게시글 상세 보기</h1>
		</div>

		<table>
			<tr>
				<th>글 번호</th>
				<td>${dto.boardId}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.writer}</td>
			</tr>
			<tr>
				<th>작성 날짜</th>
				<td>${dto.regDate}</td>
			</tr>

			<tr>
				<th>첨부된 파일:</th>
				<td><c:forEach var="i" items="${filelist}">
						<a
							href="/file/download.do?filename=${i.sysname}&oriname=${i.oriname}">${i.oriname}
						</a>
						<br>
					</c:forEach></td>
			</tr>

			<tr>

				<th>제목</th>
				<td class="change" id="board_title">${dto.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<!-- 내용 부분에 높이를 4배로 설정 -->
				<td class="change" id="board_contents">${dto.contents}</td>
			</tr>

		</table>





		<div class="commentSection">

			<form action="/reply/add.do" method="post" id="frm">
				<!-- 댓글 목록 -->
				<div id="commentInputContainer">
					<input name="parent_seq" type="hidden" value="${dto.boardId}">
					<input type="hidden" id="memberId" name="memberId"
						value="${sessionScope.MemberId}"> <input id="commentInput"
						name="contents" placeholder="댓글을 입력하세요">
						
					<div class="emoticons" style="display: none;">
						<div class="emoticon">
							<span class="emoji-btn">😀</span>
        					<span class="emoji-btn">😊</span>
       						<span class="emoji-btn">😎</span>
        					<span class="emoji-btn">😍</span>
        					<span class="emoji-btn">🎉</span>
        					<span class="emoji-btn">👍</span>
						</div>
					</div>
			
					
				</div>
						<div class = "buttonContainer">
						<button id="emojiBtn" type = "button">😀</button>
						<button id="inputbtn">등록</button>
					</div>
			</form>
			<div id="comments">
				<ul id="commentList"></ul>
				<!-- AJAX로 댓글이 추가될 부분 -->
			</div>
		</div>
		<form action="/board/update.do" method="post" id="update-form">
			<input id="id" type="hidden" name="id" value="${dto.boardId}">
			<input name="title" type="hidden" id="hdtitle"> <input
				name="contents" type="hidden" id="hdcontents">
			<div class="footer">
			
				<button type="button" id="back">목록으로</button>

				<!-- 여기 게시글 수정 삭제 버튼  -->

				<c:if test="${dto.getMemberId() == member.getMemberId() || member.role == 'admin'}">
				
					<td class="reply_button_area-${item.id}">
						<button id="update" type="button">수정하기</button>
						<button id="delete" type="button">삭제하기</button> 
						</td>
						</c:if>
						
						<script>
						
						
               $("#inputbtn").on(
                     "click",
                     function() {
                        let commentText = $("#commentInput").val();
                        if (commentText == "") {
                           alert("댓글을 입력하세요")
                           return;
                        }
                        let updatecontents = $("<div>").addClass(
                              "comment-box");
                        $("#comments").append(updatecontents);
                        $("#commentsInput").val("");
                     });
               
               
               
              $(".deletebtn").on("click", function(){
                  let target = $(this).attr("seq");
                  
                  location.href = "/delete.reply" + target;

               let last_cpage = sessionStorage.getItem("last_cpage");
               location.href = "/list.board?cpage=" + last_cpage;
              });
             
               $(".updatebtn").on("click",   function(){
                  
                        //댓글 수정하기 버튼 눌렀을때    
                           
                        $(".writerdiv").attr("contentEditable", "true").focus();
                        

                        $(".updatebtn,.deletebtn").hide();
                        //기존에 있던 버튼 숨기기 
                        let updateOK = $("<button>");
                        updateOK.html("수정완료").attr("id", "updateOK");

                        let updateCancel = $("<button>");
                        updateCancel.html("취소").attr("id","updateCancel")
                              

                        updateCancel.attr("type", "button");

                        updateCancel.on("click", function() {
                           location.reload();
                        });

                        $(".btnbox").append(updateOK, updateCancel);

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
                 $('#board_title').focus();
                    
                    $("#update,#delete").hide();
                    //기존에 있던 버튼 숨기기 
                    let updateOK = $("<button>");
                    updateOK.html("수정완료").attr("id", "board-updateOK");

                    let updateCancel = $("<button>");
                    updateCancel.html("취소").attr("id","updateCancel")
                          

                    updateCancel.attr("type", "button");

                    updateCancel.on("click", function() {
                       location.reload();
                    });
                    
                    $(".footer").append(updateOK, updateCancel);
               
                 
               
                });
            
                
                
                //댓글 수정하기 버튼 눌렀을때   
                $(".updatebtn").on("click", function(){
          
                       
                    $(".writerdiv").attr("contentEditable", "true").focus();
                    

                    $(".updatebtn,.deletebtn").hide();
                    //기존에 있던 버튼 숨기기 
                    let updateOK = $("<button>");
                    updateOK.html("수정완료").attr("id", "reply-updateOK");

                    let updateCancel = $("<button>");
                    updateCancel.html("취소").attr("id","updateCancel")
                          

                    updateCancel.attr("type", "button");
                    
                    
                    updateCancel.on("click", function() {
                       location.reload();
                    });

                    $(".btnbox").append(updateOK, updateCancel);

                // "수정완료" 버튼 클릭 시 처리                   
                   updateOK.on("click", function() {
                       let updatedContent = contentDiv.html(); // 수정된 내용을 가져옴
                       let replyId = commentItem.find("input[name='id']").val(); // 댓글 ID 가져옴

                       // AJAX 요청을 통해 서버에 수정된 댓글 전송
                       $.ajax({
                           url: '/reply/update.do', // 댓글 수정 API URL
                           type: 'POST',
                           data: {
                               id: replyId,
                               contents: updatedContent
                           },
                           success: function(response) {
                               // 수정이 성공하면 댓글 내용을 업데이트
                               contentDiv.html(updatedContent); // 수정된 내용을 댓글에 반영

                               // 원래의 수정/삭제 버튼 다시 보이게 하기
                               commentItem.find(".updatebtn, .deletebtn").show();

                               // "수정완료"와 "취소" 버튼 제거
                               updateOK.remove();
                               updateCancel.remove();
                           }
                       });
                   });	
                
                
                 // "취소" 버튼 클릭 시 처리
                   updateCancel.on("click", function() {
                       // 수정 취소 시 원래의 내용으로 되돌리기
                       contentDiv.html(contentDiv.attr("data-original-content"));

                       // 원래의 수정/삭제 버튼 다시 보이게 하기
                       commentItem.find(".updatebtn, .deletebtn").show();

                       // "수정완료"와 "취소" 버튼 제거
                       updateOK.remove();
                       updateCancel.remove();
                   });
               
                    
                });
               
                   
              
               $('#update-form').on('submit', function() {
            	   $('#hdtitle').val($('#board_title').html());
            	   $("#hdcontents").val($("#board_contents").html());
         
               });// 게시물 수정 
               
               $("#frm").on("submit", function() {

                  $("#hdtitle").val($("#board_title").html())
                  $("#hdcontents").val($("#board_contents").html())
            
               })//댓글 수정 
               
            </script>
			</div>
		</form>
		<script>
            $("#back").on("click", function() {

               let last_cpage = sessionStorage.getItem("last_cpage");
               location.href = "/board/list.do?cpage=" +last_cpage;  });

         </script>
         
         <script>	//이모티콘
         let isEmoticonPanelOpen = false;
         $("#emojiBtn").on("click", function(){
       	  isEmoticonPanelOpen = !isEmoticonPanelOpen;
       	  
       	  if(isEmoticonPanelOpen){	
       		  // 이모티콘 패널을 열면
       		  $(this).text("🤢");
       		  $(".emoticons").show();
       		  $(".emoticon").css({
       			  'background': 'linear-gradient(to bottom, #bacee0 0%, rgba(42, 81, 18950, 0.51) 100%)',
                     'transition': 'background 1s ease'
       		  });
       	  } else{
       		  //이모티콘 패널을 닫으면
       		  $(this).text("😀");
       		  $(".emoticons").hide();
       		  $(".emoticon").css({
       			  'background': '#bacee0',
                     'transition': 'background 0.5s ease'
       		  });
       	  }
         });
         $(".emoji-btn").on("click", function(){
        	    let emotion = $(this).text();
        	    let currentText = $('#commentInput').val();
        	    
        	    //$('#a').html(currentText + emotion);
        	    //$('#a').append(emotion);
        	    
        	    $('#commentInput').val(currentText + emotion);	//입력창에 이모티콘 넣기
        	    //$('#commentInput').val($('#a').html()).trigger('input');
        	    
        	    $("#commentInput").focus();
        	    //$(".emoticons").hide();	//여기있으면 하나 넣을때마다 패널 닫아버림ㅇㅇㅇ
        	    $("#emojiBtn").text("😀");
        	    isEmoticonPanelOpen = false;
        	    
        	});
         
         $('form').on("submint", function() {
        	 $('#commentInput').val($('#a').html());
         });
         
         </script>

	</div>
</body>
</html>
