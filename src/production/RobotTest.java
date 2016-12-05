package production;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class RobotTest {
	
	@Test
	public void getShelfTest(){
		SimRandom rand = new SimRandom();
		Floor floor = new MockFloor(rand);
		
		
		RobotScheduler r = new RobotScheduler(floor);
		List<Point> gg = floor.getPath(r.robots[0].location, new Point(20,21));
		r.robots[0].destination = gg;
		System.out.println(gg);
		r.tick(3);
		System.out.println("Robot's location is point");
		System.out.println(r.robots[0].location);
		r.tick(3);
		System.out.println("Robot's location is point");
		System.out.println(r.robots[0].location);
		r.tick(3);
		System.out.println("Robot's location is point");
		System.out.println(r.robots[0].location);
		assertEquals(true, r.robots[0].destination == null);
		
		
	}

}
