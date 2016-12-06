package production;

//import java.util.ArrayList;
import java.util.List;
/**
 * @author Casey Kolodziejczyk
 * This is the Class for Orders that will need to be satisfied
 */
public class Order implements Picker {
	
	private Inventory I;
	private RobotScheduler R;
	private Belt B;
	
	List<OrderItem> order;  // current order to fulfill
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
	public Order(List<OrderItem> items, String addr, int num) {
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
	public Order(List<OrderItem> items, String addr) {
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
	
	/**
	 * @author Casey Kolodziejczyk
	 * This simply creates a new bin. Will be called 
	 * each time a new order is started.
	 */
	public void getNewBin(){
		bin = new Bin();
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
		System.out.println("\nPicker puts " + added + " in bin for order: \n" + address);
		added.setFilled();
		bin.itemAdd(added);
		System.out.println("\nBin now consists of: " + bin.contents + "\n");
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
			//System.out.println("Order : [" + address + "] has been completed! \n");
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
	 * @author Ted Herman
	 * @param Robot
	 * @param Shelf
	 * Used to notify other sections when the robot has 
	 * arrived to the picker with the shelf needed
	 */
	@Override
	public void notify(Robot r, Shelf s) {
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