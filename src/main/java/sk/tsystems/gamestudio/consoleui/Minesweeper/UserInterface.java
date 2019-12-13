package sk.tsystems.gamestudio.consoleui.Minesweeper;

import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Field;;

public interface UserInterface {

	/**
	 * Starts the game.
	 * @param field field of mines and clues
	 */
	void newGameStarted(Field field);

	/**
	 * Updates user interface - prints the field.
	 */
	void update();

}