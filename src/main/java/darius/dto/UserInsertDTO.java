package darius.dto;

import java.io.Serializable;

import darius.model.UserRole;

public class UserInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
    private String password;
    private String securityQuestion;
    private String securityQuestionAnswer;
    private UserRole userRole;
    private String userLocation;

    public UserInsertDTO() {
    }
    
    
    public UserInsertDTO(String username, String password, String securityQuestion, String securityQuestionAnswer, UserRole userRole, String userLocation) {
		this.username = username;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.securityQuestionAnswer = securityQuestionAnswer;
		this.userRole = userRole;
		this.userLocation = userLocation;
	}

	public UserInsertDTO(String username, String password, String securityQuestion, String securityQuestionAnswer) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityQuestionAnswer = securityQuestionAnswer;
    }
	
	
    
    public String getUserLocation() {
		return userLocation;
	}


	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}


	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }
    
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }
}
