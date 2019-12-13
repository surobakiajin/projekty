package sk.tsystems.gamestudio.controller;

import java.util.Formatter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.consoleui.nPuzzle.Field;
import sk.tsystems.gamestudio.consoleui.nPuzzle.Tile;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {

private Field field; 
	@RequestMapping("/puzzle")
	public String index() {
			field = new Field(4, 4);
		return "puzzle";
	}

	@RequestMapping("/puzzle/move")
	public String move(int tile) {
		field.move(tile);
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
}
