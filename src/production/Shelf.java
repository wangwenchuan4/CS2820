package production;

public class Shelf {

//	static final int maxItems = 5;

	Point homeLocation;
	Point currentLocation;
	boolean resting; // true if at home, not on robot
//	List<Item> shelfStock;
	

	/**
	 * Initialize shelf at a given point.
	 * 
	 * @author Grant Gertsen
	 * @param home
	 */
	public Shelf(Point home) {
		homeLocation = home;
		currentLocation = home;
		resting = true;
		//shelfStock = new ArrayList<Item>(maxItems);
	}


	/**
	 * Gets current location
	 * 
	 * @author Grant Gertsen
	 * @return shelf location
	 */
	public Point getLocation() {
		return currentLocation;
	}

	/**
	 * Where the shelf was initialized at
	 * 
	 * @author Grant Gertsen
	 * @return the home location
	 */
	public Point getHomeLocation() {
		return homeLocation;
	}
		
	public void pickup() {
		resting = false;
	}

	public void putdown() {
		resting = true;
	}
	/**
	 * needed a method to ask if the shelf is resting for RobotScheduler
	 * @author Andrew Marburg
	 * @return true if shelf is resting
	 */
	public boolean isResting(){
		return resting;
	}
	/**
	 * Prints out all items on the shelf
	 * @author Grant Gertsen
	 */

	/**
	 * @author Casey Kolodziejczyk Just overrides toString for testing purposes
	 */
	@Override
	public String toString() {
		return ("SHELF: " + homeLocation + "");
	}

}
