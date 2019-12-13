package sk.tsystems.gamestudio;

import sk.tsystems.gamestudio.consoleui.Menu;
import sk.tsystems.gamestudio.consoleui.Minesweeper.UserInterface;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.service.ScoreServiceJDBC;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreService scoreService = new ScoreServiceJDBC();
		
		

//		scoreService.addScore(new Score("jaro", "npuzzle", 123));
//		scoreService.addScore(new Score("igor", "npuzzle", 72));
//		scoreService.addScore(new Score("slavo", "npuzzle", 67));
//		scoreService.addScore(new Score("jozo", "npuzzle", 42));
//		scoreService.addScore(new Score("mato", "npuzzle", 274));
//		scoreService.addScore(new Score("riso", "npuzzle", 53));
//		scoreService.addScore(new Score("suro", "npuzzle", 75));
//		for (Score score : scoreService.getTopScores("npuzzle"))
//			System.out.println(score.getUsername()+" "+score.getValue());
 	
		Menu userInterface = new Menu();
	    userInterface.rungame();
		
	}
	
	
}

