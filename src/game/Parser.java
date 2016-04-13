package game;
/*
 * This class handles the different command keywords
 * and executes required actions.
 */

import java.util.Scanner;

public class Parser {

	private Scanner scan;

	public Parser() {
		scan = new Scanner(System.in);
	}

	/*
	 * Takes in a command pair (verb+noun) and parses the meaning of it.
	 */
	public void parse(Player player, World world, String verb, String noun) {
		// verb.toLowerCase();
		// noun.toLowerCase(); we already do this in main

		switch (verb) {
		case "go":
			// checks if noun is a room etc
			boolean isRoom = false;
			for (Room room : world.getRooms()) {
				if (noun.equals(room.getName())) {
					isRoom = true;
					if (player.getCurrentRoom().getName().equals(noun)) {
						System.out.println("You are already in the " + noun + "!\n");
					} else {
						player.setCurrentRoom(room);
						System.out.println(room.getDesc() + "\n");
					}
					break;
				}
			}
			// if noun was a room, we break here
			if (isRoom) {
				break;
			}
			// if noun was not a room, check if item etc
			boolean isItem = false;
			for (Item item : player.getCurrentRoom().getItems()) {
				if (noun.equals(item.getName()) && item.getType().equals("go") && item.getStatus()) {
					isItem = true;
					System.out.println("You are now on the " + noun + ".\n");
					for (Item dep : item.getDependables()) {
						dep.setStatus(true);
					}
					break;
				}
			}
			if (!isItem) {
				System.out.println("You cannot go there.\n");
			}
			break;

		case "take":
			if(noun.equals("cracker")) {
				parse(player, world, "eat", "cracker");
			} else {
				boolean found = false;
				for (Item item : player.getCurrentRoom().getItems()) {
					if (item.getName().equals(noun)) {
						found = true;
						if (item.getStatus() && item.getType().equals("take")) {
							item.setType("use");
							player.addItemToInv(item);
							player.getCurrentRoom().removeItem(item);
							System.out.println("You picked up the " + noun + " and put it in your inventory.\n");
						} else {
							System.out.println("You can't pick that up.\n");
						}
						break;
					}
				}
				if (!found) {
					System.out.println("There is no " + noun + " in this room.\n");
				}
			}
			break;

		case "eat":
			boolean foundEat = false;
			for (Item item : player.getCurrentRoom().getItems()) {
				if (item.getType().equals("eat") && item.getName().equals(noun) && item.getStatus()) {
					foundEat = true;
					player.eatCracker();
					player.getCurrentRoom().getItems().remove(item);
					System.out.println("You ate the " + noun + ".");
					if (item.getName().equals("cracker")) {
						System.out.println("You have eaten " + player.getCrackers() + " crackers so far.");
					}
					System.out.println("");
					break;
				}
			}
			if (!foundEat) {
				System.out.println("Well, that didn't work...\n");
			}
			break;

		case "look":
			boolean lookItem = false;
			// if noun is the current room then list the viewable items
			if (player.getCurrentRoom().getName().equals(noun) || noun.equals("room")) {
				System.out.println("You look around the room and see:");
				for (Item item : player.getCurrentRoom().getItems()) {
					if (item.getViewable()) {
						System.out.println(item.getName());
					}
				}
				System.out.println("");
				break;
			}
			// if noun is an item in the current room, print it's description
			boolean inRoom = false;
			// special case
			if (noun.contains("locker") && !noun.equals("lockers") && !noun.equals("locker222")) {
				for (Item item : player.getCurrentRoom().getItems()) {
					if (item.getName().equals("locker")) {
						System.out.println("An empty locker...\n");
						inRoom = true;
					}
				}
			}
			if (inRoom)
				break;

			for (Item item : player.getCurrentRoom().getItems()) {
				if (item.getName().equals(noun)) {
					inRoom = true;
					for (Item dep : item.getViewDependables()) {
						if (item.getName().equals("backpack") && dep.getName().equals("cracker") && !item.getStatus()) {
							// do nothing
						} else if (item.getName().equals("vault") && dep.getName().equals("cracker")
								&& !item.getStatus()) {
							// do nothing
						} else {
							dep.setViewable(true);
						}
						if (item.getName().equals("locker222") && dep.getName().equals("cracker")) {
							dep.setStatus(true);
							for (Item dep2 : dep.getDependables()) {
								dep2.setStatus(true);
							}
						}
					}
					if (item.getStatus()) {
						System.out.println(item.getDescriptionTrue() + "\n");
					} else {
						System.out.println(item.getDescriptionFalse() + "\n");
					}
					// special case
					if (noun.equals("vault") && !item.getStatus()) {
						System.out.print("Enter code (use keyboard): ");
						String code = scan.nextLine();
						if (code.equals("1475") || code.equals("one four seven five")) {
							System.out.println("The code worked! The vault is now open.\n");
							item.setStatus(true);
							for (Item dep : item.getDependables()) {
								dep.setStatus(true);
							}
						} else {
							System.out.println("It didn't work. :(\n");
						}
					}
					break;
				}
			}
			if (inRoom)
				break;
			// if item is in inventory, print description etc
			for (Item item : player.getInventory()) {
				if (item.getName().equals(noun)) {
					lookItem = true;
					System.out.println(item.getDescription(item.getStatus()));
					System.out.println("You have the " + noun + " in your inventory.\n");
					break;
				}
			}
			if (!lookItem) {
				System.out.println("There is no " + noun + " to examine.\n");
			}
			break;

		case "use":
			// check if in inventory
			Item usedItem = null;
			for (Item item : player.getInventory()) {
				if (item.getName().equals(noun) && item.getType().equals("use")) {
					usedItem = item;
				}
			}
			// break if it was not in inventory
			if (usedItem == null) {
				System.out.println("You don't have any " + noun + " in your inventory.\n");
				break;
			}
			// check if there is any item in the room that you can use the item
			// on
			boolean foundItem = false;
			for (Item usable : usedItem.getUsables()) {
				if (player.getCurrentRoom().getItems().contains(usable)) {
					// it exists
					foundItem = true;
					usable.setStatus(true);
					for (Item dep : usable.getDependables()) {
						dep.setStatus(true);
					}
					player.getInventory().remove(usedItem);
					System.out.println("You used the " + usedItem.getName() + " on the " + usable.getName() + ".");
					System.out.println("The " + usedItem.getName() + " was removed from your inventory.\n");
					break;
				}
			}
			if (!foundItem) {
				System.out.println("There is nothing in this room to use the " + usedItem.getName() + " on.\n");
			}
			break;

		default:
			System.out.println("Try something else.\n");
			break;
		}
	}
}