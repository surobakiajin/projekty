package sk.tsystems.gamestudio.consoleui.GuessNuber;
 
import java.util.Random;
import java.util.Scanner;  


public class GuessNumber {

	public static void main(String[] args) {
		int randomNumber;
		Scanner myObj = new Scanner(System.in);
		Random random = new Random();
		randomNumber = random.nextInt(10);
		int newSelNumber;
		do {
		System.out.println("Write Number"); 		
		String selNumber = myObj.nextLine();
		newSelNumber = Integer.parseInt(selNumber);
		
		if (randomNumber > newSelNumber ) {
			System.out.println("zadaj vacsie");
		}
			else 
				System.out.println("zadaj mensie");
		
		} while (randomNumber != newSelNumber);
		 
		
              
	}
	}