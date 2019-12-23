package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.consoleui.RRoulette.Shotgun;
import sk.tsystems.gamestudio.consoleui.nPuzzle.Field;
import sk.tsystems.gamestudio.consoleui.nPuzzle.Tile;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.CommentService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class RRController {
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	boolean alive=true;
	
	static int x=1;
	

	
	@Autowired
	private MainController mainController;

	@RequestMapping("/russianRoulette")
	public String index(){
	System.out.println("Welcome to RUSSIAN ROULETTE");


	Shotgun.generateBulletPosition();
 
	x=1;
	alive=true;
	
	 return "russianRoulette";
	}

	@RequestMapping("/russianRoulette/shoot")
	 public String shoot() {
		Formatter f = new Formatter();	  
		if (x == Shotgun.getArrayList()[0] ){
	alive = false;
		return "russianRoulette";

	
	}  
	f.format("play for"+x+"000$");	
	x++;	
	 return  "russianRoulette";

}
	
	
}