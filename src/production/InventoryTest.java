package production;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class InventoryTest {
	
	/**
	 * Test to check the addItem() method
	 * @author Grant Gertsen
	 */
	@Test
	public void addItemTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf test = new Shelf(new Point(1,1));
		Item i = new Item("Hat", 234, test);
		items.addItem(i);
		//assertEquals(i,items.getItemAtIndex(0));
		assertEquals(i.getShelf(),test);
	}
	
	/**
	 * Testing "item in stock" method by item name
	 * @author Grant Gertsen
	 */
	@Test
	public void itemNameNumberInStockTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf test1 = new Shelf(new Point(1,1));
		Shelf test2 = new Shelf(new Point(1,5));
		Item i = new Item("Hat", 234, test2);
		Item j = new Item("Dog", 42, test1);
		Item h = new Item("Hat", 234, test2);
		items.addItem(i);
		items.addItem(j);
		items.addItem(h);
		assertEquals(2,items.numberInStock("Hat"));
	}
	
	/**
	 * Testing "item in stock" method by serial number
	 * @author Grant Gertsen
	 */
	@Test
	public void serialNumberNumberInStockTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf test1 = new Shelf(new Point(1,1));
		Shelf test2 = new Shelf(new Point(1,5));
		Item i = new Item("Hat", 234, test2);
		Item j = new Item("Dog", 42, test1);
		Item h = new Item("Hat", 234, test2);
		items.addItem(i);
		items.addItem(j);
		items.addItem(h);
		assertEquals(2,items.numberInStock(234));
	}
	
	/**
	 * Testing finding a shelf that the item is on
	 * @author Grant Gertsen
	 */
	@Test
	public void findItemTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf shelf1 = new Shelf(new Point(1,1));
		Shelf shelf2 = new Shelf(new Point(1,2));
		Shelf shelf3 = new Shelf(new Point(5,4));
		CatItem[] allItems = CatItem.catalog;
		Item item1 = new Item(allItems[234],shelf2);
		Item item2 = new Item(allItems[119],shelf1);
		items.addItem(item1); items.addItem(item2);
		Shelf temp = items.findItem(allItems[234].id);
		assertEquals(temp,shelf2);
	}
	
	@Test
	public void removeItemTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf shelf1 = new Shelf(new Point(1,1));
		Shelf shelf2 = new Shelf(new Point(1,2));
		Shelf shelf3 = new Shelf(new Point(5,4));
		CatItem[] allItems = CatItem.catalog;
		Item item1 = new Item(allItems[nums.nextInt(100)],shelf2);
		Item item2 = new Item(allItems[nums.nextInt(100)],shelf1);
		items.addItem(item1); items.addItem(item2);
		shelf2.showItems();
		Item removedItem = items.removeItem(item1);
		assertEquals(item1, removedItem);
		
	}

}