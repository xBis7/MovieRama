package xbis.assignment.dao;

import java.util.List;

import xbis.assignment.models.User;


public interface UserDAO {
	
	public List<User> getAllUsers();
	
	public User getUser(long id);
	
	public User addUser(User user);
	
	public boolean checkUser(String username, String password);
	
	public void setActiveUserList(List<User> activeUserList);
	
	public List<User> getActiveUserList();
	
	public void userLogin(User user);
	
	public void userLogout();
	
}
