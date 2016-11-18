package production;
/**
 * 
 * @author Wenchuan
 *
 */
public class Production {
	public static void main(String[] args){
		 		SimRandom rand = new SimRandom();
		 		Floor F = new MockFloor(rand);
		
		 		Master m = new Master(F, R, I, O, B);
		 		m.run(26);
		 	}
}
