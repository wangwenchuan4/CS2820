package production;

public class Shelf {

	Point homeLocation;
	Point currentLocation;
	boolean resting; // true if at home, not on robot

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
	 * Set the location at point p
	 * 
	 * @author Grant Gertsen
	 * @param p
	 */
	public void setLocation(Point p) {
		currentLocation = p;
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

	/**
	 * For the robot to pickup the shelf
	 * @author Grant Gertsen
	 */
	public void pickup() {
		System.out.println("Picked up shelf!");
		resting = false;
	}

	/**
	 * For the robot to put the shelf down
	 * @author Grant Gertsen
	 */
	public void putdown() {
		System.out.println("Put down shelf!");
		resting = true;
	}

	/**
	 * needed a method to ask if the shelf is resting for RobotScheduler
	 * 
	 * @author Andrew Marburg
	 * @return true if shelf is resting
	 */
	public boolean isResting() {
		return resting;
	}

	/**
	 * @author Casey Kolodziejczyk Just overrides toString for testing purposes
	 */
	@Override
	public String toString() {
		return ("Shelf: " + homeLocation + "");
	}

}
