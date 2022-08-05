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

@WebServlet("/DisplayProducts")
public class DisplayProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService;
	private Logger logger;

	public DisplayProducts() {
		super();
		this.productService = InitializeServices.createProductServiceInstance();
		this.logger = InitializeServices.createConsoleLoggerInstance();
		InitializeDatabaseData.initializeProductData(productService);
		logger.logMessage(this.getClass().toString() + " constructor invoked", LoggingType.INFO);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			request.setAttribute("username", username);
			String filterByName = (String) request.getParameter("filterByName");
			String filterByProductStarRatings = (String) request.getParameter("filterByProductStarRatings");
			if (filterByProductStarRatings != null && filterByProductStarRatings.length() > 0) {
				Integer filterByProductStarRatingsInteger = Integer.parseInt(filterByProductStarRatings);
				logger.logMessage("Filter term: " + filterByName, LoggingType.INFO);
				if (filterByProductStarRatings != null) {
					List<Product> productList = productService.filterByProductStarRatings(filterByProductStarRatingsInteger);
					logger.logMessage("productStarRatings: " + productList, LoggingType.INFO);
					request.setAttribute("products", productList);
					request.getRequestDispatcher("DisplayProducts.jsp").forward(request, response);
					return;
				}
			}
			if (filterByName != null) {
				List<Product> productsByName = productService.filterByProductName(filterByName);
				logger.logMessage("productsByName: " + productsByName, LoggingType.INFO);
				request.setAttribute("products", productsByName);
			} else {
				request.setAttribute("products", productService.getAll());
			}
			request.getRequestDispatcher("DisplayProducts.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("Login").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
