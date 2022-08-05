<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List,darius.model.Product,darius.model.User, darius.model.Order,darius.utils.CastUtils"%>
<!DOCTYPE html>
<html>
<link href="./css/UserPurchaseHistory.css" rel="stylesheet"
	type="text/css">
<head>
<meta charset="ISO-8859-1">
<title>User purchase history</title>
</head>
<body>
	<%@include file="HeaderSections.html" %>
	<h4>
		<b>Order history</b>
	</h4>
	<form method="post" action="RemoveAllOrders">
		<Button class="button">Remove orders</Button>
	</form>
	<%
	CastUtils<Order> castUtils = new CastUtils<Order>();
	Object orders = request.getAttribute("orders");
	List<Order> orderList = castUtils.castObjectToList(orders, Order.class);
	for (Order order : orderList) {
	%>
	<div class="card">
		<div class="container">
			<h4>
				<b>Ordered by: <%=order.getOrderedBy().getUsername()%></b>
			</h4>
			<p>
				Ordered at:
				<%=order.getOrderedAt()%></p>
			<%
			for (Product product : order.getProductList()) {
			%>
			<br>
			<div class="card">

				<div class="container">
					<h4>
						<b>Product id: <%=product.getId()%></b>
					</h4>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<%
	}
	%>

</body>
</html>