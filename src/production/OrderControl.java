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
public class OrderControl implements Tickable {
	//HashMap<Integer, Order> allOrders;// order # , List of Items  
	//LinkedList<Order> allOrders;
	
	private Inventory I;
	private RobotScheduler R;
	private Belt B;
	private LinkedList<Order> allOrders;
	private SimRandom randomsource;
	private Order currentorder; 
	private Bin currentbin;
	private Item neededitem;
	  
	String status;
	int time;  
	
	/**
	 * @author Ted Herman
	 * @param Inventory component, needed to create sensible
	 * new Order to add to queue of work to do (for testing,
	 * it just creates a few initial orders)
	 * @param rand is a SimRandom, for predictable randomness
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
	 * Creates OrderControl Object
	 * But at this moment, Simply creates two orders of items
	 */
	public OrderControl(){
		
		
		allOrders = new LinkedList<Order>();
		//allOrders.add(getRandomOrder());
		//allOrders.add(getRandomOrder());
		
		
		Point Point1 = new Point(1,2);
		Point Point2 = new Point(3,4);
		
		Shelf Shelf1 = new Shelf(Point1);
		Shelf Shelf2 = new Shelf(Point2);
		
		OrderItem testItem1 = new OrderItem( "Baseball", 1, Shelf1);
		OrderItem testItem2 = new OrderItem( "Glove", 2, Shelf1);
		OrderItem testItem3 = new OrderItem( "Bat", 3, Shelf2);
		
		OrderItem testItem4 = new OrderItem( "Hockey Stick", 4, Shelf2);
		OrderItem testItem5 = new OrderItem( "Puck", 5, Shelf2);
				
		List<OrderItem> testItems1 = new ArrayList<OrderItem>();
		
		testItems1.add(testItem1);
		testItems1.add(testItem2);
		testItems1.add(testItem3);
		
		List<OrderItem> testItems2 = new ArrayList<OrderItem>();
		
		testItems2.add(testItem4);
		testItems2.add(testItem5);
		
		Order TestOrder1 = new Order(testItems1, "Maclean Hall", 1);
				
		Order TestOrder2 = new Order(testItems2, "Herman's House", 2);
		
		allOrders.add(TestOrder1);
		allOrders.add(TestOrder2);
		
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
		currentOrder.completeOrder();
		allOrders.removeFirst();
	}
	
	public int orderAmount() {
		System.out.println("There are " + allOrders.size() + " orders to be fulfilled");
		return allOrders.size();
	}
	
	//@Override
	//public void tick(int count) {
	//	this.orderAmount();
	//	time++;
	//}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
		
		
		
		
		/**
		 * These are methonds I need from others in order to be able to attempt and see if tick works
		 *  I.onShelf(s) -> Needs a method that take a shelf and gives out all items on it.
		 *	R.returnShelf(robot) ->
		 *	R.requestShelf(s,(Picker)this); -> requests a shelf and then picker.
		 *	R.robotAvailable()
		 *	B.binAvailable())
		 *	B.getBin()	
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

	/**
	 * 
	 * @author Ted Herman
	 * A local class just to supply addresses
	 * for orders in the Orders component
	 *
	 */

	class Address {
		
	  SimRandom SR;
	  
	  /**
	   * @author Ted Herman
	   * @param SR is SimRandom object, so that all the random
	   * choices by methods of Address will be predictably random
	   */
	  public Address(SimRandom SR) {
		this.SR = SR;
	    }

	  /**
	   * @author Ted Herman
	   * @param R is a SimRandom for predictability in randomness
	   * @return String containing a random address for an order
	   */
	  public String randomAddress() {
		String FirstName = randomFirstName();
		String LastName = randomLastName();
		String StreetNumber = new Integer(randomStreetNumber()).toString();
		String StreetName = randomStreetName();
		String City = randomCity();
		String State = randomState();
		String ZipCode = randomZip();
		String Address = FirstName + " " +
		  LastName + "\n" + StreetNumber + " " +
		  StreetName + "\n" + City + " " + State + ZipCode;
		return Address;
	    }
	  
	  /**
	   * @author Ted Herman
	   * @return a string containing a random street name
	   */
	  private String randomStreetName() {
		final String[] baseNames = {"Park Street",
				"Main Street", "Washington Boulevard",
				"Third Street", "Park Road",
				"Maple Street", "Hill Road"};
		return baseNames[SR.nextInt(baseNames.length)];
	    }
	  
	  /**
	   * @author Ted Herman
	   * @return an integer in the range [1,999] for street address
	   */
	  private int randomStreetNumber () {
		return 1+SR.nextInt(998);
	    }
	  
	  /**
	   * @author Ted Herman
	   * @return a random first name for an address
	   */
	  private String randomFirstName() {
		final String[] baseFirstNames = {"Dakota", "Emma",
				"Julian", "Nigella", "Will", "Asti", "Lee",
				"Pat", "Mavis", "Jerome", "Lilly", "Tess"};
		return baseFirstNames[SR.nextInt(baseFirstNames.length)];
		}
	  
	  /**
	   * @author Ted Herman
	   * @return a random last name for an address
	   */
	  private String randomLastName() {
		final String[] baseLastNames = {"Parker","Mason",
					"Smith","Wright","Jefferson","Iqbal",
					"Owens","Lafleur","Metselen","Vinceroy",
					"Saville","Troitski","Andrews"};
		return baseLastNames[SR.nextInt(baseLastNames.length)];
	    }
	  
	  /**
	   * @author Ted Herman
	   * @return a random city name
	   */
	  private String randomState() {
		final String[] baseState = {"IA","NE","MO",
					"IL","KS","MN","SD","AR","OK","TX"};
		return baseState[SR.nextInt(baseState.length)];
	    }
	  
	  /**
	   * @author Ted Herman
	   * @return a random state code (two letters)
	   */
	  private String randomCity() {
		final String[] baseCity = {"Springfield","Clinton",
					"Madison","Franklin","Chester","Marion",
					"Greenville","Salem","Anytown","Hope"};
		return baseCity[SR.nextInt(baseCity.length)];
	    }
	  
	  /**
	   * @author Ted Herman
	   * @return a random state code (two letters)
	   */
	  private String randomZip() {
	    String ZipCode = "";
	    for (int i=0; i<6; i++) 
	      ZipCode += "0123456789".charAt(SR.nextInt(10));
	    return ZipCode;
	  }
	}

