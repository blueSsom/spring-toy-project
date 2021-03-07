<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Main</title>
	</head>
	
	<body>
		<h1>
			This is main page
		</h1>
		
		<c:if test="${empty member}">
			<a href="${cp}/member/loginForm">Login</a>
			<a href="${cp}/member/joinForm">Join</a>
		</c:if>
		
		<c:if test="${!empty member}">
			<a href="${cp}/member/logout">Logout</a>
			<a href="${cp}/member/modifyForm">Modify</a>
		</c:if>
	</body>
</html>
