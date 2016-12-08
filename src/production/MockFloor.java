package production;

import java.util.*;

/**
 * 
 * @author Ted Herman
 *
 */
public class MockFloor implements Floor {
  /**
   * A fake Floor component for testing purposes only
   */
  static final int warehousewidth = 40;
  static final int warehousedepth = 50;

  final Point picker = new Point(0,48);
  final Point packer = new Point(0,12);
  final Point shippingdock = new Point(0,0);
  final Point receivingdock = new Point(20,0);
  final Point charger = new Point(4,5);
  final Point charger2 = new Point(5,5);
  final Point charger3 = new Point(6,5);

  Point[] chargers;
  List<ShelfArea> shelfareas;
  Map<String,Cell> allpoints;
  SimRandom randogen;

  /**
   * Constructor of Mock Floor
   * @param rand is a SimRandom for predictable randomness
   */
  public MockFloor(SimRandom rand) {
    randogen = rand;
    shelfareas = new ArrayList<ShelfArea>();
    shelfareas.add(new ShelfArea(new Point(5,20),35, randogen));
    shelfareas.add(new ShelfArea(new Point(5,35),35, randogen));
    shelfareas.add(new ShelfArea(new Point(5,40),35, randogen));
    allpoints = new HashMap<String,Cell>();
    // make a map of all cells that the warehouse has, where
    // each cell is one "square" on the floor
    for (int i=0; i<warehousewidth; i++)
      for (int j=0; j<warehousedepth; j++) {
        Point P = new Point(i,j);  // (i,j) is the (x,y)
        // check if this point already has a cell in a shelf area
        // and if so, just use the existing cell
        Cell N = new Cell(i,j);  // will be the new cell
        for (ShelfArea s: shelfareas) {
          if (s.hasWithin(P)) {  
        	N = s.getCell(P);
        	assert N != null;
            }
          }
        allpoints.put(P.toString(),N);
        }
    chargers = new Point[3];
    chargers[0] = charger;
    chargers[1] = charger2;
    chargers[2] = charger3;
    }
  /**
   * @author Ted Herman
   * return Cell at specified place
   */
  public Cell getCell(Point P) {
	return allpoints.get(P.toString());  
    }
  public Cell getCell(int x, int y) {
	return getCell(new Point(x,y));
    }
  /**
   * @author Ted Herman
   * methods to return known locations in warehouse
   */ 
  public int getWarehouseWidth() { return warehousewidth; }
  public int getWarehouseDepth() { return warehousedepth; }
  public Point getPicker() { return picker; }
  public Point getPacker() { return packer; }
  public Point getShippingDock() { return shippingdock; }
  public Point getReceivingDock() { return receivingdock; }
  
  /**
   * @author Andrew Marburg
   * method that will return a charger from the list of chargers
   */
  public Point getCharger() {
	  int place = 1;
	  for(int i = 0; i<3; i++){
		  Cell y = getCell(chargers[i]);
		  if(y.getContents() == null ){
			  place = i;
			  return chargers[i];
		  }
	  }
	  return chargers[place];
	   }

  public List<Point> getBeltArea() {
	ArrayList<Point> beltarea = new ArrayList<Point>();
	for (int i=picker.y; i>=0; i--) {
	  beltarea.add(new Point(0,i));	
	  }
	return beltarea;
    }

  
 /*** @author Andrew Marburg
  * method that will be called to provide the a robot with a new path if it
  * is about to collide with another robot
  * @param oldPath
  * @return newPath for the Robot to traverse
  * isn't used because I couldnt get it to be called
  */
 
 public List<Point> getNewPath(List<Point> old, Point currLoc){
	  System.out.println("GOT NEW PATH");
	  Point curr = new Point(currLoc.x,currLoc.y);
	  Point next = new Point(old.get(0).x,old.get(0).y);
	 // LinkedList<Point> L = new LinkedList<Point>();
	  if (curr.y > next.y || curr.y < next.y){
		  
		  old.add(0, curr.right());
		  old.add(1,curr);
		 /** for(Point e: old){
			  L.add(e);
		  }**/
	  
		  
	  }
	  if (curr.x > next.x || curr.x < next.x){
		  old.add(curr.below());
		  old.add(curr);
		/**  for(Point e: old){
			  L.add(e);
	  }**/
	  
	  
	  
  }
	  return old;
  }


  
  
  
  /**
   * Get path for robot
   * @author Grant Gertsen
   * @param s
   * @param t
   * @return a list of points for the robot to traverses
   */
  public List<Point> getPath(Point s,Point t) {
	LinkedList<Point> L = new LinkedList<Point>();
	Point temp = new Point(s.x, s.y);
	System.out.println( "Robot at " + s + " is on it's way to " + t);
	L.addFirst(s);
		while (temp.y != t.y) {
			if(temp.y > t.y) {
			temp = temp.left();
			//System.out.println("New coords: " + temp);
		} else {
			temp = temp.right();
			//System.out.println("New coords: " + temp);
		}
		L.add(temp);
	}
	while(temp.x != t.x) {
		if(temp.x > t.x) {
			temp = temp.above();
			//System.out.println("New coords: " + temp);

		} else {
			temp = temp.below();
			//System.out.println("New coords: " + temp);
		}
		L.add(temp);
	}
	L.addLast(t);
	return L;
    }
  public int getNumShelfAreas() { 
	return shelfareas.size();
    }
  public ShelfArea getShelfArea(int which) {
	return shelfareas.get(which);
    }
  
  /**
   * @return some random Point within a randomly 
   * chosen shelfarea - might be useful for product
   * distribution on shelves, returning a shelf to
   * the shelfarea, etc.
   */
  public Point randomInShelfArea() {
    int s = randogen.nextInt(shelfareas.size());
    return (shelfareas.get(s)).randomPoint();
    }
}
