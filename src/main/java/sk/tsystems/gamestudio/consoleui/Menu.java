package sk.tsystems.gamestudio.consoleui;

import java.util.Scanner;

import sk.tsystems.gamestudio.consoleui.GuessNuber.GuessNumber;
import sk.tsystems.gamestudio.consoleui.Minesweeper.Minesweeper;
import sk.tsystems.gamestudio.consoleui.nPuzzle.nPuzzle;

public class Menu {
	public void rungame () {
		do {
			showGames();
			processInput();
		}while(processInput());
			System.out.println("konec");
	}
	
private void showGames() {
	System.out.println("Select game:");
	System.out.println("1. Minesweeper");
	System.out.println("2. Guess number");
	System.out.println("3. nPuzzle");
	System.out.println("4. Exit");
	
}

 

private boolean processInput () {
	Scanner scanner = new Scanner(System.in);
	try {
		int input = Integer.parseInt(scanner.nextLine());
		switch(input) {
		case 1:
			new Minesweeper();
			return true;
		case 2:
			
		//	 return true;
		case 3:
			 new nPuzzle();
			return true;
		case 4:
			return false;
	default:
		System.err.println("Wrong choice number.");
		return true;
		}
			
	
	}catch (NumberFormatException ex) {
		System.err.println("Wrong input");
		return true;
	}
 
}


}
