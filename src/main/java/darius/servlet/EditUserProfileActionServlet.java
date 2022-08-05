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


@WebServlet("/EditUserProfileAction")
public class EditUserProfileActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserService userService;
	
    public EditUserProfileActionServlet() {
        super();
        this.userService = InitializeServices.createUserServiceInstance();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			User user = userService.findByUsername(username);
			String securityQuestion = request.getParameter("securityQuestion");
			String securityQuestionAnswer = request.getParameter("securityQuestionAnswer");
			user.setSecurityQuestion(securityQuestion);
			user.setSecurityQuestionAnswer(securityQuestionAnswer);
			userService.update(user);
			request.setAttribute("user", user);
			request.getRequestDispatcher("EditUserProfile.jsp").forward(request, response);
		
		} else {
			request.getRequestDispatcher("Login").forward(request, response);
		}
	}

}
