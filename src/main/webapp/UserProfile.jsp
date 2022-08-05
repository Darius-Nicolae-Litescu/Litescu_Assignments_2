<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List,darius.model.User,darius.utils.CastUtils"%>
<!DOCTYPE html>
<html>
<link href="./css/UserProfile.css" rel="stylesheet" type="text/css">
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
</head>
<body>
	<%@include file="HeaderSections.html" %>
	<%
	User user = (User) request.getAttribute("user");
	%>
	<div class="card">
		<div class="container">
			<h4>
				<b>User name: <%=user.getUsername()%></b>
			</h4>
			<p>
				Security question:
				<%=user.getSecurityQuestion()%></p>
			<p>
				Security question answer:
				<%=user.getSecurityQuestionAnswer()%>
			</p>
			<p>
				User location:
				<%=user.getUserLocation()%>
			</p>
			<p>
				User role:
				<%=user.getUserRole()%>
			</p>
			<form method="post" action="EditUserProfile">
				<input type="hidden" name="username" id="username"
					value="<%=user.getUsername()%>" />
				<Button class="button">Edit profile</Button>
			</form>
		</div>
	</div>
</body>
</html>