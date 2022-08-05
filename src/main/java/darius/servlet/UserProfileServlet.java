package darius.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import darius.InitializeServices;
import darius.model.User;
import darius.service.UserService;

@WebServlet("/UserProfile")
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final UserService userService;

    public UserProfileServlet() {
        super();
        this.userService = InitializeServices.createUserServiceInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			User user = userService.findByUsername(username);
			request.setAttribute("user", user);
			request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("Login").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
