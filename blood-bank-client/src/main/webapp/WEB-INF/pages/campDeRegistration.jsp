<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SAVE A LIFE</title>
</head>
<body>
<%@ include file="menu.html" %>
<div>
	<form:form action="/deletecamp" modelAttribute="campObj" method="get">
		<div>
			<label for="id">Camp Id</label>
			<form:input path="id"/>
		</div>
		<div>
			<input type = "submit" value = "Delete">
		</div>
	</form:form>
</div>
</body>
</html>