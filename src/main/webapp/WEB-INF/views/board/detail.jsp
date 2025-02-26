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
<script
   src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link
   href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css"
   rel="stylesheet">
<script
   src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
<style>
* {
   box-sizing: border-box;
   margin: 0;
   padding: 0;
   font-family: Arial, sans-serif;
}


html, body {
   width: 100%;
   height: 100vh;
}
/* 헤드 */
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
   top: 80px;
   height: fit-content;
}

.body{
   margin: 0;
   width: 100%;
   background-attachment: fixed;
   background: url('/images/allback.jpg') no-repeat center;
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
}

/* 상세페이지부터 */
.body-inner {
   border: none;
   background-color: rgb(255, 255, 255, 0.5);
   border-radius: 16px;
   box-shadow: inset 0 0 8px #424242;
   width: 73vw;
   padding: 20px;
   margin-top : 150px;
   margin-bottom: 50px;
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
   border:none;
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

/* 내용 부분만 높이 4배로 설정 */
td#contents {
   height: 200px; /* 4배 높이 */
   word-wrap: break-word;
   white-space: pre-wrap;
}

.bottom {
   text-align: right;
   margin-top: 20px;
}

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

#back {
   margin-right: 300px;
}

.bottom a:hover {
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
   color : black;
}

#input {
   width: 100%;
   height: 60px;
   padding: 10px;
   border: 1px solid #ddd;
   border-radius: 5px;
   resize: none;
}
/*
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
*/
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
/*
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
*/
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
            <!-- ✅ 로그인 정보 -->
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
            <td class="change" id="board_contents" height="500px">${dto.contents}</td>
         </tr>
      </table>
      
      <div class="commentSection">
            <!-- 댓글 목록 -->
            <div id="commentInputContainer">
                 <input id="commentInput" name="contents" placeholder="댓글을 입력하세요">
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
                  <button id="inputbtn" type="button">등록</button>
               </div>
         <div id="comments">
            <ul id="commentList"></ul>
            <!-- AJAX로 댓글이 추가될 부분 -->
         </div>
      </div>
      <form action="/board/update.do" method="post" id="update-form">
         <input id="id" type="hidden" name="id" value="${dto.boardId}">
         <input name="title" type="hidden" id="hdtitle"> 
         <input name="contents" type="hidden" id="hdcontents">
         <div class="bottom">
            <button type="button" id="back">목록으로</button>
            <!-- 여기 게시글 수정 삭제 버튼  -->
            <c:if test="${dto.getMemberId() == member.getMemberId() || member.role == 'admin'}">
               <td class="reply_button_area-${item.id}">
                  <button id="update" type="button">수정하기</button>
                  <button id="delete" type="button">삭제하기</button> 
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
               
            let profileIcon = $("<div>").addClass("profile-icon").text(data[i].writer.charAt(0));
            let contentDiv = $("<div>").addClass("comment-content writerdiv").html(data[i].contents).attr("data-original", data[i].contents);
            let commentHeader = $("<div>").addClass("comment-header").text(data[i].writer + " · " + data[i].regDate);
               
            let btnBox = $("<div>").addClass("btnbox");

            if (data[i].writer === UserName || Master === "admin") {   //관리자이거나 작성자일 경우 보이게하기
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
    }
   	
   	function makeCommentPageNavi(pageNavi, className) {
        $('#comments').append(makePageNavi(pageNavi, className));
        
        $('.replyPageNavi').on('click', function() {
        	const clicked = $(this);	
        	
      	 $.ajax({
      		 url: '/reply/ContentsAll.do?boardId=${dto.boardId}&page=' + clicked.attr('value')
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
                    contents: $commentInput.val(),
              } 
          }).done(function(data) {
              alert('댓글이 등록되었습니다.');
              $.ajax({
                  url: "/reply/ContentsAll.do",
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
        
       validInput($inputBtn);
   }
   
   function validInput($inputBtn) {   //등록 버튼 이벤트
      if ($("#commentInput").val().trim() === "") {
           $inputBtn.prop("disabled", true);
            $inputBtn.css({
               "background-color": "#ffd1dc",
                "cursor": "not-allowed"
            });
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
       $inputBtn.css({
             "background-color": "#ffd1dc",
          "cursor": "not-allowed"
       });
       
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
       url: "/reply/ContentsAll.do",
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
   }); // $(document).ready 끝
   

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
        updateOK.html("수정완료").attr("id", "board-updateOK");

        let updateCancel = $("<button>");
        updateCancel.html("취소").attr("id","updateCancel")            
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
              console.log("test");
               let emotion = $(this).text();
               let currentText = $('#commentInput').val();
               
               $('#commentInput').val(currentText + emotion);   //입력창에 이모티콘 넣기
               $("#commentInput").focus();
               validInput($('#inputbtn'));

               $("#emojiBtn").text("😀");
               isEmoticonPanelOpen = false;
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
          
          function parseDate(timestamp) {
            const date = new Date(timestamp);
            return date.getFullYear() + '년 ' + Number(date.getMonth() + 1) + '월 ' + date.getDate() + '일 ' +  date.getHours() + 
                  '시 ' + date.getMinutes() + '분';       
         }
</script>
</html>