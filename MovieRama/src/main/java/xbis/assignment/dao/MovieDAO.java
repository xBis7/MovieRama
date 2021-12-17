package xbis.assignment.dao;

import java.util.List;

import xbis.assignment.models.Movie;

public interface MovieDAO {
	
	public List<Movie> getAllMovies();

	public List<Movie> getUserMovies(long id);
	
	public Movie getMovie(long movieID);
	
	public Movie addMovie(Movie movie);
	
	public void updateMovieLikes(Movie movie, long likes);
	
	public void updateMovieHates(Movie movie, long hates);
	
	public List<Movie> sortByLikes();
	
	public List<Movie> sortByHates();
	
	public List<Movie> sortByDate();
}
