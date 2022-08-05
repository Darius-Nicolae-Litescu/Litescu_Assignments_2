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
import darius.model.Order;
import darius.model.Product;
import darius.model.User;
import darius.service.OrderService;
import darius.utils.ProductCartUtils;

@WebServlet("/PurchaseProducts")
public class PurchaseProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final OrderService orderService;
	private ProductCartUtils productCartUtils;

    public PurchaseProductsServlet() {
        super();
        this.orderService = InitializeServices.createOrderServiceInstance();
        InitializeDatabaseData.initializeOrderData(orderService);
		this.productCartUtils = new ProductCartUtils();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username"); 
			List<Product> currentCartItems = productCartUtils.getProductListFromCart(session);	
			Order order = new Order(new User(username), currentCartItems);
			orderService.insert(order);
			request.getRequestDispatcher("UserPurchaseHistory").forward(request, response);
		} else {
			request.getRequestDispatcher("Login").forward(request, response);
		}
	}

}
