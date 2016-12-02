package production;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class InventoryTest {

	/**
	 * Test to check the addItem() method
	 * 
	 * @author Grant Gertsen
	 */
	@Test
	public void addItemTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf test = new Shelf(new Point(1, 1));
		Item i = new Item("Hat", 234, test);
		items.addItem(i);
		// assertEquals(i,items.getItemAtIndex(0));
		assertEquals(i.getShelf(), test);
	}

	/**
	 * Testing "item in stock" method by item name
	 * 
	 * @author Grant Gertsen
	 */
	@Test
	public void itemNameNumberInStockTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf test1 = new Shelf(new Point(1, 1));
		Shelf test2 = new Shelf(new Point(1, 5));
		Item i = new Item("Hat", 234, test2);
		Item j = new Item("Dog", 42, test1);
		Item h = new Item("Hat", 234, test2);
		items.addItem(i);
		items.addItem(j);
		items.addItem(h);
		assertEquals(2, items.numberInStock("Hat"));
	}

	/**
	 * Testing "item in stock" method by serial number
	 * 
	 * @author Grant Gertsen
	 */
	@Test
	public void serialNumberNumberInStockTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf test1 = new Shelf(new Point(1, 1));
		Shelf test2 = new Shelf(new Point(1, 5));
		Item i = new Item("Hat", 234, test2);
		Item j = new Item("Dog", 42, test1);
		Item h = new Item("Hat", 234, test2);
		items.addItem(i);
		items.addItem(j);
		items.addItem(h);
		assertEquals(2, items.numberInStock(234));
	}

	/**
	 * Testing finding a shelf that the item is on
	 * 
	 * @author Grant Gertsen I need to find a better way to test this now...
	 */
	// @Test
	// public void findItemTest() {
	// SimRandom nums = new SimRandom();
	// Floor floor = new MockFloor(nums);
	// Inventory items = new Inventory(floor, nums);
	// Shelf shelf1 = new Shelf(new Point(1,1));
	// Shelf shelf2 = new Shelf(new Point(1,2));
	// Shelf shelf3 = new Shelf(new Point(5,4));
	// CatItem[] allItems = CatItem.catalog;
	// Item item1 = new Item(allItems[234],shelf2);
	// Item item2 = new Item(allItems[119],shelf1);
	// items.addItem(item1); items.addItem(item2);
	// Shelf temp = items.findItem(allItems[234].id);
	// assertEquals(temp,shelf2);
	// }
	/**
	 * Testing removing items from the Inventory class
	 * 
	 * @author Grant Gertsen
	 */
	@Test
	public void removeItemTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf shelf1 = new Shelf(new Point(1, 1));
		Shelf shelf2 = new Shelf(new Point(1, 2));
		Shelf shelf3 = new Shelf(new Point(5, 4));
		CatItem[] allItems = CatItem.catalog;
		Item item1 = new Item(allItems[nums.nextInt(100)], shelf2);
		Item item2 = new Item(allItems[nums.nextInt(100)], shelf2);
		items.addItem(item1);
		items.addItem(item2);
		Item removedItem = items.removeItem(item1);
		shelf2.showItems();
		assertEquals(item1, removedItem);
	}

	/**
	 * Testing the number of items in stock
	 * 
	 * @author Grant Gertsen
	 */
	@Test
	public void stockAmountTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf shelf1 = new Shelf(new Point(1, 1));
		Shelf shelf2 = new Shelf(new Point(1, 2));
		Shelf shelf3 = new Shelf(new Point(5, 4));
		CatItem[] allItems = CatItem.catalog;
		Item item1 = new Item(allItems[nums.nextInt(100)], shelf2);
		Item item2 = new Item(allItems[nums.nextInt(100)], shelf2);
		Item item3 = new Item(allItems[nums.nextInt(100)], shelf3);
		Item item4 = new Item(allItems[nums.nextInt(100)], shelf1);
		items.addItem(item1);
		items.addItem(item2);
		items.addItem(item3);
		items.addItem(item4);
		items.removeItem(item2);
		items.addItem(item4);
		items.addItem(item4);
		int number = items.stockAmount();

		assertEquals(number, 5 + allItems.length);
	}
	
	@Test
	public void onShelfTest() {
		System.out.println("ON SHELF TEST");
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf shelf = items.getStock()[0].getShelf();
		Item[] shelfItems = items.onShelf(shelf);
		System.out.println("FAKE LIST OF ITEMS");
		for(Item i : shelfItems) {
			System.out.println(i);
		}
		shelf.showItems();
	}

}