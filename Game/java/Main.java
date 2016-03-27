/*
 * The main class
 */

import java.util.Scanner;


public class Main {

	

	public static void main(String []args) {
    	Scanner scan = new Scanner(System.in);	// Reading from System.in

       	System.out.println("~* Welcome to Crackers! *~\nDo you want to play the game? [y/n]"); // prints Hello World
       	String ans = scan.next();	// Scans the next token of the input as an int.
       	if(!ans.equals("n") && !ans.equals("y")){
       		System.out.println("Sorry, but I couldn't understand you. Bye!");
       		System.exit(0);
       	}
       	else if(ans.equals("n")){
       		System.out.println("Bye!");
       		System.exit(0);
       	}
       	else {	//initiate game

       		System.out.println("Game is not playable yet! Please be patient. :D");
       		System.exit(0);
       		

       	}
    }
}