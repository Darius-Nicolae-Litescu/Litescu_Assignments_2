package darius.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import darius.InitializeDatabaseData;
import darius.InitializeServices;
import darius.service.OrderService;


@WebServlet("/RemoveAllOrders")
public class RemoveAllOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final OrderService orderService;
       
    public RemoveAllOrdersServlet() {
        super();
        this.orderService = InitializeServices.createOrderServiceInstance();
        InitializeDatabaseData.initializeOrderData(orderService);
    }

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username"); 
			orderService.delete(username);
			request.getRequestDispatcher("DisplayProducts").forward(request, response);
		} else {
			request.getRequestDispatcher("Login").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDelete(request, response);
	}

}
