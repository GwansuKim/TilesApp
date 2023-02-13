<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
  <tbody>
    <c:forEach var="noticeList" items="${noticeList }">
      <tr>
        <td>
          <a href="noticeDetail.do?nid=${noticeList.noticeId }"
            >${noticeList.noticeId }</a
          >
        </td>
        <td>${noticeList.noticeWriter }</td>
        <td>${noticeList.noticeTitle }</td>
        <td>${noticeList.hitCount }</td>
        <td>
          <fmt:formatDate
            pattern="yyyy-MM-dd"
            value="${noticeList.noticeDate }"
          />
        </td>
      </tr>
    </c:forEach>
  </tbody>
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
    $("#example").DataTable();
  });
  $(document).ready(function () {
    var t = $("#example").DataTable();

    $("#addBtn").on("click", function () {
      var formData = new FormData();
      formData.append("writer", $("#writer").val());
      formData.append("title", $("#title").val());
      formData.append("subject", $("#subject").val());
      $.ajax({
        url: "noticeAddJson.do",
        method: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (result) {
          t.row
            .add([
              result.noticeId,
              result.noticeWriter,
              result.noticeTitle,
              result.hitCount,
              result.noticeDate,
            ])
            .draw(false);
        },
        error: function (reject) {
          console.log(reject);
        },
      });
    });
  });
  $(document).ready(function () {
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
      table.row(".selected").remove().draw(false);
    });
  });

  $(document).ready(function () {
    $("#example").DataTable({
      ajax: "data/arrays.txt",
    });
  });
</script>
