<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List,darius.model.Product,darius.utils.CastUtils"%>
<!DOCTYPE html>
<html>
<link href="./css/ProductCart.css" rel="stylesheet" type="text/css">
<head>
<meta charset="ISO-8859-1">
<title>Product cart list</title>
</head>
<body>
	<%@include file="HeaderSections.html" %>
	<b style="float: left">User cart items:</b>
	<%
	CastUtils<Product> castUtils = new CastUtils<Product>();
	Object cartProducts = request.getAttribute("userCartItems");
	List<Product> cartproductList = castUtils.castObjectToList(cartProducts, Product.class);
	for (Product product : cartproductList) {
	%>
	<br>
	<div class="card">
		<img class="card-image" src="<%=product.getPictureHref()%>"
			alt="Avatar">
		<div class="container">
			<h4>
				<b>Product name: <%=product.getName()%></b>
			</h4>
			<p>
				Product code:
				<%=product.getProductCode()%></p>
			<p>
				Product price per unit:
				<%=product.getRonPricePerUnit()%>
				RON
			</p>
			<form method="post" action="RemoveProductFromCart">
				<input type="hidden" name="productId" id="productId"
					value="<%=product.getId()%>" />
				<Button class="button">Remove this product from cart</Button>
			</form>
		</div>
	</div>
	<%
	}
	%>
	<div style="display: contents" class="card">
		<form method="post" action="PurchaseProducts">
			<Button class="button">Purchase products</Button>
		</form>
	</div>
</body>
</html>