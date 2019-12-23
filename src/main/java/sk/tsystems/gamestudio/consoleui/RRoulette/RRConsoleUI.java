package sk.tsystems.gamestudio.consoleui.RRoulette;
import java.io.IOException;
import java.util.Scanner;
public class RRConsoleUI {
	static int x=1;
	static String input;
	static int inputInt;
	
	 static Shotgun s1 = new Shotgun(0);
	public static void startGame() {
		System.out.println("Welcome to RUSSIAN ROULETTE");
		System.out.println("Insert any number of bullets into shotgun:");
 readLine2();
	s1.setNumberOfBullets(inputInt);
	
		Shotgun.generateBulletPosition();
//			System.out.println(input);
		System.out.println((Shotgun.getArrayList()[0]-1)+" "+(Shotgun.getArrayList()[1]-1));
	 do {
		 processInput();	 
		resolveInput(input);
		x++;
	} while(shoot());
		System.out.println("You are dead");
}
	

	
	private static void processInput() {
		
		 System.out.println("\nPlease enter <shoot>,<addBullet> or <reload>");

		 
		String answer = readLine1();
	 
 	}
	 
	

	
private static String readLine1() {
	 Scanner ss = new Scanner(System.in);  // Create a Scanner object
	 

	  input =    ss.nextLine();
	return input;

}
private static int readLine2() {
	 Scanner ss = new Scanner(System.in);  // Create a Scanner object
	 

	  inputInt =    ss.nextInt();
	return inputInt;

}
 static boolean shoot() {
  
		if (x == Shotgun.getArrayList()[0]||x==Shotgun.getArrayList()[1] ){
	
		return false;

	
	}  
	 return true;
	
}

public static void resolveInput(String input) {
	if (input.contentEquals("shoot")) {
		shoot();
	
	}
}
}
 