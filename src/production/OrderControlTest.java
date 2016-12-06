package production;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class OrderControlTest {
	
	/**
	 * @author Casey Kolodziejczyk
	 * 
	 *  Original test to generate new Orders and fulfill them
	 *  Simply follows the process of completing an order and prints
	 *  out the steps as they are completed. Used for debugging
	 */
	@Test
	public void testCompleteSomeOrders() {
		SimRandom numbers = new SimRandom();
		Floor F = new MockFloor(numbers);
		Inventory I = new Inventory(F, numbers);
		Belt B = new Belt(F);
		RobotScheduler R = new RobotScheduler(F);
		
		OrderControl O = new OrderControl(I, B, R, numbers);
		
				
		System.out.println("Orders in OC: " + O.orderAmount());
		System.out.println("Orders in OC (After): " + O.allOrders);
		// Keeps getting stuck at Get Path. Work from there from that. 
		//O.tick(30);
		//Master m = new Master(F, R, I, O, B);
 		//m.run(30);
		
		O.completeNextOrder();
		
		O.orderAmount();
		System.out.println("Orders in OC (After): " + O.allOrders);
		
		O.completeNextOrder();
		
		O.orderAmount();
		System.out.println("Orders in OC (After): " + O.allOrders);
		O.completeNextOrder();
		
		O.orderAmount();
		System.out.println("Orders in OC (After): " + O.allOrders);
		
		O.completeNextOrder();
		System.out.println("\n Got to the end");
		
	}
}
