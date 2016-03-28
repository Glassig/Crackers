/*
 * World class
 */
 import java.util.ArrayList;



 public class World {

 	String plot;
 	String instructions;
 	ArrayList<Room> rooms;
 	Room currentLocation;
 	int crackerCount;

 	public World(String plot, String instructions, Room startLocation){
 		this.plot = plot;
 		this.instructions = instructions;
 		rooms = new ArrayList<Room>();
 		currentLocation = startLocation;
 		crackerCount = 0;
 	}

 	/*
 	* Adds a room to the world.
 	*/
 	public void addRoom(Room room){
 		rooms.add(room);
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
 	public String getPlot(){
 		return plot;
 	}

 	public String getInst(){
 		return instructions;
 	}

 	public ArrayList getRooms(){
 		return rooms;
 	}

 	public int getCrackers(){
 		return crackerCount;
 	}

 	public String getLocation(){
 		return currentLocation.getName();
 	}

 	/*
 	* Setters
 	*/
 	public void setLocation(Room newLoc){
 		currentLocation = newLoc;
 	}

 }