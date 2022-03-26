package xbis.com.services;

import java.util.List;

import xbis.com.models.User;

public interface UserService {
	
	public List<User> getAllUsers();
	
	public User getUser(long id);
	
	public void addUser(User user);
	
	public boolean checkUser(String username, String password);

	public List<User> getActiveUserList();

	public void userLogin(User user);
	
	public void userLogout();
	
}
