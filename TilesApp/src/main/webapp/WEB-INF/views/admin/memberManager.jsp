<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript" src="js/manager.js"></script>
<h3>회원 관리 페이지</h3>
<table class="table">
	<tr align="center">
		<th align="right">아이디</th><td><input type="text" id="mid"></td>
	</tr>
	<tr align="center">
		<th align="right">이름</th><td><input type="text" id="mname"></td>
	</tr>
	<tr align="center">
		<th align="right">연락처</th><td><input type="text" id="mphone"></td>
	</tr>
	<tr align="center">
		<th align="right">주소</th><td><input type="text" id="maddr"></td>
	</tr>
	<tr align="center">
		<th align="right">이미지</th><td><input type="file" id="mimage"></td>
	</tr>
	<tr align="center">
		<td colspan="2"><input type="button" id="addBtn" value="등록"></td>
	</tr>
</table>
<table class="table">
	<thead>
		<tr>
			<th>회원 ID</th>
			<th>회원 이름</th>
			<th>연락처</th>
			<th>주소</th>
			<th>권한</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody id="list">
	</tbody>
</table>