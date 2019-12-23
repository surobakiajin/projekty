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
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.CommentService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {

	private Field field;
	@Autowired
	private ScoreService scoreService;

	@Autowired
	private CommentService commentService;
	@Autowired
	private RatingService ratingService;

	@RequestMapping("/puzzle")
	public String index() {

		field = new Field(4, 4);
		return "puzzle";
	}

	@Autowired
	private MainController mainController;

	@RequestMapping("/puzzle/move")
	public String move(int tile) {
		field.move(tile);
		if (field.isSolved() && mainController.isLogged()) {
			scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), "puzzle", 10));
		}
		return "puzzle";
	}

	public String getHtmlField() {
		Formatter f = new Formatter();
		f.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");

			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");
				Tile tile = field.getTile(row, column);
				if (tile != null)
					f.format("<a href='/puzzle/move?tile=%d'><img src='/img/puzzle/img%d.jpg'/></a>", tile.getValue(),
							tile.getValue());

				f.format("</td>\n");

			}
			f.format("</tr>\n");
		}
		f.format("</table>\n");
		return f.toString();
	}

	public String getMessage() {
		return "Hello from Java";
	}

	public boolean isSolved() {
		return field.isSolved();
	}

	public List<Score> getScores() {
		return scoreService.getTopScores("puzzle");
	}

	@RequestMapping("/puzzle/comment")
	public String comment(String content) {
		if (mainController.isLogged()) {
			commentService.addComment(new Comment(mainController.getLoggedPlayer().getName(), "puzzle", content));
		}
		return "puzzle";
	}
	
	public List<Comment> getComments() {
		return commentService.getTopComments("puzzle");
	}
	
	@RequestMapping("/puzzle/rating")
	public String  rating(int rate) {
		Formatter f = new Formatter();
		try {
			if(mainController.isLogged())
				ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(),"puzzle",rate));
			
		}catch (Exception ex) {
		 
		}return "puzzle";	}
	
	public double getRatings() {
		return ratingService.getAverageRating("puzzle");
	}
	
}
