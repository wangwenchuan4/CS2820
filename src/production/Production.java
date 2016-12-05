package production;
/**
 * 
 * @author Wenchuan
 *main class to create instance of each component and run the simulation of the warehouse
 */
public class Production {
	public static void main(String[] args){
		 		SimRandom rand = new SimRandom();
		 		Floor F = new MockFloor(rand);
		 		RobotScheduler R = new RobotScheduler();
		 		OrderControl O = new OrderControl();
		 		Belt B = new Belt(F);
		 		Inventory I = new Inventory(F, new SimRandom());
		 		
		 		Master m = new Master(F, R, I, O, B);
		 		m.run(30);
		 	}
}
