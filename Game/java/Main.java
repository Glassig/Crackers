/*
 * The main class
 */

import java.util.Scanner;


public class Main {

	World world;

	public static void main(String []args) {
    	Scanner scan = new Scanner(System.in);	// Reading from System.in

       	System.out.println("*~ Welcome to Crackers! ~*\n
       		Which version do you want to play? [text/speech]"); // prints Hello World
       	//TODO NEXT LOOP!!!
       	String ans = scan.next();	// Scans the next token of the input as an int.
       	if(!ans.equals("text") && !ans.equals("speech")){
       		System.out.println("Sorry, but I couldn't understand you. Bye!");
       		System.exit(0);
       	}
       	else if(ans.equals("text")){
       		createSchool();
       	}
       	else {	
       		createApartment();
       	}
    }



    /*
    * Creates the appartment game world
    */
    public World createApartment(){
    	//kitchen
    	Room kitchen = new Room("kitchen", "It's a kitchen! Smells good in here.");
    	Item table = new Item("table", 
    		"A tall table, but you see a cracker laying on the edge of it! Too bad it's too high up for you to reach...",
    		false);
    	Item chair = new Item("chair", "", false);
    	Item kitchenCracker = new Item("cracker", "It's a cracker!!", null);
    	kitchen.addItem(table);
    	kitchen.addItem(chair);
    	kitchen.addItem(kitchenCracker);
    	//livingroom
    	Room livingroom = new Room("livingroom", "It's a livingroom! You somehow feel more comfy.");
    	Item cat = new Item("cat",
    		"A cat is laying on the floor, right next to a cracker! Looks like it's guarding it...",
    		false);
    	Item toy = new Item("toy mouse", "A toy mouse!", false);
    	Item livingroomCracker = new Item("cracker", "It's a cracker!!", null);
    	livingroom.addItem(cat);
    	livingroom.addItem(toy);
    	livingroom.addItem(livingroomCracker);
    	//bedroom
    	Room bedroom = new Room("bedroom", "It's a bedroom! Looking at the bed makes you sleepy.");
    	Item bed = new Item("bed", "A king sized bed. There seems to be something under it.", null);
    	Item safe = new Item("safe", "A safe, but it's locked! It needs a 4-digit code.", false);
    	Item paper = new Item("piece of a paper", 
    		"A piece of a paper, and there's a code on it: 4257.", 
    		null);
    	Item bedroomCracker = new Item("cracker", "It's a cracker!!", null);
    	bedroom.addItem(bed);
    	bedroom.addItem(safe);
    	bedroom.addItem(paper);
    	bedroom.addItem(bedroomCracker);

    	//Create the appartment world
    	String plot = "You are a small pet bunny who has escaped your cage, and you happen to be very hungry. 
    	You have the munchies for crackers. Find all three crackers in the apartment to still your hunger!";
    	String instructions = "";

    	world = new World(plot, instructions);
    	apartment.addRoom(kitchen);
    	apartment.addRoom(livingroom);
    	apartment.addRoom(bedroom);

    	return apartment;
    }

    /*
    * Creates the school game world
    */
    public World createSchool(){
    	//cafeteria
    	Room cafeteria = new Room("cafeteria", "It's a cafeteria!");
    	Item coin = new Item("coin", "A coin.", false);
    	Item vendingMachine = new Item("vending machine", 
    		"A vending machine, there is a cracker for sale! If only you had some money...", 
    		false);
    	cafeteria.addItem(coin);
    	cafeteria.addItem(vendingMachine);
    	//classroom
    	Room classroom = new Room("classroom", "It's a classroom! Maybe they learn how to make crackers here.");
    	Item backpack = new Item("backpack", 
    		"A backpack is standing next to a desk. It's locked with a padlock.", 
    		false);
    	Item desk = new Item("desk", "A desk, maybe there's something in it...", null);
    	Item key = new Item("key", "A small key!", false);
    	livingroom.addItem(backpack);
    	livingroom.addItem(desk);
    	livingroom.addItem(key);
    	//corridor
    	Room corridor = new Room("corridor", "It's a long corridor with more lockers than you can count. Wow!");
    	Item poster = new Item("poster", 
    		"A poster with some text: \"If you solve this you will be lucky! 
    		Maiya has 378 melons.\nShe makes milkshakes for 78 people.\nShe uses 2 melons for each milkshake.\n
    		How many melons does she have left?\"", null);
    	Item locker222 = new Item("locker 222", "A locker with a cracker in it!", false);
    	bedroom.addItem(poster);
    	bedroom.addItem(locker222);

    	//Create the school world
    	String plot = "You are a small class pet bunny who has escaped your cage in the classroom, 
    	and you happen to be very hungry. 
    	You have the munchies for crackers. Find all three crackers in the school to still your hunger!";
    	String instructions = "";

    	world = new World(plot, instructions);
    	school.addRoom(cafeteria);
    	school.addRoom(classroom);
    	school.addRoom(corridor);

    	return school;
    }
}