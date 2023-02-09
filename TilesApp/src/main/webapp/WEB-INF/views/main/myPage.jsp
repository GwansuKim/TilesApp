<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h3>현재 페이지는 myPageForm.do의 결과 mypage.jsp입니다</h3>
<c:if test="${result != null }">
	<c:out value="${result }"></c:out>
</c:if>
<form action="modMember.do" method="post">
	<table class="table">
		<tr>
			<th>ID</th>
			<th><input type="text" name="mid" value="${vo.memberId }"
				readonly></th>
		</tr>
		<tr>
			<th>비밀번호</th>
			<th><input type="text" name="mpw" value="${vo.memberPw }"></th>
		</tr>
		<tr>
			<th>이름</th>
			<th><input type="text" name="mname" value="${vo.memberName }"></th>
		</tr>
		<tr>
			<th>연락처</th>
			<th><input type="text" name="mphone" value="${vo.memberPhone }"></th>
		</tr>
		<tr>
			<th>주소</th>
			<th><input type="text" name="maddr" value="${vo.memberAddr }"></th>
		</tr>
		<tr>
			<th>image</th>
			<th><img width="150px" src="images/${vo.image }"></th>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="수정"></td>
		</tr>
	</table>
</form>