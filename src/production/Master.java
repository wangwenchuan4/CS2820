package production;

import java.util.*;
/**
 *  @author wenchuan wang
 * master class is the main program to run the simulation  
 */

public class Master {
    
	
	private RobotScheduler rob;
	private Floor flo;
	private OrderControl ord;
	private Inventory inv;
    private Belt bel;
    
    /**
     * @author wenchuan wang
     * @param floor, robot, inventory, order, belt, inventory
     * master constructors create an instance of all component and the priority queue to store the events
     * 
     */
public Master(Floor floor,Belt belt,Inventory inventory,OrderControl order, RobotScheduler robot){
		
		
		rob =  robot;
		flo =  floor;
		bel =  belt;
		inv =  inventory;
		ord =  order;
		
	}
   
    /**
     * @author wenchuan wang
     * @param limit 
     * it runs the simulation in given time and remove events that happened from the queue
     */
public void run(int limit) {
	  
   int time=0; 
		while (time<limit){
			  
			((Tickable)ord).tick(time);
			((Tickable)inv).tick(time);
			((Tickable)bel).tick(time);
			((Tickable)rob).tick(time);        
		
			time++;
			
		}//end of while loop  

}
}
