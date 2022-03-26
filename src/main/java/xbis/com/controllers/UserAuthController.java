package xbis.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import xbis.com.models.User;
import xbis.com.models.Vote;
import xbis.com.models.Movie;

import xbis.com.services.UserService;
import xbis.com.services.MovieService;
import xbis.com.services.VoteService;

@Controller
public class UserAuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MovieService movieService;
	
	@Autowired 
	VoteService voteService;
	
	@RequestMapping(value = "/loggedIn", method = RequestMethod.GET)
	public String loggedIn(Model model) {
		List<Movie> listOfMovies = movieService.getAllMovies();
		List<User> loggedUserList = userService.getActiveUserList();
		List<Vote> listOfVotes = voteService.getAllVotes();
		String username = loggedUserList.get(loggedUserList.size()-1).getUsername();
		long id = loggedUserList.get(loggedUserList.size()-1).getUserId();
		model.addAttribute("movie", new Movie());
		model.addAttribute("vote", new Vote());
		model.addAttribute("username", username);
		model.addAttribute("currentUserId", id);
		model.addAttribute("listOfMovies", listOfMovies);
		model.addAttribute("listOfVotes", listOfVotes);
		return "loggedIn";
	}
	
	@RequestMapping(value = "/newMovie", method = RequestMethod.GET)
	public String newMovie() {
		return "newMovie";
	}
	
	@RequestMapping(value = "/addMovie", method = RequestMethod.POST)
	public String addMovie(@ModelAttribute("movie") Movie movie) {
		List <User> list = userService.getActiveUserList();
		movie.setUser(list.get(list.size()-1));
		movieService.addMovie(movie);
		return "redirect:/loggedIn";
	}
	
	@RequestMapping(value="/userMovies/{id}", method = RequestMethod.GET)
	public String userMovies(@PathVariable long id, Model model) {
		List<Movie> userMovieList = movieService.getUserMovies(id);
		List<User> loggedUserList = userService.getActiveUserList();
		User user = userService.getUser(id);
		model.addAttribute("username", user.getUsername());
		model.addAttribute("userMovieList", userMovieList);
		model.addAttribute("loggedUserList", loggedUserList);
		return "userMovies";
	}
	
	
	//sorting after login
	@RequestMapping(value="/sortByLikes", method = RequestMethod.GET)
	public String sortByLikes(Model model) {
		List<Movie> likesSortedMovieList = movieService.sortByLikes();
		List<User> currentUserList = userService.getActiveUserList();
		List<Vote> listOfVotes = voteService.getAllVotes();
		String username = currentUserList.get(currentUserList.size()-1).getUsername();
		long id = currentUserList.get(currentUserList.size()-1).getUserId(); 
		model.addAttribute("currentUser", username);
		model.addAttribute("currentUserId", id);
		model.addAttribute("movie", new Movie());
		model.addAttribute("vote", new Vote());
		model.addAttribute("likesSortedMovieList", likesSortedMovieList);
		model.addAttribute("listOfVotes", listOfVotes);
		return "sortByLikes";
	}
	
	@RequestMapping(value="/sortByHates", method = RequestMethod.GET)
	public String sortByHates(Model model) {
		List<Movie> hatesSortedMovieList = movieService.sortByHates();
		List<User> currentUserList = userService.getActiveUserList();
		List<Vote> listOfVotes = voteService.getAllVotes();
		String username = currentUserList.get(currentUserList.size()-1).getUsername();
		long id = currentUserList.get(currentUserList.size()-1).getUserId(); 
		model.addAttribute("currentUser", username);
		model.addAttribute("currentUserId", id);
		model.addAttribute("movie", new Movie());
		model.addAttribute("vote", new Vote());
		model.addAttribute("hatesSortedMovieList", hatesSortedMovieList);
		model.addAttribute("listOfVotes", listOfVotes);
		return "sortByHates";
	}
	
	@RequestMapping(value="/sortByDate", method = RequestMethod.GET)
	public String sortByDate(Model model) {
		List<Movie> dateSortedMovieList = movieService.sortByDate();
		List<User> currentUserList = userService.getActiveUserList();
		List<Vote> listOfVotes = voteService.getAllVotes();
		String username = currentUserList.get(currentUserList.size()-1).getUsername();
		long id = currentUserList.get(currentUserList.size()-1).getUserId(); 
		model.addAttribute("currentUser", username);
		model.addAttribute("currentUserId", id);
		model.addAttribute("movie", new Movie());
		model.addAttribute("vote", new Vote());
		model.addAttribute("dateSortedMovieList", dateSortedMovieList);
		model.addAttribute("listOfVotes", listOfVotes);
		return "sortByDate";
	}

	@RequestMapping(value="/vote/{movieID}", method = RequestMethod.POST)
	public String vote(@ModelAttribute("vote") Vote vote, @PathVariable long movieID, @RequestParam("voteType") String voteType) {
		if(voteType.contains("likes")) {
			List<User> list = userService.getActiveUserList();
			User user = list.get(list.size()-1);
			//set current user as vote user
			vote.setUser(user);
			Movie movie = movieService.getMovie(movieID);
			//set movie as movie vote
			vote.setMovie(movie);
			//set vote positive as true
			vote.setPositive(true);
			//create new vote entry
			voteService.addVote(vote);
			//getAllLikes for the movie
			long likes = voteService.getAllLikes(vote.getMovie().getMovieId());
			//update likes num in movie table
			movieService.updateMovieLikes(vote.getMovie(), likes);
		}
		
		if(voteType.contains("hates")) {
			List<User> list = userService.getActiveUserList();
			User user = list.get(list.size()-1);
			//set current user as vote user
			vote.setUser(user);
			Movie movie = movieService.getMovie(movieID);
			//set movie as movie vote
			vote.setMovie(movie);
			//set vote positive as false
			vote.setPositive(false);
			//create new vote entry
			voteService.addVote(vote);
			//getAllHates for the movie
			long hates = voteService.getAllHates(vote.getMovie().getMovieId());
			//update hates num in movie table
			movieService.updateMovieHates(vote.getMovie(), hates);
		}
		
		return "redirect:/loggedIn";
	}
	
	@RequestMapping(value="/deleteVote/{voteID}", method = RequestMethod.GET)
	public String deleteVote(@PathVariable("voteID") long voteID) {
		Vote vote = voteService.getVote(voteID);
		long movieId = vote.getMovie().getMovieId();
		
		//delete vote
		voteService.deleteVote(voteID);
		
		//update likes
		long likes = voteService.getAllLikes(movieId);
		movieService.updateMovieLikes(movieService.getMovie(movieId), likes);
		
		//update hates
		long hates = voteService.getAllHates(movieId);
		movieService.updateMovieHates(movieService.getMovie(movieId), hates);
		
		return "redirect:/loggedIn";
	}
	
	@RequestMapping(value="/changeVote/{voteID}/{movieID}", method = RequestMethod.POST)
	public String changeVote(@ModelAttribute("vote") Vote vote , @PathVariable long voteID , @PathVariable long movieID, @RequestParam("voteType") String voteType) {
		if(voteType.contains("likes")) {
			
			//delete vote
			voteService.deleteVote(voteID);
			
			//add new vote 
			//get current user
			List<User> list = userService.getActiveUserList();
			User user = list.get(list.size()-1);
			
			//set current user as vote user
			vote.setUser(user);
			
			//get movie
			Movie movie = movieService.getMovie(movieID);
			
			//set movie as movie vote
			vote.setMovie(movie);
			
			//set vote positive as true
			vote.setPositive(true);
			
			//create new vote entry
			voteService.addVote(vote);
		}
		
		if(voteType.contains("hates")) {
			//delete vote
			voteService.deleteVote(voteID);
			
			//add new vote 
			//get current user
			List<User> list = userService.getActiveUserList();
			User user = list.get(list.size()-1);
			
			//set current user as vote user
			vote.setUser(user);
			
			//get movie
			Movie movie = movieService.getMovie(movieID);
			
			//set movie as movie vote
			vote.setMovie(movie);
			
			//set vote positive as false
			vote.setPositive(false);
			
			//create new vote entry
			voteService.addVote(vote);
		}
		
		//update likes
		long likes = voteService.getAllLikes(movieID);
		movieService.updateMovieLikes(movieService.getMovie(movieID), likes);
		
		//update hates
		long hates = voteService.getAllHates(movieID);
		movieService.updateMovieHates(movieService.getMovie(movieID), hates);
		
		return "redirect:/loggedIn";
	}
	
}
