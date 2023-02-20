<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<style>
  .center {
    text-align: center;
  }
</style>
<h3>현재 페이지는 noticeList.do의 결과 noticeList.jsp입니다</h3>

<table class="table">
  <thead>
    <tr>
      <th>글번호</th>
      <th>작성자</th>
      <th>제목</th>
      <th>조회수</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach var="notice" items="${result }">
     <tr>
		<td><a href="noticeDetail.do?nid=${notice.noticeId }">${notice.noticeId }</a></td>
		<td>${notice.noticeWriter }</td>
		<td>${notice.noticeTitle }</td>
		<td>${notice.hitCount }</td>
	</tr>
   </c:forEach>
  </tbody>
</table>
<div class="pagination">
  <a href="#">&laquo;</a>
  <c:forEach var="item" begin="${pagingInfo.startPage}" end="${pagingInfo.endPage }"> 
  	<c:if test="${pagingInfo.curPage == item }">
  		<a href="temp.do?pageNo=${item }" class="active">${item }</a>
  	</c:if>
  	<c:if test="${pagingInfo.curPage != item }">
  		<a href="temp.do?pageNo=${item }">${item }</a>
  	</c:if>
  </c:forEach>
  <a href="#">&raquo;</a>
</div>
