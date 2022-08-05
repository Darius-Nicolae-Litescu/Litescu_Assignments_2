<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List,darius.model.User,darius.utils.CastUtils"%>
<!DOCTYPE html>
<html>
<link href="./css/EditUserProfile.css" rel="stylesheet" type="text/css">
<head>
<meta charset="ISO-8859-1">
<title>Product cart list</title>
</head>
<body>
	<%@include file="HeaderSections.html" %>
	<%
	User user = (User) request.getAttribute("user");
	%>
	<div class="card">
		<div class="container">
			<form method="post" action="EditUserProfileAction">
			<h4>
				<b>User name:</b>
				<input style="background-color:#f6f6f6" type="text" value="<%=user.getUsername()%>" readonly> 
			</h4>
			<p>
				Security question:
				<input type="text" name="securityQuestion" value="<%=user.getSecurityQuestion()%>"> 
			<p>
				Security question answer:
				<input type="text" name="securityQuestionAnswer" value="<%=user.getSecurityQuestionAnswer()%>"> 
			</p>
			<p>
				User location:
				<input style="background-color:#f6f6f6" type="text" value="<%=user.getUserLocation()%>"> 
			</p>
			<p>
				User role:
				<input style="background-color:#f6f6f6" type="text" value="<%=user.getUserRole()%>"> 
			</p>
				<input type="hidden" name="username" id="username"
					value="<%=user.getUsername()%>" />
				<Button class="button">Change details</Button>
			</form>
		</div>
	</div>

</body>
</html>