package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.consoleui.Minesweeper.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Clue;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Field;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Mine;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Tile;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.CommentService;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesweeperController {
	private Field field ;
 private boolean marking;
 @Autowired
 private ScoreService scoreService;

 @Autowired
	private CommentService commentService;
 
	@Autowired
	private MainController mainController;

	@RequestMapping("/minesweeper")
	public String index() {
		field= new Field(5, 5, 5);
			
		return "minesweeper";
	}
	
	
	

	@RequestMapping("/minesweeper/change")
	public String change() {
		marking =!marking;
		return "minesweeper";
	}
	 

	@RequestMapping("/minesweeper/open")
	public String open(int row, int column) {
		if(marking) {
			field.markTile(row, column);
		}
		else{field.openTile(row,column);
		if(field.isSolved()&& mainController.isLogged()) {
			scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), "minesweeper", 10));
		}
		
	}return "minesweeper";
	}
	
	
 


	public String getHtmlField() {
		Formatter f = new Formatter();
		f.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");

			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");
				Tile tile = field.getTiles(row, column);
				if (tile.getState() == Tile.State.CLOSED)
					f.format("<a href='/minesweeper/open?row=%d&column=%d'><img src='/img/minesweeper/facingDown.png' class='image'/></a>",row, column);
				else if (tile.getState() == Tile.State.MARKED)
					f.format("<a href='/minesweeper/open?row=%d&column=%d'><img src='/img/minesweeper/flagged.png' class='image'/></a>",row, column);
			else if (tile.getState() == Tile.State.OPEN) {
				if(tile instanceof Mine) {
					f.format("<a href='/minesweeper/open?row=%d&column=%d'><img src='/img/minesweeper/bomb.png'  /></a>",row, column);

				}
				else if (tile instanceof Clue) {
					int value=  ((Clue) tile).getValue() ;
					f.format("<a href='/minesweeper/open?row=%d&column=%d'><img src='/img/minesweeper/%d.png' class='image'/></a>",row, column,value);
				}
			
			}

				f.format("</td>\n");
			}
			f.format("</tr>\n");
		}
		f.format("</table>\n");
		return f.toString();
	}

	
	public List<Score> getScores(){
		return scoreService.getTopScores("minesweeper");
	}


 

	
	public boolean isSolved() {
		return field.isSolved();
	}
//	public String getMessage() {
//		return "Hello from Java";
//	}
	
	@RequestMapping("/minesweeper/comment")
	public String comment(String content) {
		if (mainController.isLogged()) {
			commentService.addComment(new Comment(mainController.getLoggedPlayer().getName(), "minesweeper", content));
		}
		return "minesweeper";
	}
	
	public List<Comment> getComments() {
		return commentService.getTopComments("minesweeper");
	}
}
