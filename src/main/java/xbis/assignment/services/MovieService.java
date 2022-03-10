package xbis.assignment.services;

import xbis.assignment.models.Movie;

import java.util.List;

public interface MovieService {
	
	public List<Movie> getAllMovies();
	
	public List<Movie> getUserMovies(long id);
	
	public Movie getMovie(long movieID);
	
	public void addMovie(Movie movie);
	
	public void updateMovieLikes(Movie movie, long likes);
	
	public void updateMovieHates(Movie movie, long hates);
	
	public List<Movie> sortByLikes();
	
	public List<Movie> sortByHates();
	
	public List<Movie> sortByDate();
	
}
