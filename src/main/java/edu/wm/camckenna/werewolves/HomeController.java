package edu.wm.camckenna.werewolves;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.wm.camckenna.werewolves.domain.GPSLocation;
import edu.wm.camckenna.werewolves.domain.JsonResponse;
import edu.wm.camckenna.werewolves.domain.Kill;
import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.domain.Vote;
import edu.wm.camckenna.werewolves.service.GameService;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired GameService gameService;
	// Autowired finds a satisfied bean of being a playerDAO and connects the controller to it
	// Beans are defined in root-context (these are the Singleton classes)
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/players/alive", method=RequestMethod.GET)
	public @ResponseBody List<Player> getAllAlive()
	{
		// add responseBody to package as a JSON object
		List<Player> players = gameService.getAllAlive();
		return players;
	}
	/**
	 * TODO: get Nearby
	 */
	@RequestMapping(value = "/nearby", method=RequestMethod.GET)
	public @ResponseBody List<Player> getAllNearby(Principal principal)
	{
		// add responseBody to package as a JSON object
		List<Player> players = gameService.getAllNearby(principal.getName());
		return players;
	}
	@RequestMapping(value = "/votable", method=RequestMethod.GET)
	public @ResponseBody List<Player> getVotablePlayers()
	{
		//If alive, can vote
		List<Player> players = gameService.getAllAlive();
		return players;
	}
	/*
	 * TODO
	 * This needs to be POST when I finally understand how that works
	 */
	@RequestMapping(value = "/start", method= {RequestMethod.GET,  RequestMethod.POST})
	public @ResponseBody String startGame()
	{
		gameService.startGame();
		//Some code to redirect to page you came from
		return "Starting game...";
		
	}
	/*
	 * TODO
	 * This needs to be POST when I finally understand how that works
	 */
	@RequestMapping(value = "/vote", method=RequestMethod.GET)
	public @ResponseBody String vote(@PathVariable String name, Principal principal)
	{
		logger.info("Not what I wanted to call");
		return (principal.getName() + " voted for " + name);
	}
	/*
	 * TODO
	 * This needs to be POST when I finally understand how that works
	 */
	@RequestMapping(value = "/vote/{name}", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String voteForPlayer(@PathVariable String name, Principal principal)
	{
		gameService.voteForPlayer(principal.getName(), name);
		return (principal.getName() + " voted for " + name);
	}
	/*
	 * TODO
	 * This needs to be POST when I finally understand how that works
	 * Does not need to be called
	 */
	@RequestMapping(value = "/kill", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String kill(Player victim, Principal principal)
	{
		return (principal.getName() + " attempted to kill " + victim);
	}
	/*
	 * TODO
	 * This needs to be POST when I finally understand how that works
	 */
	@RequestMapping(value = "/kill/{name}",  method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String killPlayer(@PathVariable String name, Principal principal)
	{

		gameService.kill(principal.getName(), name);
		return (principal.getName() + " attempted to kill " + name);
	}
	/*
	 * TODO
	 * This needs to be POST when I finally understand how that works
	 */
	@RequestMapping(value= "/location", method=RequestMethod.GET)
	public @ResponseBody JsonResponse setLocation(@ModelAttribute GPSLocation location, Principal principle){
		
		JsonResponse response = new JsonResponse();
		logger.info("Setting for " + principle.getName() + "'s location to: " + location);
		gameService.updatePosition(principle.getName(), location);
		return response;			
	}

	/*Heart beat of game...
	 * Check to switch over 
	 */
	@RequestMapping(value= "/home", method=RequestMethod.GET)
	public @ResponseBody String checkGameOperation(){		
		
		//logger.info("Check Game Operation");
		gameService.checkGameOperation();
		return "Checking operation...";
			
	}
	/*
	 * This one is done,
	 */
	@RequestMapping(value= "/scores", method=RequestMethod.GET)
	public @ResponseBody List<String> getScores(Principal principal){				
		List<String> scores = gameService.getScores();
		logger.info("THIS USER WAS: " + principal.getName());
		return scores;
			
	}	
}
