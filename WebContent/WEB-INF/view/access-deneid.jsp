<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Access Denied</title>
</head>
<body>
	<h1>Access deneid.</h1>
	<hr>
	<p style="color: red">You are not authorized to access to this resource.</p>
	<a href="${pageContext.request.contextPath}/">Back to Home</a>
	<h2>${err.message}</h2>
</body>
</html>