package service;

import java.util.List;

import entily.User;

public interface UserService {
	User	findById(Integer id);
	User findByEmail(String email);
	User findByUsername(String username);
	User login(String username, String passwords);
	User resetPassword(String email);
	List<User> findAll();
	List<User> findAll(int pageNumber,int pageSize);
	User register(String username, String passwords, String email);
	User update(User entity);
	User delete(String username);
	boolean register(User newUser);
	
}
