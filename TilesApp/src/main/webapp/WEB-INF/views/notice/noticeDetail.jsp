<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<h3>현재 페이지는 noticeDetail.do의 결과 noticeDetail.jsp입니다</h3>
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
		<td colspan="3"><textarea cols="50" rows="10">${vo.noticeSubject }</textarea></td>
	</tr>
	<tr>
		<th>작성일</th>
		<td><fmt:formatDate pattern="yyyy-mm-dd HH:mm:ss" value="${vo.noticeDate }" /></td>
		<th>첨부파일</th>
		<td><a target="_blank" href="upload/${vo.attachFile }">${vo.attachFile }</a></td>
	</tr>
</table>