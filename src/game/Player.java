package game;
/*
 * This class holds information of a player
 */

import java.util.ArrayList;

public class Player {
	Room currentRoom;
	int crackerCount;
	int commandCount;
	ArrayList<Item> inventory;

	public Player(Room startRoom) {
		currentRoom = startRoom;
		crackerCount = 0;
		commandCount = 0;
		inventory = new ArrayList<Item>();
	}

	/*
	 * Increment cracker count (eat a cracker)
	 */
	public void eatCracker() {
		crackerCount++;
	}

	/*
	 * Increment command counter with 1
	 */
	public void updateCommandCount() {
		commandCount++;
	}

	/*
	 * Add item to inventory
	 */
	public void addItemToInv(Item item) {
		inventory.add(item);
	}

	/*
	 * Remove item from inventory
	 */
	public void removeItemFromInv(Item item) {
		inventory.remove(item);
	}

	/*
	 * Getters
	 */
	public int getCrackers() {
		return crackerCount;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public int getCommandCount() {
		return commandCount;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	/*
	 * Setters
	 */
	public void setCurrentRoom(Room newLoc) {
		currentRoom = newLoc;
	}

}