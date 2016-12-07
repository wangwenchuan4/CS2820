package production;
/**
 * 
 * @author Wenchuan
 *main class to create instances of each component and run the simulation of the warehouse
 */
public class Production {
	public static void main(String[] args){
		 		SimRandom rand = new SimRandom();
		 		Floor F = new MockFloor(rand);
		 		RobotScheduler R = new RobotScheduler(F);
		 		Belt B = new Belt(F);
		 		Inventory I = new Inventory(F, new SimRandom());
		 		OrderControl O = new OrderControl(I,B,R,rand);
		 		
		 		Master m = new Master(F, R, I, O, B);
		 		m.run(2000); // can change as necessary
		 	}
}
