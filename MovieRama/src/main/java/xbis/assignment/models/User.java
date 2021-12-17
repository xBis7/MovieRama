package xbis.assignment.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	
	@Id
	@Column(name="UserId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;
	
	@Column(name="Username", unique=true)
	private String username;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="HashedPassword")
	private String password;
	
	@Column(name="Salt")
	private String salt;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Movie> movies;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Vote> votes;
	
	public User() {
		super();
	}
	
	public User(long userId, String username, String email, String password, String salt, List<Movie> movies, List<Vote> votes) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.movies = movies;
		this.votes = votes;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public List<Movie> getMovies(){
		return movies;
	}
	
	public List<Vote> getVotes(){
		return votes;
	}
	
}
