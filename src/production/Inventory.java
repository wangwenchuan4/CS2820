package production;

import java.util.*;

/**
 * 
 * @author Grant Gertsen
 */
public class Inventory implements Tickable {

	ArrayList<Item> stock;
	Floor floor;
	SimRandom randNums;
	int time;

	/**
	 * Create a list with all stocked items in it.
	 * 
	 * @author Grant Gertsen
	 */
	public Inventory(Floor floor, SimRandom nums) {
		stock = new ArrayList<Item>();
		this.floor = floor;
		randNums = nums;
		time = 0;
	}

	/**
	 * Add an item to the stock
	 * 
	 * @author Grant Gertsen
	 * @param item
	 */
	public void addItem(Item item) {
		System.out.println("Adding item " + item + " to shelf at " + item.shelf);
		stock.add(item);
	}

	/**
	 * Remove an item from the inventory and the shelf it is on.
	 * 
	 * @author Grant Gertsen
	 * @param item
	 */
	public Item removeItem(Item item) {
		Item removed = item.getShelf().removeItem(item);
		System.out.println("Inventory: Removing " + item + " from shelf at " + item.getShelf());
		stock.remove(item);
		return removed;
	}

	/**
	 * How many of an item is in stock
	 * 
	 * @author Grant Gertsen
	 * @param itemName
	 * @return how many of an item is in stock
	 */
	public int numberInStock(String itemName) {
		int count = 0;
		for (Item item : stock) {
			if (item.getItemName().equals(itemName)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Polymorphism of above method
	 * 
	 * @author Grant Gertsen
	 * @param serialNumber
	 *            the number you want to check
	 * @return the number of items of a given serial number in stock
	 */
	public int numberInStock(int serialNumber) {
		int count = 0;
		for (Item item : stock) {
			// System.out.println("Serial number is " + item.getItemName());
			if (item.getSerialNumber() == serialNumber) {
				count++;
			}
		}
		return count;
	}

	public int numberInStock(Item item) {
		return numberInStock(item.getSerialNumber());
	}

	/**
	 * Test method
	 * 
	 * @author Grant Gertsen
	 * @param index
	 *            the index which your item is located
	 * @return the item requested
	 */
	public Item getItemAtIndex(int index) {
		Item item = stock.get(index);
		return item;
	}

	/**
	 * Finds a shelf that has a given item on it (to be called by Orders I
	 * believe)
	 * 
	 * @author Grant Gertsen
	 * @param itemName
	 *            the name of the item you're looking for.
	 * @return a shelf where the item is.
	 */
	public Shelf findItem(String itemName) {
		for (Item item : stock) {
			if (item.getItemName().equals(itemName)) {
				return item.getShelf();
			}
		}
		return null; // item not found.
	}

	/**
	 * Finds what shelf a given item is on based off of its serial number.
	 * 
	 * @author Grant Gertsen
	 * @param itemNumber
	 * @return a shelf with the given item on it.
	 */
	public Shelf findItem(int itemNumber) {
		for (Item item : stock) {
			if (item.getSerialNumber() == itemNumber) {
				return item.getShelf();
			}
		}
		return null; // item not found.
	}

	/**
	 * Find what shelf an item object is on
	 * 
	 * @author Grant Gertsen
	 * @param item
	 * @return a shelf with the given item on it.
	 */
	public Shelf findItem(Item item) {
		return findItem(item.getSerialNumber());
	}

	@Override
	public void tick(int count) {

		time++;
	}
}
