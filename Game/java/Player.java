/*
 * This class holds information of a player
 */

import java.util.ArrayList;


public class Player {
	Room currentRoom;
	int crackerCount;
	ArrayList<Item> inventory;

	public Player(Room startRoom){
		currentRoom = startRoom;
		crackerCount = 0;
		inventory = new ArrayList<Item>();
	}


	/*
 	* Increment cracker count (eat a cracker)
 	*/
 	public void eatCracker(){
 		crackerCount++;
 	}



 	/*
 	* Getters
 	*/
 	public int getCrackers(){
 		return crackerCount;
 	}

 	public Room getCurrentRoom(){
 		return currentRoom;
 	}

	/*
 	* Setters
 	*/
 	public void setCurrentRoom(Room newLoc){
 		currentRoom = newLoc;
 	}

}