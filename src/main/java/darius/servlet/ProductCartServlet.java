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

@WebServlet("/ProductCart")
public class ProductCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductCartUtils productCartUtils;
	private ProductService productService;
	private Logger logger;

	public ProductCartServlet() {
		super();
		this.productService = InitializeServices.createProductServiceInstance();
		this.logger = InitializeServices.createConsoleLoggerInstance();
		InitializeDatabaseData.initializeProductData(productService);
		this.productCartUtils = new ProductCartUtils();
		logger.logMessage(this.getClass().toString() + " constructor invoked", LoggingType.INFO);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			List<Product> userCartItems = productCartUtils.getProductListFromCart(session);
			request.setAttribute("userCartItems", userCartItems);
			request.getRequestDispatcher("ProductCart.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("Login").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
