/*
 * Item class
 */

public class Item {

	private String type;
	private String description;
	private boolean status;

	public Item(String type, String description, boolean status){
    	this.type = type;
    	this.description = description;
    	this.status = status;
    }



    /*
    * Getters
    */
    public String getType(){
    	return type;
    }

    public String getDesc(){
    	return description;
    }

    public boolean getStatus(){
    	return status;
    }

    /*
    * Setters
    */
    public void setDescription(String newDesc){
    	description = newDesc;
    }

    public void setStatus(boolean newStatus){
    	status = newStatus;
    }

} 