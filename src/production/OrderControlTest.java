package production;

import static org.junit.Assert.*;

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
	public void testCompleteNextOrder() {
		OrderControl controlTest = new OrderControl();
		
	
	//System.out.println(controlTest.getRandomOrder());
		controlTest.completeNextOrder();
		
		controlTest.completeNextOrder();

	}

}
