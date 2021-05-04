package com.vlado.dao;

import java.util.List;

import com.vlado.entity.Role;
import com.vlado.entity.User;

public interface UserDAO {
	
	List<User> getUserList();
	
	User getUserByUsername(String username);
	
	User getAuthorWithPosts(String username);

	void toggleUser(String username);
	
	void saveUser(User user);
	
	List<Role> getRoles();
	
	void changeUserDetails(String userneme, User user);
	
	boolean changePassword(String oldPass, String newPass, String repeatPass, String username);
	
	void deleteUser(String username);
}
 