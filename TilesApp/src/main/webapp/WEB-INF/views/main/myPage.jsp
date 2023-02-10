<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<h3>현재 페이지는 myPageForm.do의 결과 mypage.jsp입니다</h3>
<c:if test="${result != null }">
	<c:out value="${result }"></c:out>
</c:if>
<form action="modMember.do" method="post">
	<input type="file" id ="fileUpload" accept="images/*" style="display: none" onchange="imageChangeFnc()">
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
			<th><img id="imgSrc" width="150px" src="images/${img }"></th>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="수정"></td>
		</tr>
	</table>
</form>
<script>
	const id = '${vo.memberId }';
	
	$('#imgSrc').on('click', function() {
		$('#fileUpload').click();
	})
	
	function imageChangeFnc() {
		let file = $('#fileUpload')[0].files[0];
		
		let formData = new FormData(); // multipart요청 처리해주는 객체
		formData.append('id', id);
		formData.append('image', file);
		
		$.ajax({
			url: 'imageUpload.do',
			method: 'post',
			data: formData,
			contentType: false,		// multipart요청일 경우에 옵션
			processData: false,		// multipart요청일 경우에 옵션
			success: function(result) {
				let reader = new FileReader();
				reader.onload = function(evt){
					$('#imgSrc').attr('src', evt.target.result);
				}
				reader.readAsDataURL(file);
			},
			error: function(reject) {
				console.log(reject);
			}
		})
	}
</script>