package xbis.com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xbis.com.models.User;


@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	//the last user on the list is the one currently logged in
	private List<User> activeUserList = new ArrayList<User>();
	
	public void setSessionFactory(SessionFactory sessionFac) {
		this.sessionFactory = sessionFac;
	}
	
	public Session getSession() {
		return sessionFactory.openSession();
	}
	
	@Override
	public List<User> getAllUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("from User").list();
		return userList;
	}
	
	@Override
	public User getUser(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		return user;
	}
	
	@Override
	public User addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		user.setSalt(PasswordUtil.generateSalt().toString());
		String hashedPassword = PasswordUtil.hashThePassword(user.getPassword(), user.getSalt()).toString();
		user.setPassword(hashedPassword);
		session.persist(user);
		return user;
	}
	
	@Override
	public boolean checkUser(String username, String password) {
		System.out.println("Authenticating user...");
		Session session = sessionFactory.openSession();
		boolean userAuthenticated = false;
		
		String mySQLquery = "from User as u where u.username=?0";
		Query query = session.createQuery(mySQLquery);
		query.setParameter(0, username);
		List list = query.getResultList();
		if((list != null) && (list.size()) > 0) {
			setActiveUserList(list);
			
			//getting logged in user's password hash
			String hashedPass = activeUserList.get(activeUserList.size()-1).getPassword();
			
			//getting salt
			String salt = activeUserList.get(activeUserList.size()-1).getSalt();
			
			//use hash and salt to verify password entered
			if(PasswordUtil.verifyThePassword(password, hashedPass, salt) == true) {
				userAuthenticated = true;
			}
		}
		System.out.println("User authenticated: " + userAuthenticated);
		session.close();
		return userAuthenticated;
	}
	
	@Override
	public void setActiveUserList(List<User> activeUserList) {
		this.activeUserList = activeUserList;
	}
	
	@Override
	public List<User> getActiveUserList() {
		return activeUserList;
	}
	
	@Override
	public void userLogin(User user) {
		activeUserList.add(user);
	}
	
	@Override
	public void userLogout() {
		activeUserList.clear();
	}
	
}
