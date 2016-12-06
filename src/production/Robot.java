package production;
import java.util.List;

/**
 *
 * @author Andrew Marburg
 *  changed my Robot class constructors to be based heavily on @author tedherman's example
 * 
 */



public class Robot {
    
  public static final int idle = 0;
  public static final int pickershelfbound = 1;
  public static final int pickerbound = 2;
  public static final int atpicker = 3;
  public static final int afterpickershelfbound = 4;
  public static final int dockshelfbound = 5;
  public static final int dockbound = 6;
  public static final int atdock = 7;
  public static final int afterdockshelfbound = 8;
  public static final int chargerbound = 9;
  public static final int charging = 10;
  
        public List<Point> destination;
        public Point location;
        public Shelf shelf;
        public Point shelfLocation;
        public int state;
        public Picker picker;
        public Dock dock;
        public int battery;
        public int charg;
        
		public Robot(Point startingLocation){
			destination = null;
			location = startingLocation;
			shelf = null;
            state = idle;
            shelfLocation = null;
            picker = null;
            dock = null;
            battery = 100; // this number will most likely change based on how long the avg mission takes
            charg = 0;
                        
                        
			
		}
		/**
                 * 
                 * @author Andrew Marburg
                 * method for getting the robot's current destination
                 */
		public List<Point> getDestination(){
			return destination;
			
		}
                /**
                 * 
                 * @author Andrew Marburg
                 * method for getting robot's current location
                 */
		public Point getLocation(){
			return location;
			
		}
                /**
                 * 
                 * @author Andrew Marburg
                 * method for finding out if the robot has a mission
                 */
		public int status(){
			return state;
				
		}
                /**
                 * 
                 * @author Andrew Marburg
                 * method to see which shelf the robot is currently holding
                 */
		public Shelf hasShelf(){
			return shelf;
		}
                /**
                 * 
                 * @author Andrew Marburg 
                 * method to change the robot's current destination
                 */
		public void setPath(List<Point> newDest){
			destination = newDest;
                        
                      	
		}
		
		public void setDock(Dock d){
			dock = d; 
		}
		
		public void setStatus(int i){
			state = i;
		}
		
		public int batteryStatus(){
			return battery;
		}
                /**
                 * @author Andrew Marburg
                 * method that will be used to drain the robot's battery
                 */
		private void output(){
			
			battery = battery - 1;
			
		}
		/**
		 * @author Andrew Marburg
		 * method that charges the battery for 20 ticks then sets 
		 * the status to idle when fully charged
		 */
		private void charge(int i){
			if(charg<2){
				charg = charg + 1;
				
				System.out.println("Robot " + i + " is Charging");
				
			}
			
			if(charg == 2){
				battery = 100;
				charg = 0;
				state = idle;
				System.out.println("Robot " + i + " is Charged");
			}
			
			
		}
		/**
		 * @author Andrew Marburg
		 * method that drains the robot's battery if it is moving
		 * and checks to see if it needs to be charged when not moving
		 * 
		 */
		
		public void batteryUsage(int i){
			if (state != idle && state != charging){
				output();
				
			}
			if (state == charging){
				charge(i);
			}
			
			if (state == idle && battery < 25){
				setStatus(charging);
				charge(i);
			}
			
		}
		
		
		
		
		
	}


