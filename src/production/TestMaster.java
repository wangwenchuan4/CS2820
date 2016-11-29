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
   
   public class Floor implements Tickable,Task{
		
		Master master;
		int currentTime;
		
		public Floor(Master m){
			master = m;
		}
		
		public void tick(int count){
			currentTime = count;
			
		}
		
		public void fire(Object arg){
			System.out.print(arg);
			System.out.println(currentTime);
			enqueue("Floor event happened at: ");
		}
		
		public void enqueue(Object arg){
			Event e = new Event(currentTime+4,arg,this);
			master.enqueue(e);
		}
		
		
	}
public class Inventory implements Tickable,Task{
	
	Master master;
	int currentTime;

	public Inventory(Master m){
		master = m;
	}
	
	public void tick(int count){
		currentTime = count;
		
	}
	
	public void fire(Object arg){
		System.out.print(arg);
		System.out.println(currentTime);
		enqueue("Inventory event happened at: ");
	}
	
	public void enqueue(Object arg){
		Event e = new Event(currentTime+6,arg,this);
		master.enqueue(e);
		
	}
	
	
}
public class Order implements Tickable,Task{
	
	Master master;
	int currentTime;

	public Order(Master m){
		master = m;
	}
	
	public void tick(int count){
		currentTime = count;
		
	}
	
	public void fire(Object arg){
		System.out.print(arg);
		System.out.println(currentTime);
		
		enqueue("Orders event happened at: ");
	}
	
	public void enqueue(Object arg){
		Event e = new Event(currentTime+5,arg,this);
		master.enqueue(e);
	}
	
	
}
/**
*
* @author wenchwang
*/
//assume robot event takes 5 tick
public class Robot implements Tickable,Task {
  	Master master;
      int currentTime;
		
  public Robot(Master m){
			master = m;
		}
  
  
  public void tick(int count){
			currentTime = count;
			
		}
		
		
  public void fire(Object arg){
			System.out.print(arg);
			System.out.println(currentTime);
                       
			enqueue("Robot event happened at: ");
		}
              
  
  public void enqueue(Object arg){
			Event e = new Event(currentTime+5,arg,this);
			// add another (future) event to the Master's queue
                      master.enqueue(e);
		}
		

        
              
}
/**
*
* @author wenchwang
*/
//assume Visualizer event takes 3 tick
public class Visualizer implements Tickable,Task{
		
		Master master;
		int currentTime;

		public Visualizer(Master m){
			master = m;
		}
		
		public void tick(int count){
			currentTime = count;
			
		}
		
		public void fire(Object arg){
			System.out.print(arg);
			System.out.println(currentTime);
			enqueue("Visualizer event happened at: ");
		}
		
		public void enqueue(Object arg){
			Event e = new Event(currentTime+3,arg,this);
			master.enqueue(e);
		}
		
		
	}
   
}