<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>mypage</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
    <script>
        $(function() {
            $("#editBtn").on("click", function() {
                $("input.editable").removeAttr("readonly");
                $("#findPostcode").show();
                $("#backBtn, #editBtn").hide();
                let submitBtn = $("<button>").attr("type", "submit")
                    .text("수정완료").css("margin-right", "5px");
                let btnCancel = $("<button>").attr("type", "button").text("취소");
                btnCancel.on("click", function() {
                    location.reload();
                });
                $("#btn1").append(submitBtn);
                $("#btn2").append(btnCancel);
            })
        });

        function openPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    $("#postcode").val(data.zonecode); 
                    $("#address").val(data.roadAddress); 
                    $("#detail_address").focus(); 
                }
            }).open();
        }

    </script>
</head>
<body>
    ${member.name}님 환영합니다!
    <form action="/member/update.do" method="post">
        <table border="1" align="center">
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>닉네임</th>
                <th>주민번호</th>
                <th>이메일</th>
                <th>전화번호</th>
                <th>우편번호</th>
                <th>주소</th>
                <th>상세주소</th>
                <th>권한</th>
                <th>생성일</th>
            </tr>
            <tr>
                <td>${member.id}<input type="hidden" name="id" value="${member.id}"></td>
                <td>${member.name}</td>
                <td>${member.nickname}<input type = "text" name = "nickname" value = "${member.nickname}" readonly class = "editable"></td>
                <td>${member.ssn}</td>
                <td>${member.email}<input type="email" name="email" value="${member.email}" readonly class="editable"></td>
                <td>${member.phone}<input type="text" name="phone" value="${member.phone}" readonly class="editable"></td>
                <td>${member.postcode}<input type="text" name="postcode" id = "postcode" value="${member.postcode}" readonly class="editable">
                   <button type="button" id="findPostcode" style="display: none;" onclick="openPostcode()">찾기</button>
                <td>${member.address}<input type="text" name="address" id = "address" value="${member.address}" readonly class="editable"></td>
                <td>${member.detail_address}<input type="text" name="detail_address" id = "detail_address" value="${member.detail_address}" readonly class="editable"></td>
                <td>${member.authority}</td>
                <td>${member.date}</td>
            </tr>
            <tr>
                <td colspan="5" align="center" id="btn1">
                    <button type="button" id="editBtn">회원정보 수정</button>
                </td>
                <td colspan="5" align="center" id="btn2">
                    <button type="button" id="backBtn">뒤로가기</button>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>