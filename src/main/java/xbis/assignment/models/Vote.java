package xbis.assignment.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Vote", uniqueConstraints = 
@UniqueConstraint(columnNames= {"MovieId", "UserId"}))
public class Vote {

	@Id
	@Column(name="VoteId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long voteId;
	
	@Column(name="Positive")
	private boolean positive;
	
	@ManyToOne
	@JoinColumn(name="MovieId", nullable = false)
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name="UserId", nullable = false)
	private User user;
	
	public Vote() {
		super();
	}
	
	public Vote(long voteId, boolean positive, Movie movie, User user) {
		this.voteId = voteId;
		this.positive = positive;
		this.movie = movie;
		this.user = user;
	}
	
	public void setVoteId(long voteId) {
		this.voteId = voteId;
	}
	
	public long getVoteId() {
		return voteId;
	}
	
	public void setPositive(boolean positive) {
		this.positive = positive;
	}
	
	public boolean getPositive() {
		return positive;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
}
