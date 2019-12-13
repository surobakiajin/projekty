package sk.tsystems.gamestudio.consoleui.Minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.tsystems.gamestudio.consoleui.Minesweeper.UserInterface;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Field;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.GameState;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Clue;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Tile;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.ScoreService;
import sk.tsystems.gamestudio.service.ScoreServiceJDBC;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Mine;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	int scoreTime;
	public int getScoreTime(Field field) {
		return scoreTime;
	}

	public void setScoreTime(int scoreTime) {
		this.scoreTime = scoreTime;
	}

	private Field field;
	String user = System.getProperty("user.name");
	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Starts the game.
	 * 
	 * @param field field of mines and clues
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		ScoreService scoreService = new ScoreServiceJDBC();
		long startMillis = System.currentTimeMillis();
		do {
			update();
			processInput();
 
			//throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
		} while (field.getState() == GameState.PLAYING );
	if (field.getState() == GameState.SOLVED)  {
		 update();
		  long seconds = (System.currentTimeMillis() - startMillis) / 1000;
		   scoreTime = (int) seconds;
		    scoreService.addScore(new Score(user,"Minesweeper", scoreTime));
		    System.out.println("\n"+"your time ="+ scoreTime);
		    

	}
	else {
		System.out.println("You are dead you suck");
		
	}
	
	}

	/**
	 * Updates user interface - prints the field.
	 */
	@Override
	public void update() {
		System.out.printf(" ");
		for (int i = 0; i < field.getColumnCount() ; i++) {
			System.out.printf("%3d", i);
		}
		for (int j = 0; j < field.getRowCount(); j++) {
			System.out.printf("%n%c", j + 'A');
			for (int i = 0; i < field.getColumnCount() ; i++) {
				Tile till = field.getTiles(j, i);
				if (till instanceof Mine && till.getState() == Tile.State.OPEN) {
					System.out.printf("%3c", 'X');
				} else if (till instanceof Clue && till.getState() == Tile.State.OPEN) {
					Clue clue = (Clue) till;
					System.out.print("  " + clue.getValue());
				}  else if (till.getState() == Tile.State.MARKED) {
					System.out.printf("%3c" , 'M');
				} else if ( till.getState() == Tile.State.CLOSED) {
					System.out.printf("%3c", '-');
				}
			
				}
		}

	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		
 	String answer;
 	System.out.println();
 	System.out.println("Enter your selection <X>for exit,<MA1> mark tile, <OA1> to open tile");
 	answer = readLine();

 	Pattern pattern = Pattern.compile("(O|M)([A-I])([0-8])");
 	Matcher matcher = pattern.matcher(answer); 
 	boolean matches = matcher.matches();
 	if(matches==false) {
 		System.err.println("ERR");
 		return;
 	}
 	int rowName=matcher.group(2).charAt(0)-'A';
 	int columnName =  matcher.group(3).charAt(0) - '0';  //int columnName =  matcher.group(1).charAt(0) - '0' tu bola chyba group(3) ma byt nie group(1)
 	
 	if (matcher.group(1).equals("O")){
 		field.openTile(rowName,columnName);
 	}
 	if (matcher.group(1).equals("M")){
 		field.markTile(rowName,columnName);
 	}
 	
 	

	
	
	
	
	}
	
}