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
            	for(Room room : world.getRooms()){
            		if(noun.equals(room.getName())){
            			if(player.getCurrentRoom().getName().equals(noun)){
            				System.out.println("You are already in the "+noun+"!\n");
            			}else{
            				player.setCurrentRoom(room);
            				System.out.println("You are now in the "+noun+".\n");
            			}
            			break;
            		}
            	}
            	break;
            case "take":
            	break;

            case "eat":
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
            	break;
            case "interact":
            	break;
            default:
            	//System.out.println("I'm not sure what you are trying to do.\n");
                break;
        }
	}
}