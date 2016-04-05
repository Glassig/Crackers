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
            case "go":
                //checks if noun is a room etc
                boolean isRoom = false;
            	for(Room room : world.getRooms()){
            		if(noun.equals(room.getName())){
                        isRoom = true;
            			if(player.getCurrentRoom().getName().equals(noun)){
                            System.out.println("You are already in the "+noun+"!\n");
            			}else{
            				player.setCurrentRoom(room);
            				System.out.println(room.getDesc()+"\n");
            			}
            			break;
            		}
            	}
                //if noun was a room, we break here
                if(isRoom){
                    break;
                }
                //if noun was not a room, check if item etc
                for(Item item : player.getCurrentRoom().getItems()){
                    if(noun.equals(item.getName()) && item.getType().equals("go")){
                        System.out.println("You are now on the "+noun+".\n");
                        for(Item dep : item.getDependables()){
                            dep.setStatus(true);
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
            	if(!found){
            		System.out.println("There is no "+noun+" in this room.\n");
                }
            	break;

            case "eat":
            	for(Item item : player.getCurrentRoom().getItems()){
            		if(item.getType().equals("eat") && item.getName().equals(noun) && item.getStatus()){
            			player.eatCracker();
                        player.getCurrentRoom().getItems().remove(item);
                        System.out.println("You ate the "+noun+".");
                        if(item.getName().equals("cracker"))
                            System.out.println("You have eaten "+player.getCrackers()+" crackers so far.");
                        System.out.println("");
                        break;
            		}
            	}
            	break;

            case "look":
            	//if noun is the current room then list the viewable items
            	if(player.getCurrentRoom().getName().equals(noun) || noun.equals("room")){
            		System.out.println("You look around the room and see:");
            		for (Item item : player.getCurrentRoom().getItems()) {
                        if(item.getViewable())
                            System.out.println(item.getName());
            		}
            		System.out.println("");
            		break;
            	}
                //if noun is an item in the current room, print it's description
            	for (Item item : player.getCurrentRoom().getItems()) {
            		if(item.getName().equals(noun)){
                        for(Item dep : item.getViewDependables()){
                            dep.setViewable(true);
                            if(item.getName().equals("locker222") && dep.getName().equals("cracker"))
                                dep.setStatus(true);
                        }
                        if(item.getStatus()){
                            System.out.println(item.getDescriptionTrue()+"\n");
                        }else{
                            System.out.println(item.getDescriptionFalse()+"\n");
                        }
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