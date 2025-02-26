<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의내역 상세보기</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link 
  href="https://fonts.googleapis.com/css2?family=Jua&family=Press+Start+2P&display=swap"
  rel="stylesheet">

<style>
  body {
    background: #f4f7f8;
    color: #333;
    margin: 0;
    padding: 0;
  }

  .container {
    width: 80%;
    max-width: 1000px;
    background: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
    margin: 50px auto;
    padding: 20px;
  }

  .title {
    font-size: 28px;
    margin-bottom: 20px;
    color: #1e201d;
    text-align: center;
  }

  fieldset {
    border: 3px solid #b4c28a;
    border-radius: 10px;
    margin-bottom: 30px;
    padding: 20px;
    background: #fafafa;
  }
  legend {
    font-size: 20px;
    font-weight: bold;
    color: #b4c28a;
    padding: 0 10px;
  }

  .info-row {
    margin: 10px 0;
    display: flex;
    flex-wrap: wrap;
  }
  .label {
    width: 120px;
    font-weight: bold;
    font-size:19px;
    color: #444;
  }
  .value {
    flex: 1;
    color: #333;
  }
  .value p { margin: 0; }

</style>
</head>

<body>
  <div class="container">
    <div class="title">문의내역 상세보기</div>

    <fieldset>
      <legend>문의 내역</legend>

      <div class="info-row">
        <div class="label">문의번호</div>
        <div class="value">${qnaDto.qnaId}</div>
      </div>

      <div class="info-row">
        <div class="label">작성자ID</div>
        <div class="value">${qnaDto.memberId}</div>
      </div>

      <div class="info-row">
        <div class="label">작성일</div>
        <div class="value">
          <fmt:formatDate value="${qnaDto.regDate}" pattern="yyyy-MM-dd HH:mm"/>
        </div>
      </div>
      <br>

      <div class="info-row">
        <div class="label">내용</div>
        <div class="value">${qnaDto.contents}</div>
      </div>
      
    </fieldset>

    <c:if test="${not empty qnaReplyDto}">
      <fieldset>
        <legend>답변 내역</legend>
        <div class="info-row">
          <div class="label">답변일</div>
          <div class="value">
            <fmt:formatDate value="${qnaReplyDto.regDate}" pattern="yyyy-MM-dd HH:mm"/>
          </div>
        </div>
         <div class="info-row">
            <div class="label">답변내용</div>
            <div class="value">${qnaReplyDto.context}</div>
         </div>
      </fieldset>
    </c:if>
  </div>
</body>
</html>
