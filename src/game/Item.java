package game;
/*
 * Item class
 */

import java.util.ArrayList;

public class Item {

	private String name; // e.g. key, ball, apple
	private String type; // e.g. move, eat, go
	private String descriptionTrue; // when status is true
	private String descriptionFalse; // when status is false
	private boolean status; // decides if it's accessable yet
	private boolean viewable; // if it's listed when you look around the room
	private ArrayList<Item> dependables; // items that depend on this item's
											// status
	private ArrayList<Item> usables; // items you can use this item on
	private ArrayList<Item> viewDependables;// items you can only discover by
											// first looking at this item

	public Item(String name, String type, String descriptionTrue, String descriptionFalse, boolean status,
			boolean viewable) {
		this.name = name;
		this.type = type;
		this.descriptionTrue = descriptionTrue;
		this.descriptionFalse = descriptionFalse;
		this.status = status;
		this.viewable = viewable;
		dependables = new ArrayList<Item>();
		usables = new ArrayList<Item>();
		viewDependables = new ArrayList<Item>();
	}

	/*
	 * Add a dependable item.
	 */
	public void addDependable(Item item) {
		dependables.add(item);
	}

	/*
	 * Add a usable item.
	 */
	public void addUsable(Item item) {
		usables.add(item);
	}

	/*
	 * Add a viewable dependable
	 */
	public void addViewDependable(Item item) {
		viewDependables.add(item);
	}

	/*
	 * Getters
	 */
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getDescription(boolean status) {
		if (status) {
			return descriptionTrue;
		}
		return descriptionFalse;
	}

	public String getDescriptionTrue() {
		return descriptionTrue;
	}

	public String getDescriptionFalse() {
		return descriptionFalse;
	}

	public boolean getStatus() {
		return status;
	}

	public boolean getViewable() {
		return viewable;
	}

	public ArrayList<Item> getDependables() {
		return dependables;
	}

	public ArrayList<Item> getUsables() {
		return usables;
	}

	public ArrayList<Item> getViewDependables() {
		return viewDependables;
	}

	/*
	 * Setters
	 */
	public void setType(String newType) {
		type = newType;
	}

	public void setStatus(boolean newStatus) {
		status = newStatus;
	}

	public void setViewable(boolean bool) {
		viewable = bool;
	}

}