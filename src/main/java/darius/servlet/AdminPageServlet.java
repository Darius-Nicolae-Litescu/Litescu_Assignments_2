package darius.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import darius.InitializeServices;
import darius.dto.AdminOrderViewDTO;
import darius.service.AdminService;

@WebServlet("/AdminPage")
public class AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminService adminService;
	
    public AdminPageServlet() {
        super();
        this.adminService = InitializeServices.createAdminServiceInstance();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String filterByDate = (String) request.getParameter("filterByDate");
			String filterByUserLocation = (String) request.getParameter("filterByUserLocation");
            if(filterByUserLocation != null && filterByUserLocation.length() > 0) {
            	List<AdminOrderViewDTO> adminViewList = adminService.viewOrdersByUserLocation(filterByUserLocation);
				request.setAttribute("adminViewItems", adminViewList);
				request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
			    return;
            }
			if(filterByDate != null) {
				List<AdminOrderViewDTO> adminViewList = adminService.viewOrdersByExactDate(filterByDate);
				request.setAttribute("adminViewItems", adminViewList);
				request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
				return;
			} else {
				List<AdminOrderViewDTO> adminViewList = adminService.viewOrdersByExactDate("2022-08-05");
				request.setAttribute("adminViewItems", adminViewList);
				request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
				return;
			}
			
		} else {
			request.getRequestDispatcher("Login").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
