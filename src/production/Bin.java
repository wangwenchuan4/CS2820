package production;

import java.util.ArrayList;
import java.util.List;

public class Bin {
	
	/**
	 * Borrowed from Prof. Herman -- May chanage implementation as needed.
	 * More of a placeholder for the moment.
	 */
	  Order order;  // ready to go on the Belt
	  
	  List<OrderItem> contents = new ArrayList<OrderItem>(); // Arraylist of Items within the bin.
	  boolean finished;  // initially false, becomes true when ready to go
	  public Bin() {
		  order = null;
		  finished = false;
	  }
	  /**
	   * @author Casey Kolodziejczyk
	   * @param added
	   * Adds an Item to the List in Bin Object
	   */
	  public void itemAdd(OrderItem added) { contents.add(added); }
	  /**
	   * @author Casey Kolodziejczyk
	   * @param removed
	   * Removes an Item from the List in Bin Object
	   */
	  
	  public void itemRemove(OrderItem removed) { contents.remove(removed); }
	  
	  /**
	   * @author Casey Kolodziejczyk
	   * @return size of bin 
	   */
	  public int binSize() { return contents.size();}
	  
	  public boolean isFinished() { return finished; }
	  
	  public void setFinished() { finished = true; }
	  
	  public Order getOrder() { return order; }
	  
	  public void setOrder(Order o) { order = o; }
	  
	  /**
		 * @author Casey Kolodziejczyk
		 * For testing and printing out bin objects
		 */
		 @Override
		public String toString() {
		      	return ("" + contents + "");
		   }
	  
	}
