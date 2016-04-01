/*
 * The main class
 */

import java.util.Scanner;
import java.util.List;
import java.lang.StringBuilder;

import edu.stanford.nlp.ling.TaggedWord;


public class Main {

  	private static Player bunny;
	private static World world;
	private static Scanner scan = new Scanner(System.in);
  	private static POSTagger tagger = new POSTagger("models/english-left3words-distsim.tagger");
  	private static Synonyms synonyms = new Synonyms();
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

       	//This runs until you have collected all 3 crackers!
       	while(bunny.getCrackers() < 3){
       		System.out.print("@"+bunny.getCurrentRoom().getName()+": ");
       		String com = scan.nextLine();
          	bunny.updateCommandCount();
       		if(com.contains("quit") || com.contains("exit"))
       			System.exit(0);
          	else if(com.contains("help"))
            	System.out.println(world.getInst()+"\n");
            else if(com.contains("items") || com.contains("inventory")){
            	System.out.println("Your inventory contains:");
            	if(bunny.getInventory().isEmpty())
            		System.out.println("nothing at all.");
            	for (Item item : bunny.getInventory()) {
            		System.out.println(item.getName());
            	}
            	System.out.println("");
            }

       		//handle user input here!
          	String[] comWords = com.split("[\\W]");
          	List<TaggedWord> comTagged = tagger.postag(comWords); //ONLY VB AND NN INCLUDED

          	boolean foundValidVerb = false;
          	String vb = null;
          	for(TaggedWord tw : comTagged){
            	if(tw.tag().startsWith("VB")){
            		String synVb = synonyms.verbs(tw.word());
            		if(synVb != null){
              			foundValidVerb = true;
              			vb = synVb;
              		}
            	}
            	else if(tw.tag().startsWith("NN") && foundValidVerb){
              		parser.parse(bunny, world, vb, tw.word());
              		foundValidVerb = false;	//might not be needed
            	}
          	}

       	}

       	//victory!
       	System.out.println("Victory! You are no longer hungry.\n"
       						+ "You completed the game using " + bunny.getCommandCount() + "commands. \n"
                  			+ "Thank you for playing! <3\n");
    }



    /*
    * Creates the appartment game world
    */
    public static void createApartment(){
    	//KITCHEN
    	Room kitchen = new Room("kitchen", "It's a kitchen! Smells good in here.");
    	Item kitchenCracker = new Item("cracker", 
    		"eat", 
    		"There's a cracker laying on the table, what are you waiting for?!",
    		"There's a cracker laying on the table, but it's too high up to reach...",
    		 false);
    	Item table = new Item("table",
    		"go", 
    		"A tall table.",
    		"A table too tall to jump up onto from the floor...",
    		false);
    	Item box = new Item("box", 
    		"go", 
    		"A box standing next to the table, just small enough for you to jump up onto.", 
    		"",
    		true);
    	box.addDependable(table);
    	table.addDependable(kitchenCracker);
    	kitchen.addItem(kitchenCracker);
    	kitchen.addItem(table);
    	kitchen.addItem(box);
    	//LIVINGROOM
    	Room livingroom = new Room("livingroom", "It's a livingroom! You get a cozy feeling.");
    	Item livingroomCracker = new Item("cracker", 
    		"eat", 
    		"There's a cracker laying on the floor!",
    		"There's a cracker laying on the floor right next to the cat. If only you could fet rid of that cat...",
    		false);
    	Item cat = new Item("cat",
    		"",
    		"The cat is gone!",
    		"A scary looking cat is laying on the floor. Better stay away.",
    		false);
    	Item toy = new Item("toy",
    	 	"take", 
    	 	"A toy mouse!", 
    	 	"",
    	 	true);
    	toy.addUsable(cat);
    	cat.addDependable(livingroomCracker);
    	livingroom.addItem(livingroomCracker);
    	livingroom.addItem(cat);
    	livingroom.addItem(toy);
    	//BEDROOM
    	Room bedroom = new Room("bedroom", "It's a bedroom! You feel a bit sleepy.");
    	Item bedroomCracker = new Item("cracker",
    		"eat",
    		"There's a cracker laying in the safe!!",
    		"There's a cracker somewhere in here...",
    		false);
    	Item trash = new Item("trash", 
    		"", 
    		"A trash can, it's almost empty except for a small piece of paper.",
    		"", 
    		true);
    	Item safe = new Item("safe", 
    		"", 
    		"The safe is open, and there's a cracker in it!",
    		"A safe, but it's locked. If only you knew the code.", 
    		false);
    	Item paper = new Item("paper",
    		"take",
    		"A paper with some kind of code on it. I wonder what it's for...",
    		"", 
    		true);
    	paper.addUsable(safe);
    	safe.addDependable(bedroomCracker);
    	bedroom.addItem(bedroomCracker);
    	bedroom.addItem(trash);
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
    	//CAFETERIA
    	Room cafeteria = new Room("cafeteria", "It's a cafeteria!");
    	Item cafeteriaCracker = new Item("cracker",
    		"eat",
    		"The cracker dropped down in the vending machine!",
    		"There's a cracker for sale in the vending machine.",
    		false);
    	Item coin = new Item("coin",
    		"take", 
    		"A shiny coin!",
    		"",
    		true);
    	Item vendingMachine = new Item("vending machine", 
    		"",
    		"You bought the cracker!",
    		"A vending machine, there is a single cracker left for sale! If only you had some money.", 
    		false);
    	coin.addUsable(vendingMachine);
    	vendingMachine.addDependable(cafeteriaCracker);
    	cafeteria.addItem(cafeteriaCracker);
    	cafeteria.addItem(coin);
    	cafeteria.addItem(vendingMachine);
    	//CLASSROOM
    	Room classroom = new Room("classroom", "It's a classroom! You feel a little smarter.");
    	Item classroomCracker = new Item("cracker",
    		"eat",
    		"There's a cracker in the backpack!",
    		"There's a cracker in here somwhere...",
    		false);
    	Item backpack = new Item("backpack", 
    		"",
    		"An open backpack.",
    		"A backpack is standing next to a desk. It's locked with a padlock.", 
    		false);
    	Item desk = new Item("desk", 
    		"",
    		"A desk, looking closer you see a key laying on it.",
    		"", 
    		true);
    	Item key = new Item("key", 
    		"take",
    		"A small key!", 
    		"",
    		true);
    	key.addUsable(backpack);
    	backpack.addDependable(classroomCracker);
    	classroom.addItem(classroomCracker);
    	classroom.addItem(backpack);
    	classroom.addItem(desk);
    	classroom.addItem(key);
    	//CORRIDOR
    	Room corridor = new Room("corridor", "It's a long corridor filled with lockers numbered between 001-999");
    	Item corridorCracker = new Item("cracker",
    		"eat",
    		"There's a cracker in locker 222!",
    		"There's a cracker somewhere in here...",
    		false);
    	Item poster = new Item("poster", 
    		"",
    		"A poster with some text: \"If you solve this you will be lucky!\n" 
    		+"Maiya has 378 melons.\n"
    		+"She makes milkshakes for 78 people.\n"
    		+"She uses 2 melons for each milkshake.\n"
    		+"How many melons does she have left?\"", 
    		"",
    		true);
    	Item locker = new Item("locker", 
    		"",
    		"This locker has a cracker in it!",
    		"An empty locker.",
    		false);
    	poster.addDependable(locker);
    	locker.addDependable(corridorCracker);
    	corridor.addItem(corridorCracker);
    	corridor.addItem(poster);
    	corridor.addItem(locker);

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