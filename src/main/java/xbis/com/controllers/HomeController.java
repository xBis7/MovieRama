package xbis.com.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xbis.com.models.User;

import xbis.com.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
        return "login";
	}
	
	@RequestMapping(value= "/userAuth", method = RequestMethod.GET)
	public String authentication(@Valid User user, BindingResult result, Map model) {
		
		if (result.hasErrors()) {
			return "redirect:/login";
		}
		
		boolean userExists = userService.checkUser(user.getUsername(), user.getPassword());
		if(userExists){
			model.put("userAuth", user);
			return "redirect:/loggedIn";
		}else{
			result.rejectValue("username","invaliduser");
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp() {
		return "signUp";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return "redirect:/login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout() {
		userService.userLogout();
		return "redirect:/homePage";
	}
	
}
	
