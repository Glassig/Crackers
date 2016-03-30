/*
 * The main class
 */

import java.util.Scanner;
import java.lang.StringBuilder;


public class Main {

  private static Player bunny;
	private static World world;
	private static Scanner scan = new Scanner(System.in);
  private static POSTagger tagger = new POSTagger("models/english-left3words-distsim.tagger");
	private static Parser parser = new Parser();

	public static void main(String []args) {

       	System.out.println("\n*~ Welcome to Crackers! ~*\n"
       		+ "Which version do you want to play? [text/speech]\n");

       	System.out.print("Version: ");
       	String ans = scan.nextLine();
       	if(ans.equals("text")){
       		createSchool();
       	}
       	else if(ans.equals("speech")){	
       		createApartment();
       	}
       	else{
       		System.out.println("Sorry, but I couldn't understand you. Bye!\n");
       		System.exit(0);
       	}

       	System.out.println("\nPLOT:\n"+world.getPlot()+"\n");
       	System.out.println("INSTRUCTIONS:\n"+world.getInst()+"\n");

       	//this runs until you have collected all 3 crackers!
       	while(bunny.getCrackers() < 3){
       		System.out.print("@"+bunny.getCurrentRoom().getName()+": ");
       		String com = scan.nextLine();
          bunny.updateCommandCount();
       		if(com.equals("quit") || com.equals("exit"))
       			System.exit(0);
          else if(com.equals("help"))
            System.out.println(world.getInst()+"\n");

       		//handle user input here!
          String[] comWords = com.split("[\\W]");

          tagger.postag(comWords);

       	}

       	//victory!
       	System.out.println("Victory! You are no longer hungry.\n"
       						+ "You completed the game in: " + bunny.getCommandCount() + "commands. \n"
                  + "Thank you for playing! <3\n");
    }



    /*
    * Creates the appartment game world
    */
    public static void createApartment(){
    	//kitchen
    	Room kitchen = new Room("kitchen", "It's a kitchen! Smells good in here.");
    	Item table = new Item("table", 
    		"A tall table, but you see a cracker laying on the edge of it! Too bad it's too high up for you to reach...",
    		false);
    	Item chair = new Item("chair", "", false);
    	kitchen.addItem(table);
    	kitchen.addItem(chair);
    	//livingroom
    	Room livingroom = new Room("livingroom", "It's a livingroom! You somehow feel more comfy.");
    	Item cat = new Item("cat",
    		"A cat is laying on the floor, right next to a cracker! Looks like it's guarding it...",
    		false);
    	Item toy = new Item("toy mouse", "A toy mouse!", false);
    	livingroom.addItem(cat);
    	livingroom.addItem(toy);
    	//bedroom
    	Room bedroom = new Room("bedroom", "It's a bedroom! Looking at the bed makes you sleepy.");
    	Item bed = new Item("bed", "A king sized bed. There seems to be something under it.", false);
    	Item safe = new Item("safe", "A safe, but it's locked! It needs a 4-digit code.", false);
    	Item paper = new Item("piece of a paper", 
    		"A piece of a paper, and there's a code on it: 4257.", 
    		false);
    	bedroom.addItem(bed);
    	bedroom.addItem(safe);
    	bedroom.addItem(paper);

      bunny = new Player(kitchen);

    	//Create the appartment world
    	String plot = "You are a small pet bunny who has escaped your cage, and you happen to be very hungry.\n"
    		+"You have the munchies for crackers.\n"
    		+"Find all three crackers in the apartment to still your hunger!";
    	String instructions = "";
    	
      world = new World(plot, instructions);
    	world.addRoom(kitchen);
    	world.addRoom(livingroom);
    	world.addRoom(bedroom);
    }

    /*
    * Creates the school game world
    */
    public static void createSchool(){
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
    	Item desk = new Item("desk", "A desk, maybe there's something in it...", false);
    	Item key = new Item("key", "A small key!", false);
    	classroom.addItem(backpack);
    	classroom.addItem(desk);
    	classroom.addItem(key);
    	//corridor
    	Room corridor = new Room("corridor", "It's a long corridor with more lockers than you can count. Wow!");
    	Item poster = new Item("poster", 
    		"A poster with some text: \"If you solve this you will be lucky!\n" 
    		+"Maiya has 378 melons.\n"
    		+"She makes milkshakes for 78 people.\n"
    		+"She uses 2 melons for each milkshake.\n"
    		+"How many melons does she have left?\"", false);
    	Item locker222 = new Item("locker 222", "A locker with a cracker in it!", false);
    	corridor.addItem(poster);
    	corridor.addItem(locker222);

      bunny = new Player(classroom);

    	//Create the school world
    	String plot = "You are a small sexy class pet bunny who has escaped your cage in the classroom, and you happen to be very hungry.\n" 
    		+"You have the munchies for crackers.\n"
    		+"Find all three crackers in the school to still your hunger!";
    	String instructions = "";

    	world = new World(plot, instructions);
    	world.addRoom(cafeteria);
    	world.addRoom(classroom);
    	world.addRoom(corridor);
    }
}