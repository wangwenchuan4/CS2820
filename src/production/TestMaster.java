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
		
		Floor F = new Floor();
		RobotScheduler R = new RobotScheduler();
		OrderControl O = new OrderControl();	
		Belt B = new Belt();
		Inventory I = new Inventory();
		Visualizer V= new Visualizer();
		Master m=new Master( F, R, I, O, B, V);
		
		m.run(30);
       
       
   }
   
   
   
   public class Master {
	    

		private RobotScheduler rob;
		private Floor flo;
		private OrderControl ord;
		private Inventory inv;
	    private Belt bel;
	    private Visualizer vis;
	    
	    /**
	     * @author wenchuan wang @author Ted Herman
	     * @param floor, robot, inventory, order, belt, inventory
	     * master constructors create an instance of all component 
	     * 
	     */
	public Master(Floor floor, RobotScheduler robot,Inventory inventory,OrderControl order, Belt belt, Visualizer visualizer){
			
		//count =0;
		
		    flo =  floor;
			rob =  robot;
			inv =  inventory;
			ord =  order;
			bel =  belt;
			vis=  visualizer;
		}
	   



		/**
	     * @author wenchuan wang
	     * @param limit 
	     *
	     * it runs the simulation in given time limit and and pass the tick to each component
	     */
	public void run(int limit) {
		  
	   int time=0; 
			while (time<=limit){
				((Tickable)flo).tick(time); 
				((Tickable)rob).tick(time); 
				((Tickable)ord).tick(time);
				((Tickable)inv).tick(time);
				((Tickable)bel).tick(time);
				((Tickable)vis).tick(time);
			
				
				time++;
				
			}//end of while loop  

	}
	}
   
   
   /**
    * all the mock classes for master testing
    * @author Wenchuan
    *
    */
   
   class Belt implements Tickable,Task{
		
		Master master;
		int currentTime;
		
		public Belt( ){
			
		}
		
		public void tick(int count){
			currentTime = count;
			if (currentTime==5)
				System.out.println("Belt event happened at: " + currentTime);
		}
		
		public void fire(Object arg){
			System.out.print(arg);
			System.out.println(currentTime);
			enqueue("Belt event happened at: ");
		}
		
		public void enqueue(Object arg){
		
		}
		
		
	}
   
   public class Floor implements Tickable,Task{
		
		Master master;
		int currentTime;
		
		public Floor(){
			
		}
		
		public void tick(int count){
			currentTime = count;
			if(currentTime==2)
			System.out.println("Floor event happened at: "+currentTime);
		}
		
		public void fire(Object arg){
			System.out.print(arg);
			System.out.println(currentTime);
			enqueue("Floor event happened at: ");
		}
		
		public void enqueue(Object arg){
			
		}
		
		
	}
public class Inventory implements Tickable,Task{
	
	Master master;
	int currentTime;

	public Inventory(){
		
	}
	
	public void tick(int count){
		currentTime = count;
		if (currentTime==10)
			System.out.println("Inventory event happened at: "+currentTime);
	}
	
	public void fire(Object arg){
		System.out.print(arg);
		System.out.println(currentTime);
		enqueue("Inventory event happened at: ");
	}
	
	public void enqueue(Object arg){
		
		
	}
	
	
}
public class OrderControl implements Tickable,Task{
	
	
	int currentTime;

	public OrderControl(){
		
	}
	
	public void tick(int count){
		currentTime = count;
		if (currentTime==15)
			System.out.println("OrderControl event happened at: "+currentTime);
	}
	
	public void fire(Object arg){
		System.out.print(arg);
		System.out.println(currentTime);
		
		enqueue("Orders event happened at: ");
	}
	
	public void enqueue(Object arg){
	
	}
	
	
}
/**
*
* @author wenchwang
*/
//assume robot event takes 5 tick
public class RobotScheduler implements Tickable,Task {
  	Master master;
      int currentTime;
		
  public RobotScheduler(){
			
		}
  
  
  public void tick(int count){
			currentTime = count;
			if (currentTime==28)
			System.out.println("Robot event happened at: "+currentTime);
		}
		
		
  public void fire(Object arg){
			System.out.print(arg);
			System.out.println(currentTime);
                       
			enqueue("Robot event happened at: ");
		}
              
  
  public void enqueue(Object arg){
			
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

		public Visualizer(){
			
		}
		
		public void tick(int count){
			currentTime = count;
			if (currentTime==30)
			System.out.println("Visualizer event happened at: "+currentTime);
			
		}
		
		public void fire(Object arg){
			System.out.print(arg);
			System.out.println(currentTime);
			enqueue("Visualizer event happened at: ");
		}
		
		public void enqueue(Object arg){
			
			
		}
		
		
	}
   
}