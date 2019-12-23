package sk.tsystems.gamestudio.consoleui.RRoulette;
import java.util.Random;

public class Shotgun {
	private static int[] arrayList = new int[6];

	static int numberOfBullets;
	public static int bulletPosition;

	public static void setNumberOfBullets(int numberOfBullets) {
		Shotgun.numberOfBullets = numberOfBullets;
	}

	public Shotgun(int numberOfBullets) {
		this.numberOfBullets = numberOfBullets;

	}

	public int getNumberOfBullets() {
		return numberOfBullets;
	}

	public static int getBulletPosition() {
		return bulletPosition;
	}

public static int generateBulletPosition() {
	Random random = new Random() ;
	getArrayList() [0] =  (  random.nextInt(6)+2);
	for (int i =1; i < numberOfBullets; i++) {
	
		do {  
			getArrayList() [i] =  (  random.nextInt(6)+2);
		
		}while((getArrayList()[i-1] == getArrayList()[i]));
		
	}
 
	
 return bulletPosition;
	
}

public static int[] getArrayList() {
	return arrayList;
}

public static void setArrayList(int[] arrayList) {
	Shotgun.arrayList = arrayList;
}

}

