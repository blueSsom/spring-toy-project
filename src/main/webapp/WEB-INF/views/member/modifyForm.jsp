<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Modify page</h1>
	
	<form:form method="post" commandName="member">
		<table>
			<tr>
				<td>ID</td>
				<td>${member.memId}</td>
			</tr>
			<tr>
				<td>PW</td>
				<td><form:password path="memPw" /></td>
			</tr>
			<tr>
				<td>New PW</td>
				<td><input type="text" name="newPw"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Modify" formaction="${cp}/member/modify"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Remove" formaction="${cp}/member/remove"></td>
			</tr>
		</table>
	</form:form>
	
	<a href="${cp}/">MAIN</a>
</body>
</html>