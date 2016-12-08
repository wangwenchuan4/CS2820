package production;


import java.util.List;

public class RobotScheduler implements Tickable{
	Floor F;
	Robot[] robots;
	
	/**
	 * 
	 * @author Andrew Marburg
	 * intializing the robotScheduler with 3 robots
	 */
	public RobotScheduler(Floor F){
		this.F = F;
		robots = new Robot[3];
		Point st1 = new Point(4,4);
		Point st2 = new Point(5,4);
		Point st3 = new Point(6,4);
		robots[0] = new Robot(st1);
		robots[1] = new Robot(st2);
		robots[2] = new Robot(st3);
		Cell t = F.getCell(st1);
		t.setContents(robots[0]);
		Cell y = F.getCell(st2);
		y.setContents(robots[1]);
		Cell x = F.getCell(st3);
		x.setContents(robots[2]);
		
		
	}
		/**
		 * @author Andrew Maburg
		 * added batteryUsage to tick in order to drain the robot's battery
		 */
	public void tick(int count) { 
		
		// Look to see if any Robot should move
		//for (Robot e: robots) {
		
		
		for (int i = 0; i<3; i++) {
			robots[i].batteryUsage(i);
		   if (robots[i].destination != null) moveRobot(robots[i], i);
		   }
	    };	
	    
	    
	    
	    
	    
	  /**@author Ted Herman and Andrew Marburg
	   * @param r is a Robot to move along its path, and if
	   * the Robot reaches the end of the path, then decide
	   * on where it should go next (if anywhere).
	   * @Andrew Marburg
	   * I changed this method by adding a way to track which robot is being moved
	   * and added print statments to show it's movements at key times
	   */
	  private void moveRobot(Robot r, int i) { 
	    // some initial assertions say what is the expected precondition
	    assert r.destination != null;
	    assert r.destination.size() > 0;
	    Cell tempcell = F.getCell(r.location.x, r.location.y);
	    assert tempcell.getContents() == r || tempcell.getShadow() == r;
	    tempcell.setContents(null); // Robot will no longer be in this cell
	    tempcell.setShadow(null);
		if (r.destination.size()>1) {
		   r.location = r.destination.get(0);  // move to first point in path
		   r.destination.remove(0);  // remove first point in path
		   tempcell = F.getCell(r.location);
		   tempcell.setContents(r);  // robot has moved to new place
		   return;
		   }
		
		// when path has one Point, we arrive in this tick to target
		
		Point goal = r.destination.get(0);
		r.location = goal;
		tempcell = F.getCell(goal);
		
		// on arrival to Shelf, validate there is a Shelf there
		
		if (r.state == Robot.pickershelfbound 
				|| r.state == Robot.dockshelfbound) {
		   assert tempcell.getContents() instanceof Shelf;
		   assert r.shelf == tempcell.getContents();
		   tempcell.setShadow(r);
		   
		   System.out.println("Robot " + (i+1) + " is Picker Shelf Bound");
		   }
		
		// in any other case, cell should empty
		
		
		else { 
		  assert tempcell.getContents() == null; 
		  tempcell.setContents(r);
		  }
		
		r.destination = null;
		switch (r.state) { 
		
		// these are cases of reaching goal in path
		case Robot.pickershelfbound:
		   r.shelf.pickup();  // robot claims this shelf
		   assert !r.shelf.isResting();
		   r.destination = F.getPath(r.location,F.getPicker());
		   r.state = Robot.pickerbound;  // now heading to Picker
		   System.out.println("");
		   System.out.println("Robot " + (i+1) + "'s Location is " + robots[i].location);
		   System.out.println("Robot " + (i+1) + " state is pickerbound");
		   System.out.println("");
		   break;
		case Robot.pickerbound:
		   r.state = Robot.atpicker;
		   r.picker.notify(r,r.shelf);
		   System.out.println("");
		   System.out.println("Robot " + (i+1) + "'s Location is " + robots[i].location);
		   System.out.println("Robot " + (i+1) + " is at Picker");
		   System.out.println("");
		   break;
		case Robot.afterdockshelfbound:
		case Robot.afterpickershelfbound:
		   r.shelf.putdown();  // Shelf is back home
		   tempcell = F.getCell(r.location.x,r.location.y);  // change status of this cell
		   tempcell.setContents(r.shelf);
		   tempcell.setShadow(r);
		   r.shelf = null;
		   r.destination = F.getPath(goal,F.getCharger());
		   r.state = Robot.chargerbound;
		   System.out.println("");
		   System.out.println("Robot " + (i+1) + "'s Location is " + robots[i].location);
		   System.out.println("Robot " + (i+1) + " is Homeward Bound");
		   System.out.println("");
		   break;
		case Robot.dockshelfbound:
		   r.shelf.pickup();  // robot claims this shelf
		   r.destination = F.getPath(r.location,F.getReceivingDock());
		   r.state = Robot.dockbound;  // now heading to Dock
		   break;
		case Robot.dockbound:
		   r.state = Robot.atdock;
		   r.dock.notify(r,r.shelf);
		   break;
		case Robot.atpicker:
		case Robot.atdock:
		   break;   // just wait around in these cases
		case Robot.chargerbound:
		   r.state = Robot.idle;
		   System.out.println("");
		   System.out.println("Robot " + (i+1) + "'s Location is " + robots[i].location);
		   System.out.println("Robot " + (i+1) + " is Idle");
		   System.out.println("");
		   r.batteryUsage(i);
		   break;
		   }
		return;
	    }
	  
	  
	  
	  
	  
	  /**
	   * @param s is a Shelf to fetch and bring to the picker
	   * location (which the Floor knows)
	   * @param p is a Picker interface, implemented by Orders,
	   * which invoked this method - p is essentially a 
	   * "callback" object to notify Orders at some later tick()
	   */
	  public void requestShelf(Shelf s, Picker p) { 
		Point target = s.getHomeLocation(); // where Shelf sits
		Robot robot = findRobot(); // get some idle robot
		robot.destination = F.getPath(robot.location,target);
		robot.state = Robot.pickershelfbound;
		robot.picker = p;
		robot.shelf = s;  // don't have it yet, but will get it
		
	    };
	    
	    
	    
	    
	    
	  /**
	   * @param s is a Shelf to fetch and bring to the receiving
	   * dock location (which the Floor knows)
	   * @param d is a Dock interface, implemented by Inventory,
	   * which invoked this method. The d parameter is thus a
	   * "callback" object to notify Inventory at some later tick()
	   */
	 /** public void requestShelf(Shelf s, Dock d) { 
	  	Robot r = findRobot();
	  	List<Point> pathing = F.getPath(r.getLocation(),s.getLocation());
	  	r.setPath(pathing);
	  	r.setDock(d);
	  	r.setStatus(1);
	  	
	  			
	  }**/
	  	
	  
	  
	  
	  
	  
	  
	  /**
	   * Command to return a robot carrying a shelf back to 
	   * a ShelfArea on the Floor and put it down. Then the 
	   * @param r is a Robot which is carrying a Shelf
	   */
	  public void returnShelf(Robot r) { 
		assert r.state == Robot.atpicker;
		r.destination = F.getPath(r.location,r.shelf.getHomeLocation());
		r.state = Robot.afterpickershelfbound;
	    }
	  
	  /**@author Andrew Marburg
	   * rewrote this method for more than one robot
	   * find an available Robot (which is not in use)
	   */
	  public Robot findRobot() {
		// currently there is only one robot, this is trivial
		  for (int i = 0; i<3; i++) {
			  if(robots[i].state == Robot.idle && robots[i].shelf == null){
				  return robots[i];
			  }
		  }
		Robot r = robots[0];
		
		return r;
		
		
		
		
	  }

	    
	  /**@author Andrew Marburg
	   * rewrote this method for more than one robot
	   * @return true if a robot is available
	   */
	  public boolean robotAvailable() {
		  for(Robot e: robots){
			  if(e.state == Robot.idle){
				  return true;
			  }
		  }
		  return false;
		
	    }


	  
	  /**
	   * @author Andrew Marburg
	   * method to see if the robots next movement will hit another robot
	   * @param r
	   * @return true if there will be a collision
	   * Couldn't figure out how to get it to return true
	   */

	  
	  
	  public boolean collision(Robot r){
		  System.out.println("gg");
		  for(Robot e: robots){
			  
			  if (r != e){
				  if(r.destination.get(0) ==e.destination.get(0)){
				 
					  System.out.println("COLISION");
					  System.out.println("COLISION");
					  System.out.println("COLISION");
					  System.out.println("COLISION");
					  return true;
				  		}
				  
			  		}
			  }
		  
			 return false;
		  
	  }

	  }
	
	