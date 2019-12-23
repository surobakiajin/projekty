package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.consoleui.nPuzzle.Field;
import sk.tsystems.gamestudio.consoleui.nPuzzle.Tile;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.PlayerService;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {
	private Player loggedPlayer;
	private Player registeredPlayer;
	
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
@RequestMapping("/login")
public String index(Player player) {
	Player userInDatabase = playerService.findPersonByName(player.getName());
	if(userInDatabase!=null && (userInDatabase.getPasswd()).equals(player.getPasswd())){
		loggedPlayer = player;
	}
	return "redirect:/";
}

@RequestMapping("/register")
public String register(Player player) {
if(!player.getName().trim().equals("")&& !player.getPasswd().trim().equals("")&& playerService.findPersonByName(player.getName())==null){
	playerService.addPlayer(player);
		registeredPlayer = player;
	}
	return "redirect:/";
}

@RequestMapping("/logout")
public String logout() {
	loggedPlayer = null;
	return "redirect:/";

}

public boolean isLogged() {
	return loggedPlayer!=null;
}

public Player getLoggedPlayer () {
	return loggedPlayer;
}

public Player getRegisteredPlayer() {
	return registeredPlayer;
}
}
