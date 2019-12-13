package sk.tsystems.gamestudio.consoleui.nPuzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI {
	private Field field;
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	String user = System.getProperty("user.name");

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public void newGameStarted(Field field) {
		this.field = field;

		do {
			update();
			
			processInput();

		} while (!field.isSolved())   ;
		System.out.println("GG");
	}

	public void update() {

		for (int i = 0; i < field.getRowCount(); i++) {
			for (int j = 0; j < field.getColumnCount(); j++) {
				Tile till = field.getTile(i, j);

				System.out.printf("%2d ", till.getValue());

			}
			
			
			System.out.println();
		}
		System.out.println("Write number:");

	}

	private void processInput() {
		String answer;
		answer = readLine();
		try{
			
			int tileNumber = Integer.parseInt(answer);
			field.move(tileNumber);	
		} catch (Exception e) {
			System.out.println("Wrong input"+"\n");
		}
		

	}
 

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
}
