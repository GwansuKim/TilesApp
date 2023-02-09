<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="d-flex" id="wrapper">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!-- Sidebar-->
	<div class="border-end bg-white" id="sidebar-wrapper">
		<div class="sidebar-heading border-bottom bg-light">Start
			Bootstrap</div>
		<div class="list-group list-group-flush">
			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="noticeList.do">게시글 목록</a>
			<c:if test="${id != null }">
				<a class="list-group-item list-group-item-action list-group-item-light p-3" href="noticeAddForm.do">게시글 등록</a>
			</c:if>
			<c:if test="${id == null }">
				<a class="list-group-item list-group-item-action list-group-item-light p-3" href="signOnForm.do">회원가입</a>
			</c:if>
			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Overview</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Events</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Profile</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Status</a>
		</div>
	</div>