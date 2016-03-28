/*
 * World class
 */

 public class World() {

 	String plot;
 	String instructions;
 	ArrayList<Room> rooms;
 	int crackerCount;

 	public World(String plot, String instructions){
 		this.plot = plot;
 		this.instructions = instructions;
 		this.rooms = new ArrayList<Room>();
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



 	//getters
 	public String getPlot(){
 		return plot;
 	}

 	public String getInst(){
 		return instructions;
 	}

 	public ArrayList getRooms(){
 		return rooms;
 	}

 }