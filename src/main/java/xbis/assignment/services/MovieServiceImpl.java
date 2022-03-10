package xbis.assignment.services;

import xbis.assignment.models.Movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xbis.assignment.dao.MovieDAO;

@Service("movieService")
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	MovieDAO movieDAO;
	
	@Override
	@Transactional
	public List<Movie> getAllMovies(){
		return movieDAO.getAllMovies();
	}
	
	@Override
	@Transactional
	public List<Movie> getUserMovies(long id) {
		return movieDAO.getUserMovies(id);
	}
	
	@Override
	@Transactional
	public Movie getMovie(long movieID) {
		return movieDAO.getMovie(movieID);
	}
	
	@Override
	@Transactional
	public void addMovie(Movie movie) {
		movieDAO.addMovie(movie);
	}
	
	@Override
	@Transactional
	public void updateMovieLikes(Movie movie, long likes) {
		movieDAO.updateMovieLikes(movie, likes);
	}
	
	@Override
	@Transactional
	public void updateMovieHates(Movie movie, long hates) {
		movieDAO.updateMovieHates(movie, hates);
	}
	
	@Override
	@Transactional
	public List<Movie> sortByLikes(){
		return movieDAO.sortByLikes();
	}
	
	@Override
	@Transactional
	public List<Movie> sortByHates(){
		return movieDAO.sortByHates();
	}
	
	@Override
	@Transactional
	public List<Movie> sortByDate(){
		return movieDAO.sortByDate();
	}
	
}
