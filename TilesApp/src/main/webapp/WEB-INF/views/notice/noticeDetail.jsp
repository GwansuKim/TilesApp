<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"
prefix="fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<h3>현재 페이지는 noticeDetail.do의 결과 noticeDetail.jsp입니다</h3><input type="file" id ="fileUpload" accept="image/*" style="display: none">
<table class="table">
  <tr>
    <th>글번호</th>
    <td>${vo.noticeId }</td>
    <th>조회수</th>
    <td>${vo.hitCount }</td>
  </tr>
  <tr>
    <th>제목</th>
    <td>${vo.noticeTitle }</td>
    <th>작성자</th>
    <td>${vo.noticeWriter }</td>
  </tr>
  <tr>
    <th>내용</th>
    <td colspan="3">
      <textarea cols="80" rows="10">${vo.noticeSubject }</textarea>
    </td>
  </tr>
  <tr>
    <th>작성일</th>
    <td>
      <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${vo.noticeDate }" />
    </td>
    <th>첨부파일</th>
    <td>
      <a target="_blank" href="upload/${vo.attachFile }">${vo.attachFile }</a>
    </td>
  </tr>
</table>
</form>
<br />
<table class="table">
  <thead>
    <tr align="center">
      <th>댓글번호</th>
      <th>제목</th>
      <th>작성자</th>
      <th>작성일</th>
    </tr>
  </thead>
  <tbody id="list"></tbody>
  <tfoot id="addReplyForm">
    <tr align="center">
      <td>제목: <input type="text" id="title" /></td>
      <td>작성자: <input type="text" id="writer" value='${id }' readonly /></td>
      <td></td>
      <td></td>
    </tr>
    <tr align="center">
      <td colspan="3">
        <textarea cols="80" rows="3" id="subject"></textarea>
      </td>
      <td><button class="btn btn-primary" id="addReply">등록</button></td>
      <td></td>
    </tr>
  </tfoot>
</table>
<script>
  const url = 'replyList.do?nid=' + ${vo.noticeId };
  const nid = ${vo.noticeId };
  const mid = '${id }';
  fetch(url)
  .then(resolve => resolve.json())
  .then(result => {
  	result.forEach(item => {
      makeTr(item)
    })
  })
  .catch(error => {
  	console.log(error);
  })

  function deleteReply(replyId) {
  $.ajax({
  	method: "post",
  	url: "removeReply.do",
  	data: { rid:replyId },
  	success: function(result) {
  		if(result.retCode == "Success") {
  			$("tr[data-id="+replyId+"]").remove();
  		} else {
  			alert("처리 실패");
  		}
  	},
  	error: function(reject) {
  		console.log(reject)
  	}
  })
  }

  $("#addReply").on('click', function() {
    let writer = $("#writer").val();
    let title = $("#title").val();
    let subject = $("#subject").val();

    $.ajax({
      url: 'addReply.do',
      method: 'post',
      data: { title: title, writer: writer, subject: subject, nid: nid},
      success: function(result) {
          makeTr(result);
      },
      error: function(reject) {
      }
    })

    $("#writer").val("")
    $("#title").val("")
    $("#subject").val("")

  })

  function makeTr(item){
  		// tr: 댓글번호, 제목, 작성자, 작성일자
  		// tr: 댓글내용
  		// let tr1 = document.createElement('tr');
  		// let td = document.createElement('td');
  		let tr1 = $('<tr />').attr('data-id', item.replyId).append(		// jQuery
  			$("<td align='center' />").text(item.replyId),
  			$("<td align='center' />").text(item.replyTitle),
  			$("<td align='center' />").text(item.replyWriter),
  			$("<td width='180px' />").text(item.replyDate)
  		)
  		let tr2 = $('<tr />').attr('data-id', item.replyId).append(
  			$("<td colspan='3' align='center' />").text(item.replySubject),
  			$("<td align='right' />").html(function(){
  				if (item.replyWriter == mid) {
  					return "<button class='btn btn-warning' onclick='deleteReply("+item.replyId+")'>삭제</button>"
  				}
  			})
  		)
  		$('#list').prepend(tr1, tr2);}
</script>
