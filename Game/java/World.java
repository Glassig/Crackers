/*
 * World class
 */

 public class World() {

 	String plot;
 	String instructions;
 	ArrayList<Room> rooms;

 	public World(String plot, String instructions){
 		this.plot = plot;
 		this.instructions = instructions;
 		this.rooms = new ArrayList<Room>();
 	}

 	/*
 	* Adds a room to the world.
 	*/
 	public void addRoom(Room room){
 		rooms.add(room);
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