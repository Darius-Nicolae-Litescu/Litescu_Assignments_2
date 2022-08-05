package darius.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import darius.model.Product;

public class ProductCartUtils {

	private final CastUtils<Product> castUtils;

	public ProductCartUtils() {
		this.castUtils = new CastUtils<Product>();
	}

	public void initializeCartIfEmpty(HttpSession session) {
		Object cartItems = session.getAttribute("cartItems");
		List<Product> currentCartItems = castUtils.castObjectToList(cartItems, Product.class);
		if (currentCartItems == null) {
			session.setAttribute("cart", new ArrayList<Product>());
		}
	}

	public void addProductToCartIfNotEmpty(HttpSession session, List<Product> products, Long productId) {
		Object cartItems = session.getAttribute("cartItems");
		List<Product> currentCartItems = castUtils.castObjectToList(cartItems, Product.class);
		Product productToAdd = products.stream().filter(product -> product.getId().equals(productId))
		  .findAny()
		  .orElse(null);
		currentCartItems.add(productToAdd);
		Set<Product> set = new HashSet<>(currentCartItems);
		currentCartItems.clear();
		currentCartItems.addAll(set);
		session.setAttribute("cartItems", currentCartItems);
	}

	public void removeProductToCartIfNotEmpty(HttpSession session, Long productId) {
		Object cartItems = session.getAttribute("cartItems");
		List<Product> currentCartItems = castUtils.castObjectToList(cartItems, Product.class);
		currentCartItems.removeIf(item -> item.getId().equals(productId));
		session.setAttribute("cartItems", currentCartItems);
	}
	
	public List<Product> getProductListFromCart(HttpSession session){
		Object cartItems = session.getAttribute("cartItems");
		List<Product> currentCartItems = castUtils.castObjectToList(cartItems, Product.class);
		return currentCartItems;
	}
	
}
