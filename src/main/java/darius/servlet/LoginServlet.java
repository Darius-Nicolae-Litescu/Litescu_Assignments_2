package darius.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import darius.InitializeServices;
import darius.dto.UserLoginDTO;
import darius.service.UserService;
import darius.utils.ProductCartUtils;
import darius.validator.UserValidator;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private ProductCartUtils productCartUtils;

    public LoginServlet() {
        super();
        this.userService = InitializeServices.createUserServiceInstance();
        this.productCartUtils = new ProductCartUtils();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
		String errorMessage = UserValidator.validateUserLoginDTO(userLoginDTO);
		if(errorMessage!= null) {
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		} else if(userService.validateUser(userLoginDTO)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			productCartUtils.initializeCartIfEmpty(session);
			request.getRequestDispatcher("DisplayProducts").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "User could not be found/ Invalid username or password");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
