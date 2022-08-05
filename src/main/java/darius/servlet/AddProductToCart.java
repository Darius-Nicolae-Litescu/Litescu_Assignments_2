package darius.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import darius.InitializeDatabaseData;
import darius.InitializeServices;
import darius.logger.Logger;
import darius.logger.LoggingType;
import darius.model.Product;
import darius.service.ProductService;
import darius.utils.ProductCartUtils;

@WebServlet("/AddProductToCart")
public class AddProductToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductCartUtils productCartUtils;
	private ProductService productService;
	private Logger logger;

	public AddProductToCart() {
		super();
		this.productService = InitializeServices.createProductServiceInstance();
		this.logger = InitializeServices.createConsoleLoggerInstance();
		InitializeDatabaseData.initializeProductData(productService);
		this.productCartUtils = new ProductCartUtils();
		logger.logMessage(this.getClass().toString() + " constructor invoked", LoggingType.INFO);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String productId = (String) request.getParameter("productId");
		logger.logMessage("Product to add" + productId, LoggingType.INFO);
		if (productId != null) {
			if (productId != null) {
				Long productIdLong = Long.parseLong(productId);
				if (session != null) {
					productCartUtils.initializeCartIfEmpty(session);
					List<Product> allProducts = productService.getAll();
					productCartUtils.addProductToCartIfNotEmpty(session, allProducts, productIdLong);
				}
			}
		}
		request.getRequestDispatcher("ProductCart").forward(request, response);
	}

}
