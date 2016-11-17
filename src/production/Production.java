package production;

public class Production {
	public static void main(String[] args){
		 		SimRandom rand = new SimRandom();
		 		Floor F = new MockFloor(rand);
		 		RobotScheduler R = new MockRobotScheduler(F);
		 		Belt B = new MockBelt(F);
		 		Visualizer V = new MockVisualizer(F);
		 		Inventory I = new MockInventory(F,randomsource);
		 		Orders O = new MockOrders(I,R,randomsource);
		 		Master m = new Master(V, F, B, I, O, R);
		 		m.run(26);
		 	}
}
