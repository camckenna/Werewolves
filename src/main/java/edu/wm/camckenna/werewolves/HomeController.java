package edu.wm.camckenna.werewolves;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.wm.camckenna.werewolves.domain.Kill;
import edu.wm.camckenna.werewolves.domain.Player;
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
	

	
}
