<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
<link
  rel="stylesheet"
  href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css"
/>
<div>
  작성자:<input type="text" id="writer" value="${id }" readonly /> 제목:<input
    type="text"
    id="title"
  />
  내용:<input type="text" id="subject" />
  <button id="addBtn">저장</button>
  <button id="delBtn">삭제</button>
</div>
<br />
<table id="example" class="display">
  <thead>
    <tr>
      <th>글번호</th>
      <th>작성자</th>
      <th>제목</th>
      <th>조회수</th>
      <th>작성일</th>
    </tr>
  </thead>
  <tfoot>
    <tr>
      <th>글번호</th>
      <th>작성자</th>
      <th>제목</th>
      <th>조회수</th>
      <th>작성일</th>
    </tr>
  </tfoot>
</table>
<script>
  $(document).ready(function () {
    $("#example").DataTable({
      ajax: "noticeListJson.do",
    });
    
    var table = $("#example").DataTable();

    $("#example tbody").on("click", "tr", function () {
      if ($(this).hasClass("selected")) {
        $(this).removeClass("selected");
      } else {
        table.$("tr.selected").removeClass("selected");
        $(this).addClass("selected");
      }
      localStorage.setItem("noticeId", $(this).children().eq(0).text());
    });

    $("#delBtn").on("click", function () {
      console.log("삭제할 글 번호: " + localStorage.getItem("noticeId"));
      let id = table.row(".selected").remove().draw(false);
    });
  });
</script>
