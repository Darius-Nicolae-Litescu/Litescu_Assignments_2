package darius;

import darius.model.Order;
import darius.model.Product;
import darius.model.User;
import darius.service.OrderService;
import darius.service.ProductService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class InitializeDatabaseData {

	public static void initializeProductData(ProductService productService) {

		productService.createTable();
		System.out.println("Main started");

		List<Product> productList = productService.getAll();
		System.out.println(productList);
		if (productList.size() == 0) {
			Product product1 = new Product("Dell laptop", "DeLL2424",
					"https://cdn.pixabay.com/photo/2017/11/27/21/31/computer-2982270_960_720.jpg",
					BigDecimal.valueOf(2520.52), 10, 40, "electronics", 5);
			Product product2 = new Product("Macbook laptop", "maC5239",
					"https://cdn.pixabay.com/photo/2015/01/08/18/25/desk-593327_960_720.jpg",
					BigDecimal.valueOf(12520.42), 12, 2, "electronics", 5);
			Product product3 = new Product("Black pen", "pen62",
					"https://cdn.pixabay.com/photo/2016/10/04/12/17/white-1714170_960_720.jpg",
					BigDecimal.valueOf(2.20), 2, 2000, "school items", 5);
			Product product4 = new Product("Computer mouse", "mouse852",
					"https://cdn.pixabay.com/photo/2013/07/12/17/41/computer-mouse-152249_960_720.png",
					BigDecimal.valueOf(42.57), 4, 200, "electronics", 4);
			productService.insert(product1);
			productService.insert(product2);
			productService.insert(product3);
			productService.insert(product4);
		}

		List<Product> productListByName = productService.filterByProductName("pen");
		System.out.println(productListByName);

	}

	public static void initializeOrderData(OrderService orderService) {
		User user = new User("Darius");
		Product product1 = new Product("Dell laptop", "DeLL2424",
				"https://cdn.pixabay.com/photo/2017/11/27/21/31/computer-2982270_960_720.jpg",
				BigDecimal.valueOf(2520.52), 10, 40, "electronics", 5);
		product1.setId(1L);
		Product product2 = new Product("Macbook laptop", "maC5239",
				"https://cdn.pixabay.com/photo/2015/01/08/18/25/desk-593327_960_720.jpg", BigDecimal.valueOf(12520.42),
				12, 2, "electronics", 5);
		product2.setId(2L);
		List<Order> orders = orderService.getAll(user.getUsername());
		if (orders == null & orders.size() == 0) {
			List<Product> productList = Arrays.asList(product1, product2);
			Order order = new Order(user, productList);
			orderService.insert(order);
		}
	}
}
