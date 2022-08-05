package darius.service;

import darius.dto.UserInsertDTO;
import darius.dto.UserLoginDTO;
import darius.model.User;

public interface UserService {
    void createTable();

    void insert(UserInsertDTO userInsertDTO);

    boolean validateUser(UserLoginDTO userLoginDTO);
    
	User findByUsername(String username);

	void update(User user);
}
