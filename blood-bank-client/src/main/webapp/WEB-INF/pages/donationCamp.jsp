<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width"/>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Blood Bank</title>
</head>
<style type="text/css">
a{
float:centre;
}
div.form
{
    display: block;
    text-align: center;
}
form
{
    display: inline-block;
    margin-left: auto;
    margin-right: auto;
    text-align: left;
}
</style>
<body>
<h1>Donation Camp Registration</h1>
<%@ include file="menu.html" %>

<div class="form">
<a class="btn btn-info" href="campderegistration">Cancel Registration</a><br><br>
	<c:if test="${errorMsg.length()!=0} ">
	
		<div class="alert alert-danger">
			<ul>
				<li >All fields are required</li>
			</ul>
		</div>
	</c:if>
</div>

<div class="form">

	<form:form action="/donationcamp" modelAttribute="campObj" method="post">
	<div>
			<label for="id">ID</label>
			<form:input path="id"/>
			<form:errors path="id" cssStyle="color:#ff0000;"></form:errors>
		</div>
		<div>
			<label for="campName">Name</label>
			<form:input path="campName"/>
			<form:errors path="campName" cssStyle="color:#ff0000;"></form:errors>
		</div>
		<div>
			<label for="location">location</label>
			<form:input path="location"/>
		</div>
		<div>
			<label for="dateOfHeld">Event Date</label>
			<form:input type="date" path="dateOfHeld"/>
		</div>
		<div>
			<input type = "submit" value = "Register">
		</div>
	</form:form>
</div>
</body>
</html>