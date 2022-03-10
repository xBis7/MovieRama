package xbis.assignment.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Movie")
public class Movie {
	
	@Id
	@Column(name="MovieId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long movieId;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Date")
	private String date;
	
	@Column(name="Likes")
	private long likes;
	
	@Column(name="Hates")
	private long hates;
	
	@ManyToOne
	@JoinColumn(name="UserId", nullable = false)
	private User user;
	
	public Movie() {
		super();
	}
	
	public Movie(long movieId, String title, String description, String date, User user, long likes, long hates) {
		this.movieId = movieId;
		this.description = description;
		this.date = date;
		this.user = user;
		this.likes = likes;
		this.hates = hates;
	}
	
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	
	public long getMovieId() {
		return movieId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setLikes(long likes) {
		this.likes = likes;
	}
	
	public long getLikes() {
		return likes;
	}
	
	public void setHates(long hates) {
		this.hates = hates;
	}
	
	public long getHates() {
		return hates;
	}
	
}
