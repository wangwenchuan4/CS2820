package production;

import java.util.Arrays;
import java.util.LinkedList;


	/**
	 * @author Casey Kolodziejczyk
	 * This is the OrderControl class where all orders will be stored
	 * methods will allow for order to be added, removed, and completed 
	 * Might need to change allOrders from a hashmap, but not at the moment
	 */
public class OrderControl implements Tickable, Picker {
	private Inventory I;
	private RobotScheduler R;
	private Belt B;
	private SimRandom randomsource;
	private Order currentorder; 
	private Bin currentbin;
	private Item neededitem;
	
	LinkedList<Order> allOrders;  
	String status;
	int time;  
	
	/**
	 * @author Ted Herman, Casey Kolodziejczyk
	 * @param Inventory component, needed to create sensible
	 * new Order to add to queue of work to do (for testing,
	 * it just creates a few initial orders)
	 * @param rand is a SimRandom, for predictable randomness
	 * 
	 * Decided to use this over mine because it was a better version of mine
	 */
	public OrderControl(Inventory I, Belt B, RobotScheduler R, SimRandom rand) {
		this.I = I; 
		this.R = R; 
		this.B = B; 
		randomsource = rand;
		allOrders = new LinkedList<Order>();
		currentorder = null;
		currentbin = null;
		neededitem = null;
	    }	
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param orderNum
	 * @param newest
	 * Adds an order to the List of Orders
	 */
	public void addOrder(Order newest) {
		allOrders.add(newest);
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param index
	 * Removes an order from the List of Orders
	 */
	public void removeOrder(int index) {
		allOrders.remove(index);
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param updated
	 * @param statusUpdate
	 * Updates the status of an individual order
	 * (Might just need to change it to updated.Isfilled())
	 */ 
	public void updateOrder(Order updated, String statusUpdate) {
		updated.status = statusUpdate;
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param orderChecked (Order Object)
	 * @return the status of an individual order
	 */
	public String orderStatus(Order orderChecked) {
		return orderChecked.status;
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param canceled (Order to be canceled)
	 * Changes the status of an order to "canceled"
	 */
	public void cancelOrder(Order canceled) {
		canceled.updateStatus("Canceled");
		allOrders.remove(canceled);
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * @return Current status of OrderControl
	 * Might not be needed 
	 */
	public String getSystemStatus() {
		return status;
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param update (status of OrderControl)
	 * Changes the value of status
	 * Might not be needed
	 */ 
	public void updateSystemStatus(String update){
		status = update;
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * Solves the first order that is within the linked list
	 * removes that order from the list once it is satisfied
	 */
	public void completeNextOrder(){
		Order currentOrder = allOrders.getFirst();
		startOrderProcess(currentOrder);
		allOrders.removeFirst();
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * @param Item
	 * @return OrderItem from Shelf
	 * Takes an shelf and an Item and then takes the item
	 * off of the shelf and turns it into an OrderItem
	 * 
	 * Works as part of the previous solution for Orders along with startOrderProcess
	 */
	public OrderItem getItemNeeded(Item product) { 
		System.out.println("\nOrders tells robot to get shelf at point: " + I.findItem(product) );
		//R.requestShelf(I.findItem(product) , (Picker)this);  // Stopped working
		System.out.println("\nPicker removes " + product + " from shelf\n");
		Item itemTook = I.removeItem(product);	
		OrderItem orderProd = new OrderItem(itemTook);
		return orderProd;
	}
	/**
	 * @author Casey Kolodziejczyk
	 * @param an Order
	 * 
	 * My original version for handling the job for orders and fulfilling orders
	 *  	Recent interations of everyones code  however, most of it
	 *  	has been readjusted
	 */
	
	public void startOrderProcess(Order todo) {
		currentorder = todo;
		System.out.println("\nPicker starts to complete new order ");
		System.out.println("\nPicker grabs a new bin for the order \n");
		currentorder.getNewBin();
		
		while (todo.orderFulfilled() != true) {
			for (int i = 0; i < todo.order.size(); i++) {		
				todo.addItem(getItemNeeded(todo.order.get(i)));
			}
		}
		if (todo.orderFulfilled() == true){
			System.out.println("Order :\n" + todo.address + " has been completed!");
			System.out.println("\nPicker notifies belt that the Bin is completed");
			//B.addBin(currentbin);
		}
	}
	
	/**
	 * @author Casey Kolodziejczyk
	 * @return the number of orders left in OrderControl
	 * 
	 * Simply tells the number of orders.
	 * If the number is 0, it will generate a new random order
	 */
	public int orderAmount() {
		System.out.println("There are " + allOrders.size() + " orders to be fulfilled");
		if (allOrders.size() == 0){
			makeOrder();
		}
		
		return allOrders.size();
	}

	/**
	 * @author Ted Herman, Casey Kolodziejczyk
	 * 
	 * The location where Orders does the majority of the work
	 * 
	 * Entire Process consists of: Starting Order, Grabbing new bin, Find item shelf,
	 * Request robot grab shelf, Remove item from shelf*, Place item in bin*, 
	 * Request robot return shelf*, Complete Order, Place bin on Belt.
	 * 
	 * (Steps with * at end are handled within method notify)
	 * 
	 */
	public void tick(int count) {
	
	// special case: make currentbin variable null if an order was
	// just finished and the belt moved it away
	if (currentbin != null && currentbin.isFinished() && B.binAvailable()){
		currentbin = null; 
	}
	
	if (allOrders.size() < 1) { makeOrder(); return; }
	
	if (currentorder == null) {
	  currentorder = allOrders.pop(); // start work
	  System.out.println("\nPicker starts new order");
	  System.out.println("Order information: \n\n" + currentorder.address + "\n" );
	  System.out.println("Order consists of: " + currentorder + "\n");
	  }
			

	// at this point, Orders is acting the Picker role, so it has 
	// to look at the current order and decide what to do
	if (currentbin == null && B.binAvailable()) {
	  System.out.println("Picker grabs a new bin from Belt");
	  currentbin = B.getBin();
	  }
	if (currentbin == null) return;  // try again in another tick to get bin
	
	if (currentorder.isFilled) {
	   // we have a bin, we have a filled order, so finish up this bin
	   currentbin.order = currentorder;
	   currentbin.setFinished();
	   System.out.println("\nOrder has been finished!");
	   currentorder = null;
	   System.out.println("Picker notifies belt bin is complete");
	   //B.addBin(currentbin); // Picker then places the Bin on the Belt
	   return;  // done! 
	   }
		
	// have we already requested a needed item? if yes, just wait for a 
	// later tick, or when the Robot notifies of arrival
	if (neededitem != null) return;
	
	// at this point in code, there is a currentorder not yet filled
	OrderItem nextitem = null;
	for (OrderItem item: currentorder.order) {
	  if (item.inBin) continue;
	  nextitem = item; 
	  break;  // found an item to fetch
	  }
	
	if (nextitem == null) { // all items are in bin, so this order is done
	  currentorder.isFilled = true;
	  return;   // let a later tick take care of the bin
	  }
	
	// try to find the item in the warehouse, and ask for a robot
	if (!R.robotAvailable()) return;  // wait for a later tick
	
	Shelf s = I.findItem(nextitem);
	
	if (s == null) return;  // item not in warehouse, Inventory should replenish
	neededitem = nextitem;  // remember the item needed when Robot arrives
	
	System.out.println("Picker requests robot grabs shelf: " + s + "\n");
	
	R.requestShelf(s,(Picker)this);  // pretend to be the picker
	return;
	   }

	/**
	  * @author Ted Herman, Casey Kolodziejczyk
	  * @param Robot
	  * @param Shelf 
	  * 
	  * Picker event that consists of the steps within tick that are:
	  * Remove Item from shelf, Place item in bin, Tell Robot to return shelf
	  */
	public void notify(Robot r, Shelf s) { 
	// should be code here to remove desired Item OrderItem from Shelf s
	// (Inventory has a method to do that)
	// mark that Item as being checked off in the Order
	// and if this is the last Item needed, tell Belt 
	// that a Bin is done.
	Item[] atpicker = I.onShelf(s);
	int found = -1;
	 
	for (int i=0;i<atpicker.length;i++) {
	  if (atpicker[i].equals(neededitem)) found = i;
	  }
	
	assert found > -1;
	System.out.println("\nPicker takes item off of shelf");
	I.removeItem(neededitem);		// Item removed from shelf
	
	for (OrderItem item: currentorder.order) {
	  if (!item.inBin && item.equals(neededitem)) {  // take item from shelf
		System.out.println("Picker places " + item + " in Bin");
		currentbin.itemAdd(item);  // Adds item to bin 
		System.out.println("Bin now consists of :" + currentbin);
		item.inBin = true;  // put item in bin  
		break;
	    }
	  }
	neededitem = null;   // no longer need this item
	System.out.println("\nPicker tells robot to return shelf");
	R.returnShelf(r);  // tell Robot to return Shelf back to its home
	};
	
	/**
	   * @author Ted Herman
	   * get a random new order and add it to the queue; a fancier
	   * implementation of this would perhaps delay actually putting
	   * the new order into the queue for some number of ticks, to 
	   * simulate how orders might come in sporadically over time; 
	   * and perhaps multiple new orders could be created in one call  
	   */
	  private void makeOrder() {
		System.out.println("Generating a new order!");
		allOrders.add(getRandomOrder());
	    };
	  
	  /**
	   * @author Ted Herman
	   * creates a random Order
	   */
	  public Order getRandomOrder() {
		String addr = new Address(randomsource).randomAddress();
		OrderItem[] orderitems = new OrderItem[1+randomsource.nextInt(2)];
		for (int i=0;i<orderitems.length;i++) {
		  orderitems[i] = new OrderItem(I.randomItem());
		  }
		return new Order(Arrays.asList(orderitems),addr);
	    }
	  }
