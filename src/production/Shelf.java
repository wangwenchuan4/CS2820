package production;

import java.util.*;

public class Shelf {

	static final int maxItems = 5;

	Point homeLocation;
	Point currentLocation;
	boolean resting; // true if at home, not on robot
	List<Item> shelfStock;

	/**
	 * Initialize shelf at a given point.
	 * 
	 * @author Grant Gertsen
	 * @param home
	 */
	public Shelf(Point home) {
		homeLocation = home;
		currentLocation = home;
		shelfStock = new ArrayList<Item>(maxItems);
	}

	/**
	 * Check if an item is on the shelf by name
	 * 
	 * @author Grant Gertsen
	 * @param itemName
	 * @return true if that item is on the shelf
	 */
	public boolean hasItem(String itemName) {
		for (Item item : shelfStock) {
			if (item.itemName.equals(itemName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if an item is on shelf by serial number
	 * 
	 * @author Grant Gertsen
	 * @param itemNumber
	 * @return true if that item is on the shelf
	 */
	public boolean hasItem(int itemNumber) {
		for (Item item : shelfStock) {
			if (item.serialNumber == itemNumber) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if an item is on shelf by item
	 * @author Grant Gertsen
	 * @param item
	 * @return true if that item is on the shelf
	 */
	public boolean hasItem(Item item) {
		return hasItem(item.getSerialNumber());
	}

	/**
	 * Add an item to the shelf
	 * 
	 * @author Grant Gertsen
	 * @param item
	 *            the item to add
	 */
	public void addToShelf(Item item) {
		if (shelfStock.size() < 5) {
			shelfStock.add(item);
			item.shelf = this;
			System.out.println("Adding " + item + " to shelf!");
		} else {
			System.out.println("Shelf full!");
			// need to figure out what to do if shelf is full
		}
	}
	
	public Item removeItem(Item item) {
		//Item temp = new Item(item.getItemName(), item.getSerialNumber(), this);
		System.out.println("Trying to remove " + item + " from shelf " + item.shelf);
		this.showItems();
		if(shelfStock.contains(item)) {
			shelfStock.remove(item);
			System.out.println(item + " taken from shelf ");
			item.changeShelf(null);
			return item;
		}
		System.out.println("Item removal failure");
		return null; // error
	}
	
	public Point getLocation() {
		return currentLocation;
	}
	
	public Point getHomeLocation() {
		return homeLocation;
	}
	
	public void showItems() {
		System.out.println("\nList of items:");
		for(Item item : shelfStock) {
			System.out.println("Shelf has " + item);
		}
		System.out.println();
	}

	/**
	 * @author Casey Kolodziejczyk
	 * Just overrides toString for testing purposes
	 */
	@Override
	public String toString() {
		return ("" + homeLocation + "");
	}
	
}
