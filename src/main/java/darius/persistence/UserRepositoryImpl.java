package darius.persistence;

import darius.database.ShoppingDatabaseStatement;
import darius.dto.UserInsertDTO;
import darius.dto.UserLoginDTO;
import darius.hashing.SHA256Utils;
import darius.model.User;

import java.security.NoSuchAlgorithmException;

public class UserRepositoryImpl implements UserRepository {
    private final ShoppingDatabaseStatement shoppingDatabaseStatement;

    public UserRepositoryImpl(ShoppingDatabaseStatement shoppingDatabaseStatement) {
        this.shoppingDatabaseStatement = shoppingDatabaseStatement;
    }

    @Override
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS User " +
                "(id BIGINT NOT NULL AUTO_INCREMENT," +
                " username VARCHAR(255) NOT NULL," +
                " password VARCHAR(255) NOT NULL," +
                " security_question VARCHAR(255) NOT NULL," +
                " security_question_answer VARCHAR(255) NOT NULL," +
                " user_location VARCHAR(255) NOT NULL," +
                " role VARCHAR(255) NOT NULL," +
                " PRIMARY KEY (id)," +
                " UNIQUE KEY username (username)" +
                ")";

        shoppingDatabaseStatement.execute(sql, "");
    }

    @Override
    public void insert(UserInsertDTO userInsertDTO) throws NoSuchAlgorithmException {
        String sha256HashedPassword = SHA256Utils.toHexString(SHA256Utils.getSHA(userInsertDTO.getPassword()));
        User userToInsert = new User(userInsertDTO.getUsername(), sha256HashedPassword, userInsertDTO.getSecurityQuestion(), userInsertDTO.getSecurityQuestionAnswer(), userInsertDTO.getUserRole(), userInsertDTO.getUserLocation());
        String sql = "INSERT INTO User(username, password, security_question, security_question_answer, user_location, role) VALUES (?,?,?,?,?,?)";
        shoppingDatabaseStatement.execute(sql,
        		userToInsert.getUsername(),
        		userToInsert.getHashedPassword(),
        		userToInsert.getSecurityQuestion(),
        		userToInsert.getSecurityQuestionAnswer(),
        		userToInsert.getUserLocation(),
        		userToInsert.getUserRole().toString());
    }

    @Override
    public boolean validateUser(UserLoginDTO userLoginDTO) throws NoSuchAlgorithmException {
        String sha256HashedPassword = SHA256Utils.toHexString(SHA256Utils.getSHA(userLoginDTO.getPassword()));
        String sql = "SELECT * FROM User WHERE username = ?";
        User user = shoppingDatabaseStatement.executeGetUserByUsername(sql, userLoginDTO.getUsername());
        return user.getHashedPassword().equals(sha256HashedPassword);

    }
    
    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM User WHERE username = ?";
        User user = shoppingDatabaseStatement.executeGetUserByUsername(sql, username);
        return user;
    }
    
    @Override
    public void update(User user) {
    	String sql = "UPDATE `User` SET `security_question` = ?, `security_question_answer` = ? WHERE `username` = ?";
        shoppingDatabaseStatement.execute(sql,
        		user.getSecurityQuestion(),
        		user.getSecurityQuestionAnswer(),
        		user.getUsername());
    }

}
