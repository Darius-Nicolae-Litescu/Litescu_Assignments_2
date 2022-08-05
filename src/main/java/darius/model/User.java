package darius.model;

import java.util.List;
import java.util.Objects;

public class User {
    private String username;
    private String hashedPassword;
    private String securityQuestion;
    private String securityQuestionAnswer;
    private UserRole userRole;
    private String userLocation;
    private List<Product> purchasedProducts;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String hashedPassword, String securityQuestion, String securityQuestionAnswer) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.securityQuestion = securityQuestion;
        this.securityQuestionAnswer = securityQuestionAnswer;
        this.userRole = UserRole.USER;
    }
    
    
    
    public User(String username, String hashedPassword, String securityQuestion, String securityQuestionAnswer, UserRole userRole, String userLocation) {
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.securityQuestion = securityQuestion;
		this.securityQuestionAnswer = securityQuestionAnswer;
		this.userRole = userRole;
		this.userLocation = userLocation;
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

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", securityQuestion='" + securityQuestion + '\'' +
                ", securityQuestionAnswer='" + securityQuestionAnswer + '\'' +
                ", purchasedProducts=" + purchasedProducts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(hashedPassword, user.hashedPassword) && Objects.equals(securityQuestion, user.securityQuestion) && Objects.equals(securityQuestionAnswer, user.securityQuestionAnswer) && Objects.equals(purchasedProducts, user.purchasedProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, hashedPassword, securityQuestion, securityQuestionAnswer, purchasedProducts);
    }
}
