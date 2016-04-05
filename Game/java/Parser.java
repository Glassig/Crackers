/*
 * This class handles the different command keywords
 * and executes required actions.
 */

public class Parser {

	public Parser(){
	}

	/*
	* Takes in a command pair (verb+noun) and parses the meaning
	* of it.
	*/
	public void parse(Player player, World world, String verb, String noun){
		verb.toLowerCase();
		noun.toLowerCase();

		switch (verb) {
            case "go":	//FIXA
            	for(Room room : world.getRooms()){
            		if(noun.equals(room.getName())){
            			if(player.getCurrentRoom().getName().equals(noun)){
            				System.out.println("You are already in the "+noun+"!\n");
            			}else{
            				player.setCurrentRoom(room);
            				System.out.println(room.getDesc()+"\n");
            			}
            			break;
            		}
            	}
            	break;
            case "take":
            	//if status = true:
            		//ändra type till use
            		//adda till inv
            		//remove from room
            	//if status = false:
            		//printa descriptionFalse
            	boolean found = false;
            	for (Item item : player.getCurrentRoom().getItems()) {
            		if(item.getName().equals(noun)){
            			found = true;
            			if(item.getStatus() && item.getType().equals("take")){
            				item.setType("use");
            				player.addItemToInv(item);
            				player.getCurrentRoom().removeItem(item);
            				System.out.println("You picked up the "+noun+" and put it in your inventory.\n");
            			}else{
            				System.out.println("You can't pick that up.\n");
            			}
            			break;
            		}
            	}
            	if(!found)
            		System.out.println("There is no "+noun+" in this room.\n");
            	break;

            case "eat":	//FIXA
            	Item cracker = null;
            	boolean existsCracker = false;
            	for(Item item : player.getCurrentRoom().getItems()){
            		if(item.getType().equals("cracker")){
            			existsCracker = true;
            			cracker = item;
            			break;
            		}
            	}
            	if(noun.equals("cracker") && existsCracker){
            		player.eatCracker();
            		player.getCurrentRoom().getItems().remove(cracker);
            		System.out.println("You ate the cracker!");
            		System.out.println("You have now eaten a total of "+player.getCrackers()+" crackers.\n");
            	}else{
            		System.out.println("That is not possible right now.\n");
            	}
            	break;

            case "look":
            	//if Room: print items in the room
            	//if Item: check if in room
            		//print description based on status
            	if(player.getCurrentRoom().getName().equals(noun)){
            		System.out.println("You look around the room and see:");
            		for (Item item : player.getCurrentRoom().getItems()) {
            			System.out.println(item.getName());
            		}
            		System.out.println("");
            		break;
            	}
            	for (Item item : player.getCurrentRoom().getItems()) {
            		if(item.getName().equals(noun) && item.getStatus()){
            			System.out.println(item.getDescriptionTrue()+"\n");
            			break;
            		}else if(item.getName().equals(noun) && !item.getStatus()){
            			System.out.println(item.getDescriptionFalse()+"\n");
            			break;
            		}
            	}
            	break;
            case "use":
            	//kolla om finns i inv
            	//kolla om noun finns i usables och i currentRoom
            	//om nej: printa att det inte går
            	//om ja: printa att du använde itemet och ändra noun status till true
            		//ändra noun dependables status till true
            		//remove noun från usables
            	//om usables empty: remove from inv
            	break;
            default:
            	System.out.println("Try something else.\n");
                break;
        }
	}
}