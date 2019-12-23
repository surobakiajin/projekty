 package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

 


@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)

public class GuessController {
 	private Random random = new Random();
	private int answer;
	private int number;
	private int newSelNumber;
	
	
	@RequestMapping("/guess")
	public String index() {
	 generateNumber();
		return "guess";
	}
	
	@RequestMapping("/guess/input")
	public String processInput(String answer) {
		Formatter f = new Formatter();
		try {
			this.answer = Integer.parseInt(answer);
		} catch (Exception ex) {
			f.format("Wrong input");
		}
		return "guess";
	}
	
	
	
	
	public void generateNumber() {
		int randomNumber = random.nextInt(10)+1;
    number = randomNumber;
	}
	
	public String getMessage() {
		Formatter f = new Formatter();

		
			if (answer == number) {
				f.format("You won");}
			else if (answer< number) {
				f.format("The number I think of is higher");
			}
			else if (answer> number) {
				f.format("The number I think of is lower");
			}
		
	return f.toString();
	}

}
	 
	
	






//	int randomNumber;
//	Scanner myObj = new Scanner(System.in);
//	Random random = new Random();
//	randomNumber = random.nextInt(10);
//	int newSelNumber;
//	do {
//	System.out.println("Write Number"); 		
//	String selNumber = myObj.nextLine();
//	newSelNumber = Integer.parseInt(selNumber);
//	
//	if (randomNumber > newSelNumber ) {
//		System.out.println("zadaj vacsie");
//	}
//		else if(randomNumber < newSelNumber ) 
//			System.out.println("zadaj mensie");
//	} while (randomNumber != newSelNumber);
//	 
//	System.out.println("Congrats you won");    
//}
//	
//	

