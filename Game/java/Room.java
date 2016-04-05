/*
 * Room class
 */

import java.util.ArrayList;


public class Room {

	private String name;
	private String description;
	private ArrayList<Item> items;

    public Room(String name, String description){
    	this.name = name;
    	this.description = description;
    	this.items = new ArrayList<Item>();
    }

    /*
    * Adds an item to the list of items in the room.
    */
    public void addItem(Item item){
    	items.add(item);
    }

    /*
    * Removes an item from the list of items in the room.
    */
    public void removeItem(Item item){
    	items.remove(item);
    }



    /*
    * Getters
    */
    public String getName(){
    	return name;
    }

    public String getDesc(){
    	return description;
    }

    public ArrayList<Item> getItems(){
    	return items;
    }

} 