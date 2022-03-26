package xbis.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xbis.com.models.Movie;

import xbis.com.services.MovieService;

@Controller
public class UserAnonymController {
	
	@Autowired
	MovieService movieService;
	
	@RequestMapping(value="/homePage", method = RequestMethod.GET)
	public String homePage(Model model) {
		List<Movie> listOfMovies = movieService.getAllMovies();
		model.addAttribute("movie", new Movie());
		model.addAttribute("listOfMovies", listOfMovies);
		return "homePage";
	}
	
	//sorting without logging in
	@RequestMapping(value="/sortByLikesAnonym", method = RequestMethod.GET)
	public String sortByLikesAnonym(Model model) {
		List<Movie> likesSortedMovieList = movieService.sortByLikes();
		model.addAttribute("likesSortedMovieList", likesSortedMovieList);
		return "sortByLikesAnonym";
	}
	
	@RequestMapping(value="/sortByHatesAnonym", method = RequestMethod.GET)
	public String sortByHatesAnonym(Model model) {
		List<Movie> hatesSortedMovieList = movieService.sortByHates();
		model.addAttribute("hatesSortedMovieList", hatesSortedMovieList);
		return "sortByHatesAnonym";
	}
	
	@RequestMapping(value="/sortByDateAnonym", method = RequestMethod.GET)
	public String sortByDateAnonym(Model model) {
		List<Movie> dateSortedMovieList = movieService.sortByDate();
		model.addAttribute("dateSortedMovieList", dateSortedMovieList);
		return "sortByDateAnonym";
	}

}
