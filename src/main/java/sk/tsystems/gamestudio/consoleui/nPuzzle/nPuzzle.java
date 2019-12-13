package sk.tsystems.gamestudio.consoleui.nPuzzle;

 

public class nPuzzle {
	ConsoleUI consoleui = new ConsoleUI();
	
	  public nPuzzle () {
	        new ConsoleUI();
	        
	        Field field = new Field(2,2) ;

	      consoleui.newGameStarted(field);
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
               new nPuzzle();



	}

}
