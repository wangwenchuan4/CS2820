package production;
import org.junit.Test;


/**
 * 
 * @author Wenchuan
 *
 */

public class TestMaster {

		
		
   @Test
   public void test() {
		
		MockFloor F = new MockFloor();
		MockRobotScheduler R = new MockRobotScheduler();
		MockOrderControl O = new MockOrderControl();	
		MockBelt B = new MockBelt();
		MockInventory I = new MockInventory();
		MockVisualizer V= new MockVisualizer();
		Master m=new Master( F, R, I, O, B, V);
		
		m.run(30);
       
       
   }
   
   
   /**
    *  @author wenchuan wang 
    * master class is the main program to run the simulation by passing tick to each component
    */
   public class Master {
	    

		private MockRobotScheduler rob;
		private MockFloor flo;
		private MockOrderControl ord;
		private MockInventory inv;
	    private MockBelt bel;
	    private MockVisualizer vis;
	    
	    /**
	     * @author wenchuan wang 
	     * @param floor, robot, inventory, order, belt, inventory
	     * master constructors create an instance of all component 
	     * 
	     */
	public Master(MockFloor floor, MockRobotScheduler robot,MockInventory inventory,MockOrderControl order, MockBelt belt, MockVisualizer visualizer){
			
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
    * all the mock classes for each component 
    * @author Wenchuan Wang
    *
    */
   
   class MockBelt implements Tickable{
		
		Master master;
		int currentTime;
		
		public MockBelt( ){
			
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
   
   public class MockFloor implements Tickable{
		
		Master master;
		int currentTime;
		
		public MockFloor(){
			
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
public class MockInventory implements Tickable{
	
	Master master;
	int currentTime;

	public MockInventory(){
		
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
public class MockOrderControl implements Tickable{
	
	
	int currentTime;

	public MockOrderControl(){
		
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


public class MockRobotScheduler implements Tickable {
  	Master master;
      int currentTime;
		
  public MockRobotScheduler(){
			
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


public class MockVisualizer implements Tickable{
		
		Master master;
		int currentTime;

		public MockVisualizer(){
			
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