package production;

//import java.util.ArrayList;
import java.util.List;
/**
 * @author Casey Kolodziejczyk
 * This is the Class for Orders that will need to be satisfied
 */
public class Order {
	List<Item> order;  // current order to fulfill
	//List<OrderItem> bin = new ArrayList<OrderItem>(); // bin being filled (this will be another object from belt)
	Bin bin = new Bin();
	String address;
	String status;
	boolean isFilled; 
	int orderNumber;
	
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param items
	 * @param addr
	 * @param num
	 * Creates an Order object using these three parameters
	 */
	public Order(List<Item> items, String addr, int num) {
		isFilled = false;
		order = items;
		address = addr;
		orderNumber = num;
	}
	/**
	 * @author Casey Kolodziejczyk
	 * @param items
	 * @param addr
	 * Creates an Order Object without the number parameter
	 */
	public Order(List<Item> items, String addr) {
		isFilled = false;
		order = items;
		address = addr;
	}
	
	/**
	 * @author Ted Herman
	 * @return Boolean
	 *  Returns whether or not the Order has been filled 
	 */
	public boolean filled() { return isFilled; }
	  
	/**
	 * @author Ted Herman
	 * Changes the value of isFilled from false to true
	 */
	public void setFilled() { isFilled = true; }
	
	
	// Need an method that asks inventory what shelf the Item is on
	/**
	 * @author Casey Kolodziejczyk
	 * @param Item Object
	 * @return Shelf location for that Objects
	 * Takes an Item an finds out which shelf that item is located on
	 *		Or should it ask Inventory where that is? 
	 */
	public Point requestLocation(Item needed) {
		Shelf itemLoc = needed.getShelf();
		return itemLoc.homeLocation;
	   }

	
	// Also needs a method that tells the robot the shelf value from above 
	
	/**
	 * public void tellRobotGet(Shelf needed){
	 * 
	 * }
	
	  - Use the location above and give it to the robot to retreive the shelf
	 
	 * 
	 * 
	 */
		
	/**
	 * Also maybe one for get new bin
	 */
	
	
	
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param product  (An Item)
	 * For right now, It simply adds an whatever Item listed to the bin
	 * 
	 * When this is done, it will take the Item requested from the shelf that is there,
	 * turns the item into a OrderItem type which can be tracked whether or not it is in the bin
	 * and then add that item to the bin
	 */
	
	/**
	 
	public OrderItem getItemNeeded(Item product) {
		// Take item from Shelf 
		// If shelf.location = not here. Then wait
		
		OrderItem orderProd = new OrderItem(product);
		
		return orderProd;
		
		//bin.add(orderProd);
		//orderProd.setFilled();
	}
	*/
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param Item
	 * @param Shelf
	 * @return OrderItem from Shelf
	 * Takes an shelf and an Item and then takes the item
	 * off of the shelf and turns it into an OrderItem
	 */
	public OrderItem getItemNeeded(Item product, Shelf location) {
		Item itemTook = location.removeItem(product);
		OrderItem orderProd = new OrderItem(itemTook);
		
		return orderProd;
	}
	
	/** 
	 * @author Casey Kolodziejczyk
	 * @return the bin
	 * This simply returns the bin in its current state, could be used to check if enough items are in it.
	 */
	public Bin getItems() {
		// Return the Bin? or the items of the bin? 
		return bin;
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param OrderItem
	 * Updates the status of the Item, and adds that item to the bin.
	 */
	public void addItem(OrderItem added) {
		System.out.println("Picker puts " + added + " in bin for order: " + address + "\n");
		added.setFilled();
		bin.itemAdd(added);
	}
	/**
	 * @author Casey Kolodziejczyk
	 * @param OrderItem
	 * Simply removes an item from the bin and updates variables of the item.
	 */
	public void removeItem(OrderItem removed) {
		System.out.println("Picker takes " + removed + " out of bin for order: " + address);
		removed.inBin = false;
		bin.itemRemove(removed);
	}
	// If bin and order are the Same =  true, else false. 
	/**
	 * @author Casey Kolodziejczyk
	 * @return boolean
	 * Compares the bin to the order variable, If they both contain similar items,
	 * return true. else false 
	 */
	public boolean compareOrder() {
	    if (order.size() != bin.binSize()) return false;
	    for (int i=0; i<order.size(); i++) {
	        if (order.get(i).getItemName() != bin.contents.get(i).getItemName()) return false;
	    }
	    return true;
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * @return boolean
	 * If the Bin contains the exact same items as the Order,
	 * Set the status of the order to fulfilled
	 */
	public boolean orderFulfilled() {		
		if (compareOrder()) {
			setFilled();
			System.out.println("Order : [" + address + "] has been completed! \n");
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * @author Casey Kolodziejczyk
	 * @param string
	 * update the status of the Order to the string inserted
	 */
	public void updateStatus(String update) {
		status = update;
	}
	
	/**
	 *@author Casey Kolodziejczyk
	 * This method runs a loop of the entire order and adds all of the items needed to be bin 
	 */
	
	
	// Maybe when Shelf is here, start to Complete order
	
	public void completeOrder() {
		System.out.println("Picker starts to complete new order \n");
		while (orderFulfilled() != true) {
			for (int i = 0; i < order.size(); i++) {		
				addItem(getItemNeeded(order.get(i), order.get(i).getShelf()));
			}
		}
	}
	
	
	/**
	 * @author Casey Kolodziejczyk
	 * For testing and printing out Order objects
	 */
	 @Override
	public String toString() {
	      	return ("" + order + "");
	   }
}