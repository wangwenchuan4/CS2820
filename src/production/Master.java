package production;

import java.util.*;
/**
 *  @author wenchuan wang @author Ted Herman
 * master class is the main program to run the simulation by passing tick to each component
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
     * master constructors create an instance of all component 
     * 
     */
public Master(Floor floor, RobotScheduler robot,Inventory inventory,OrderControl order, Belt belt){
		
	    flo =  floor;
		rob =  robot;
		inv =  inventory;
		ord =  order;
		bel =  belt;
	}
   



	/**
     * @author wenchuan wang
     * @param limit 
     *
     * it runs the simulation in given time limit and and pass the tick to each component
     */
public void run(int limit) {
	  
   int time=0; 
		while (time<=limit){
			
			rob.tick(time); 
			ord.tick(time);
			inv.tick(time);
			bel.tick(time);
			        
		
			time++;
			
		}//end of while loop  

}
}
