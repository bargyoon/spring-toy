<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style type="text/css">
	h1{
		text-align: center;
	}
</style>
</head>
<body>
<h1>PCLASS TOY PROJECT</h1>

<c:if test="${empty authentication}">
	<h2><a href='/member/login'>login</a></h2>
	<h2><a href='/member/join'>회원가입</a></h2>
</c:if>


<c:if test="${not empty authentication}">
	<h2><a href='/member/logout'>logout</a></h2>
	<h2><a href='/member/mypage'>마이페이지</a></h2>
	<h2><a href='/board/board-form'>게시글페이지</a></h2>
</c:if>






</body>
</html>