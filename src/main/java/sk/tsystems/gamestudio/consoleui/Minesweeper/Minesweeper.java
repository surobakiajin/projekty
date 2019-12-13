package sk.tsystems.gamestudio.consoleui.Minesweeper;

import sk.tsystems.gamestudio.consoleui.Minesweeper.consoleui.*;
import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Field;


/**
 * Main application class.
 */
public class Minesweeper {
    /** User interface. */
    private UserInterface userInterface;
 
    /**
     * Constructor.
     */
    public Minesweeper() {
        userInterface = new ConsoleUI();
        
        Field field = new Field(9, 9, 1);
        userInterface.newGameStarted(field);
    }

    /**
     * Main method.
     * @param args arguments
     */
    public static void main(String[] args) {
        new Minesweeper();
    }
}
