package sk.tsystems.gamestudio.controller;

import java.util.Formatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sk.tsystems.gamestudio.consoleui.Minesweeper.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Clue;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Field;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Mine;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Tile;

@Controller
public class MinesweeperController {
	private Field field ;
 private boolean marking;

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

	
 


 

	
	public boolean isSolved() {
		return field.isSolved();
	}
//	public String getMessage() {
//		return "Hello from Java";
//	}
}
