package xbis.assignment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xbis.assignment.dao.UserDAO;
import xbis.assignment.models.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Override
	@Transactional
	public List<User> getAllUsers(){
		return userDAO.getAllUsers();
	}
	
	@Override
	@Transactional
	public User getUser(long id) {
		return userDAO.getUser(id);
	}
	
	@Override
	@Transactional
	public void addUser(User user) {
		userDAO.addUser(user);
	}
	
	@Override
	@Transactional
	public boolean checkUser(String username, String password) {
		return userDAO.checkUser(username, password);
	}
	
	@Override
	@Transactional
	public List<User> getActiveUserList(){
		return userDAO.getActiveUserList();
	}
	
	@Override
	@Transactional
	public void userLogin(User user) {
		userDAO.userLogin(user);
	}
	
	@Override
	@Transactional
	public void userLogout() {
		userDAO.userLogout();
	}
	
}
