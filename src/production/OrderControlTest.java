package production;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class OrderControlTest {
	
	/**
	 * @author Casey Kolodziejczyk
	 *  Simple test to see if an Order can be fulfilled and the Status of the order changing
	 *
	 * 		This consists of adding items to a bin, and then checking to see if the bin has 
	 * 		the same items as the order.
	 *  	The print Statements are just to see what's going on.
	 * 
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
		O.completeNextOrder();
		
		O.orderAmount();
		System.out.println("Orders in OC (After): " + O.allOrders);
		
		O.completeNextOrder();
		
		O.orderAmount();
		System.out.println("Orders in OC (After): " + O.allOrders);
		O.completeNextOrder();
		
		O.orderAmount();
		System.out.println("Orders in OC (After): " + O.allOrders);
		
		System.out.println("\n Got to the end");
		
	}
}
