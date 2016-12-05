package production;

import static org.junit.Assert.*;
import org.junit.Test;


public class BeltTester {
	
	/* Class for testing functionality of Belt and Bin classes 
	 * 
	 * */
	
	@Test
	public void testBeltCapacity() {
		Order order = null;
 		SimRandom rand = new SimRandom();

		Floor F = new MockFloor(rand);
		Belt beltTester = new Belt(F);
		Bin bin = new Bin();
		bin.setOrder(order);
		beltTester.addBin(bin);
		assertEquals(1, beltTester.getNumberOfBins());
		
	}
	
	
	

	

}
