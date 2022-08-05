<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List,darius.model.Product,darius.dto.AdminOrderViewDTO, darius.utils.CastUtils"%>
<!DOCTYPE html>
<html>
<link href="./css/AdminPage.css" rel="stylesheet" type="text/css">
<head>
<meta charset="ISO-8859-1">
<title>Admin page</title>
</head>
<body>
	<%@include file="HeaderSections.html" %>
	<div class="container">
		<FORM action="AdminPage" method="post">
			<h4 style="display: auto">
				<b>Filter by date: <input type="text"
					name="filterByDate">
					<Button class="button-filter" type="submit">Filter by date</Button>
				</b> <br> <b>Filter by user location: <input type="text"
					name="filterByUserLocation">
					<Button class="button-filter" type="submit">Filter by user location
						</Button>
				</b>
			</h4>
		</FORM>
	</div>
	<%
	CastUtils<AdminOrderViewDTO> castUtils = new CastUtils<AdminOrderViewDTO>();
	Object adminViewItemsObject = request.getAttribute("adminViewItems");
	List<AdminOrderViewDTO> adminViewItems = castUtils.castObjectToList(adminViewItemsObject, AdminOrderViewDTO.class);
	for (AdminOrderViewDTO adminUserItem : adminViewItems) {
	%>
	<div class="card">
		<div class="container">
			<h4>
				<b> User id: <%=adminUserItem.getUserId()%>
				</b>
			</h4>
			<p>
				Username:
				<%=adminUserItem.getUsername()%>
			</p>
			<p>
				Product id:
				<%=adminUserItem.getProductId()%>
			</p>
			<p>
				Ordered at:
				<%=adminUserItem.getOrderedAt()%>
			</p>
		</div>
	</div>
	<%
	}
	%>
</body>
</html>