package production;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShelfTest {

//	@Test
//	public void addItemTest() {
//		SimRandom nums = new SimRandom();
//		Floor floor = new MockFloor(nums);
//		Inventory items = new Inventory(floor, nums);
//		Shelf shelf1 = new Shelf(new Point(1,1));
//		Shelf shelf2 = new Shelf(new Point(1,2));
//		Shelf shelf3 = new Shelf(new Point(5,4));
//		CatItem[] allItems = CatItem.catalog;
//		Item item1 = new Item(allItems[234]);
//		Item item2 = new Item(allItems[119]);
//		items.addItem(item1); items.addItem(item2);
//		shelf1.addToShelf(item1);
//		shelf1.showItems();
//		assertEquals(true,shelf1.hasItem(item1));
//	}
	
	@Test
	public void removeItemTest() {
		SimRandom nums = new SimRandom();
		Floor floor = new MockFloor(nums);
		Inventory items = new Inventory(floor, nums);
		Shelf shelf1 = new Shelf(new Point(1,1));
		Shelf shelf2 = new Shelf(new Point(1,2));
		Shelf shelf3 = new Shelf(new Point(5,4));
		CatItem[] allItems = CatItem.catalog;
		Item item1 = new Item(allItems[234]);
		Item item2 = new Item(allItems[119]);
		items.addItem(item1); items.addItem(item2);
		shelf1.addToShelf(item1);
		shelf1.showItems();
		Item fake = new Item(allItems[234],shelf1);
		Item copy = shelf1.removeItem(fake);
		System.out.println("Item 1 = " + item1);
		System.out.println("REMOVED ITEM IS: " + copy);
		assertEquals(item1,copy);
	}

}
