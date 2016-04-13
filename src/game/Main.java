/*
 * The main class
 */
package game;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static Player bunny;
	private static World world;
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("\n*~ Welcome to Crackers! ~*\n" + "Which version do you want to play? [text/speech]\n");

		System.out.print("Version: ");
		String ans = scan.nextLine();
		if (ans.equals("text")) {
			createSchool();
			TextVersion game = new TextVersion(world, bunny);
			game.run();
		} else if (ans.equals("speech")) {
			createApartment();
			SpeechVersion game = new SpeechVersion(world, bunny);
			game.run();
		} else {
			System.out.println("Sorry, but I couldn't understand you. Bye!\n");
			System.exit(0);
		}

	}

	/*
	 * Creates the apartment game world
	 */
	public static void createApartment() {
		// KITCHEN
		Room kitchen = new Room("kitchen", "It's a kitchen! Smells good in here.");
		Item kitchenCracker = new Item("cracker", "eat",
				"There's a cracker laying on the table, what are you waiting for?!",
				"There's a cracker laying on the table, but it's too high up to reach...", false, true);
		Item table = new Item("table", "go", "A tall table.", "A table too tall to jump up onto from the floor...",
				false, true);
		Item box = new Item("box", "go", "A box standing next to the table, just small enough for you to jump up onto.",
				"", true, true);
		box.addDependable(table);
		table.addDependable(kitchenCracker);
		kitchen.addItem(kitchenCracker);
		kitchen.addItem(table);
		kitchen.addItem(box);
		// LIVINGROOM
		Room livingroom = new Room("livingroom", "It's a livingroom! You get a cozy feeling.");
		Item livingroomCracker = new Item("cracker", "eat", "There's a cracker laying on the floor!",
				"There's a cracker laying on the floor right next to the cat. If only you could get rid of that cat...",
				false, true);
		Item cat = new Item("cat", "", "The cat is gone!",
				"A scary looking cat is laying on the floor. Better stay away.", false, true);
		Item toy = new Item("toy", "take", "A toy mouse!", "", true, true);
		toy.addUsable(cat);
		cat.addDependable(livingroomCracker);
		livingroom.addItem(livingroomCracker);
		livingroom.addItem(cat);
		livingroom.addItem(toy);
		// BEDROOM
		Room bedroom = new Room("bedroom", "It's a bedroom! You feel a bit sleepy.");
		Item bedroomCracker = new Item("cracker", "eat", "There's a cracker laying in the safe!!",
				"There's a cracker somewhere in this room...", false, false);
		Item trash = new Item("trash", "", "A trash can, it's almost empty except for a letter.", "", true,
				true);
		Item vault = new Item("depository", "", "The depository is open, and there's a cracker in it!",
				"A depository, but it's locked with a 4-digit code.", false, true);
		Item paper = new Item("letter", "", "A letter with the number 1475 written on it.", "", true, false);
		// paper.addUsable(vault);
		vault.addDependable(bedroomCracker);
		vault.addViewDependable(bedroomCracker);
		trash.addViewDependable(paper);
		bedroom.addItem(bedroomCracker);
		bedroom.addItem(trash);
		bedroom.addItem(vault);
		bedroom.addItem(paper);

		bunny = new Player(kitchen);

		// Create the apartment world
		String plot = "You are a small pet bunny who has escaped your cage, and you happen to be very hungry.\n"
				+ "You have the munchies for crackers.\n"
				+ "Find all three crackers in the apartment to still your hunger!";
		
		String instructions = "Speak commands in order to progress.\n"
				+ "Commands mostly follow the structure of <verb> <noun> with optional determiners and conjunctions inbetween.\n"
				+ "Try speaking as if you were talking to any human.\n"
				+ "Some useful commands: \n"
				+ "   *   \"quit\" to quit the game\n"
				+ "   *   \"help\" tells you these instructions\n\n"
				+ "Your goal is to find and eat all three crackers.\n"
				+ "There is ONE CRACKER IN EACH ROOM and sometimes they are hidden somewhere, so be sure to look around carefully!\n"
				+ "If you have trouble with your commands, try using synonyms for the words you want to use!\n\n"
				+ "Available rooms: kitchen, livingroom and bedroom\n";

		world = new World(plot, instructions);
		world.addRoom(kitchen);
		world.addRoom(livingroom);
		world.addRoom(bedroom);
	}

	/*
	 * Creates the school game world
	 */
	public static void createSchool() {
		// CAFETERIA
		Room cafeteria = new Room("cafeteria", "It's a cafeteria!");
		Item cafeteriaCracker = new Item("cracker", "eat", "The cracker dropped down in the vending machine!",
				"There's a cracker for sale in the vending machine.", false, false);
		Item coin = new Item("coin", "take", "A shiny coin!", "", true, true);
		Item machine = new Item("machine", "", "You bought the cracker!",
				"A vending machine, there is a single cracker left for sale! If only you had some money.", false, true);
		coin.addUsable(machine);
		machine.addDependable(cafeteriaCracker);
		machine.addViewDependable(cafeteriaCracker);
		cafeteria.addItem(cafeteriaCracker);
		cafeteria.addItem(coin);
		cafeteria.addItem(machine);
		// CLASSROOM
		Room classroom = new Room("classroom", "It's a classroom! You feel a little smarter.");
		Item classroomCracker = new Item("cracker", "eat", "There's a cracker in the backpack!",
				"There's a cracker somewhere in this room...", false, false);
		Item backpack = new Item("backpack", "", "An open backpack, there's a cracker in it!",
				"A backpack is standing next to a desk. It's locked with a padlock.", false, true);
		Item desk = new Item("desk", "", "A desk, looking closer you see a key laying on it.", "", true, true);
		Item key = new Item("key", "take", "A small key!", "", true, false);
		key.addUsable(backpack);
		backpack.addDependable(classroomCracker);
		backpack.addViewDependable(classroomCracker);
		desk.addViewDependable(key);
		classroom.addItem(classroomCracker);
		classroom.addItem(backpack);
		classroom.addItem(desk);
		classroom.addItem(key);
		// CORRIDOR
		Room corridor = new Room("corridor", "It's a long corridor filled with lockers numbered locker001-locker999");
		Item corridorCracker = new Item("cracker", "eat", "There's a cracker in locker 222!",
				"There's a cracker somewhere in this room...", false, false);
		Item poster = new Item("poster", "",
				"A poster with some text:\n" + "\"If you solve this you will be lucky!\n" + "Maiya has 378 melons.\n"
						+ "She makes milkshakes for 78 people.\n" + "She uses 2 melons for each milkshake.\n"
						+ "How many melons does she have left?\"",
				"", true, true);
		Item locker222 = new Item("locker222", "", "This locker has a cracker in it!", "", true, false);
		Item locker = new Item("locker", "", "An empty locker...", "", true, false);
		Item lockers = new Item("lockers", "", "Lockers ranging from locker001-locker999.", "", true, true);
		locker222.addDependable(corridorCracker);
		locker222.addViewDependable(corridorCracker);
		// poster.addViewDependable(locker222);
		corridor.addItem(corridorCracker);
		corridor.addItem(poster);
		corridor.addItem(locker222);
		corridor.addItem(locker);
		corridor.addItem(lockers);

		bunny = new Player(classroom);

		// Create the school world
		String plot = "You are a small pet bunny who has escaped your cage in the classroom, and you happen to be very hungry.\n"
				+ "You have the munchies for crackers.\n"
				+ "Find all three crackers in the school to still your hunger!";
		
		String instructions = "Type commands in order to progress.\n"
				+ "Commands mostly follow the structure of <verb> <noun> with optional determiners and conjunctions inbetween.\n"
				+ "Try typing as if you were talking to any human.\n"
				+ "Some useful commands: \n"
				+ "   *   \"quit\" to quit the game\n"
				+ "   *   \"help\" give you these instructions\n\n"
				+ "Your goal is to find and eat all three crackers.\n"
				+ "There is ONE CRACKER IN EACH ROOM and sometimes they are hidden somewhere, so be sure to look around carefully!\n"
				+ "If you have trouble with your commands, try using synonyms for the words you want to use!\n\n"
				+ "Available rooms: classroom, cafeteria and corridor\n";

		world = new World(plot, instructions);
		world.addRoom(cafeteria);
		world.addRoom(classroom);
		world.addRoom(corridor);
	}
}