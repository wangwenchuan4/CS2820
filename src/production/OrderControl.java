package production;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

	/**
	 * @author Casey Kolodziejczyk
	 * This is the OrderControl class where all orders will be stored
	 * methods will allow for order to be added, removed, and completed 
	 * Might need to change allOrders from a hashmap, but not at the moment
	 */
public class OrderControl implements Tickable, Picker {
	//HashMap<Integer, Order> allOrders;// order # , List of Items  
	//LinkedList<Order> allOrders;
	
	private Inventory I;
	private RobotScheduler R;
	private Belt B;
	 LinkedList<Order> allOrders;
	private SimRandom randomsource;
	private Order currentorder; 
	private Bin currentbin;
	private Item neededitem;
	  
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
		this.I = I; // so we can later call upon Inventory methods
		this.R = R; // so that later, we can call RobotScheduler
		this.B = B; // we will need the Belt
		randomsource = rand;
		allOrders = new LinkedList<Order>();
		currentorder = null;
		currentbin = null;
		neededitem = null;
		
		for (int i=0;i<3;i++) {
		  allOrders.addLast(getRandomOrder());
		  }
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
	 * @param Shelf
	 * @return OrderItem from Shelf
	 * Takes an shelf and an Item and then takes the item
	 * off of the shelf and turns it into an OrderItem
	 */
	public OrderItem getItemNeeded(Item product) { 
		System.out.println("\nOrders tells robot to get shelf at point: " + I.findItem(product) );
		//R.requestShelf(I.findItem(product) , (Picker)this);
		System.out.println("\nPicker removes " + product + " from shelf\n");
		Item itemTook = I.removeItem(product);	
		OrderItem orderProd = new OrderItem(itemTook);
		return orderProd;
	}
	
	
	public void startOrderProcess(Order todo) {
		currentorder = todo;
		System.out.println("\nPicker starts to complete new order ");
		System.out.println("\nPicker grabs a new bin for the order \n");
		B.getBin();
		
		while (todo.orderFulfilled() != true) {
			for (int i = 0; i < todo.order.size(); i++) {		
				todo.addItem(getItemNeeded(todo.order.get(i))); //, order.get(i).getShelf()));
				//addItem(getItemNeeded(order.get(i), I.findItem(order.get(i))));
							/// Call findItem()v 
			}
		}
		if (todo.orderFulfilled() == true){
			System.out.println("Order :\n" + todo.address + " has been completed!");
			System.out.println("\nPicker now places the bin for on the belt");
			B.addBin(currentbin);
		}
	}

	public int orderAmount() {
		System.out.println("There are " + allOrders.size() + " orders to be fulfilled");
		return allOrders.size();
	}

	/**
	 * @author Ted Herman
	 * 
	 * This tick method would be where Orders does the 
	 * real work (see design README)
	 * 
	 */
	public void tick(int count) {
	/**
	 * What tick should do (vague description)
	 * 1. if there is no Order in orderqueue, then
	 *    maybe it's time to generate a random new
	 *    Order and put it in the queue? Another idea
	 *    would be to delay putting a new Order in the 
	 *    queue (postpone that to a later tick)
	 */
	
		
	/**	
	 * 2. if no Order being worked on, and
	 *    there is an Order in orderqueue, ask Belt 
	 *    if a new Bin is available - a "no" from the 
	 *    Belt would mean Orders has to wait - so ignore
	 *    this tick. 
	 *
	 */
		
	/**	
	 * 3. if Belt says a new Bin can be started, and 
	 *    there's an Order in the orderqueue, time to 
	 *    start working (go to next step in this same tick).
	 *    Probably some variables/fields will be changed
	 *    to indicate that an Order is in progress, being
	 *    worked on by the Picker.
	 */
		
	/**	
	 * 4. if an Order is being worked on (need additional
	 *    variable/fields to know this), then check if 
	 *    it has any Items that need fetching.
	 */
		
	/**	
	 * 5. if an Item needs fetching, do pretty much what
	 *    is done in TestRobotScheduler, but when notify()
	 *    of the Picker interface is called (see below), then
	 *    get take the desired OrderItem from the Shelf that
	 *    the Robot brought, mark that OrderItem as being
	 *    in the Bin.
	 */
		
	 /** 6. if all OrderItems are filled from the current 
	 *    Order being worked on, then tell the Belt that 
	 *    the Bin at the Picker is ready to go. 
	 *    
	 * Special Note on Step 5. In MockRobotScheduler, it's 
	 * assumed that whatever an Order needs, that OrderItem
	 * will be on some Shelf. A more realistic idea is to 
	 * attempt to find the OrderItem on a Shelf, but if it 
	 * cannot be found, tell Inventory (so Inventory would need
	 * to have a new method for that). Then Orders is stuck, 
	 * waiting for the desired OrderItem to be present, checking
	 * that in each tick(). Eventually, after Inventory has done
	 * its job, Orders will find a Shelf with the needed OrderItem
	 * and continue.
	 */
	
	// special case: make currentbin variable null if an order was
	// just finished and the belt moved it away
	if (currentbin != null && currentbin.isFinished() && B.binAvailable()){
		currentbin = null; 
	}
	
	if (allOrders.size() < 1) { makeOrder(); return; }
	
	if (currentorder == null) {
	  currentorder = allOrders.pop(); // start work
	  System.out.println("Picker starts new order");
	  }
			

	// at this point, Orders is acting the Picker role, so it has 
	// to look at the current order and decide what to do
	if (currentbin == null && B.binAvailable()) {
	  System.out.println("Picker got a new bin");
	  currentbin = B.getBin();
	  }
	if (currentbin == null) return;  // try again in another tick to get bin
	
	if (currentorder.isFilled) {
	   // we have a bin, we have a filled order, so finish up this bin
	   currentbin.order = currentorder;
	   currentbin.setFinished();
	   currentorder = null;
	   return;  // done! The Bin part will take it away from here.
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
	
	System.out.println("\nCurrent Shelf requesting: " + s);
	System.out.println("\nCurrent Shelf home location"  + s.getHomeLocation());
	System.out.println("\nCurrent Item shelf: "  + I.findItem(neededitem));
	
	R.requestShelf(s,(Picker)this);  // pretend to be the picker
	return;
	   }

	/**
	  * @author Ted Herman
	  * Picker event notify(robot), not finished.
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
	Item got = I.removeItem(neededitem);		// Item removed from shelf
	
	for (OrderItem item: currentorder.order) {
	  if (!item.inBin && item.equals(neededitem)) {  // take item from shelf
		System.out.println("Picker item put in bin");
		
		currentbin.itemAdd(item);  // Adds item to bin (or should it add got?)
		
		item.inBin = true;  // put item in bin  
		break;
	    }
	  }
	neededitem = null;   // no longer need this item
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

	

