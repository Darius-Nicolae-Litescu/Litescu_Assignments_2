<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List,darius.model.Product,darius.utils.CastUtils"%>
<!DOCTYPE html>
<html>
<link href="./css/DisplayProducts.css" rel="stylesheet" type="text/css">
<head>
<meta charset="ISO-8859-1">
<title>Display products</title>
</head>
<body>
	<%
	String username = (String) request.getAttribute("username");
	%>
	<h4>
		<b class="username-display">Welcome: <%=username%></b> <br />
	</h4>
	<%@include file="HeaderSections.html" %>
	<div class="container">
		<FORM action="DisplayProducts" method="post">
			<h4 style="display: auto">
				<b>Filter by product name: <input type="text"
					name="filterByName">
					<Button class="button-filter" type="submit">Filter by name</Button>
				</b> <br> <b>Filter by product star ratings: <input type="text"
					name="filterByProductStarRatings">
					<Button class="button-filter" type="submit">Filter by star
						ratings</Button>
				</b>
			</h4>
		</FORM>
	</div>

	<%
	CastUtils<Product> castUtils = new CastUtils<Product>();
	Object products = request.getAttribute("products");
	List<Product> productList = castUtils.castObjectToList(products, Product.class);
	for (Product product : productList) {
	%>
	<div class="card">
		<img class="card-image" src="<%=product.getPictureHref()%>"
			alt="Avatar">
		<div class="container">
			<h4>
				<b> Product name: <%=product.getName()%>
				</b>
			</h4>
			<p>
				Product code:
				<%=product.getProductCode()%>
			</p>
			<p>
				Product price per unit:
				<%=product.getRonPricePerUnit()%>
				RON
			</p>
			<p>
				Product tax percentage:
				<%=product.getTaxPercentage()%>
			</p>
			<p>
				Product available quantity:
				<%=product.getAvailableQuantity()%>
			</p>
			<p>
				Product star rating:
				<%=product.getStarRating()%>
			</p>
			<form method="post" action="AddProductToCart">
				<input type="hidden" name="productId" id="productId"
					value="<%=product.getId()%>" />
				<Button class="button">Add product to cart</Button>
			</form>
		</div>
	</div>
	<%
	}
	%>
</body>
</html>