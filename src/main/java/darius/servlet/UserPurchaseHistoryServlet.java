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
import darius.service.OrderService;

@WebServlet("/UserPurchaseHistory")
public class UserPurchaseHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final OrderService orderService;

    public UserPurchaseHistoryServlet() {
        super();
        this.orderService = InitializeServices.createOrderServiceInstance();
        InitializeDatabaseData.initializeOrderData(orderService);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
		    List<Order> userOrders = orderService.getAll(username);
		    request.setAttribute("orders", userOrders);
			request.getRequestDispatcher("UserPurchaseHistory.jsp").forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
