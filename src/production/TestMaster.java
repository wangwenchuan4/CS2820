package production;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Wenchuan
 *
 */

public class TestMaster {

		
		
   @Test
   public void test() {
		SimRandom rand = new SimRandom();
		
		Floor F = new MockFloor(rand);
			RobotScheduler R = new RobotScheduler();
			OrderControl O = new OrderControl();
			Belt B = new Belt("belt1", 2, 2);
			Inventory I = new Inventory(F, new SimRandom());
			Master m=new Master( F, R, I, O, B);
			m.run(30);
       
       
   }
}