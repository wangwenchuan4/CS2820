
package production;

import java.util.*;
/**
 * TODO - Need Packer method.
 * @author Peter Nelson
 */


public class Belt implements Tickable {
	
	//double speed;
	//double beltWidth;
	Floor F;
	List<Point> beltArea;
	//int beltCapacity;  //determined by belt length -- does floor determine this?
	ArrayList<Bin> currentBins = new ArrayList<Bin>();
	Bin newBin;
	
	ArrayList<Bin> toBeShipped = new ArrayList<Bin>();
	
	String beltID;
	
	/**
	 * 
	 * @author Peter Nelson
	 * Constructor for Belt. Takes instance of Floor as parameter.
	 * Gets the belt area from Floor.
	 */
	public Belt(Floor F) {
		//this.beltID = beltID;
		//this.beltCapacity = beltCapacity;
		this.F = F;
		beltArea = F.getBeltArea();
	}

	/**
	 * pauseBelt freezes the movement of the belt.  Ticker still ticks but belt is stationary.
	 */
	public void pauseBelt() {
		// pause belt
	}
	
	
	/**
	 * Resumes movement of Belt
	 */
	public void resumeBelt() {
		//resume
	}
	
	
	/**
	 * Returns an ArrayList of Bin objects currently on the Belt
	 * @return
	 */
	public ArrayList<Bin> getCurrentBins() {
		return currentBins;
	}
	
	/**
	 * Returns the number of bins currently on the Belt
	 * @return
	 */
	public int getNumberOfBins() {
		return currentBins.size();
	}
	
	/**
	 * Adds bin to Belt - referenced by beltID
	 * @param binId
	 */
	public void addBin(Bin bin) {
		//add bin
		currentBins.add(bin);
	}
	/**
	 * Removes bin from Belt.
	 * @param binId
	 */
	//May need to replace with some Packer method
	public void remove(Bin binId) {
		//remove bin
	}

	public Bin getBin() {
		newBin = new Bin();
		return newBin;
	}
	
	/**
	 * @author TedHerman - needed for compatibility with Orders
	 * @return
	 */
	public boolean binAvailable() {
		if (newBin != null) {
			return false;
		}
		
		Cell c = F.getCell(F.getPicker());
		if (c.getContents() != null){
			return false;
		}
		
	return true;
	}
	
	  private boolean isMovable() {
			if (newBin != null) {
				return false;  // wait for picker to finish bin
			}
			for (Point p: beltArea) {
			  Cell c = F.getCell(p);
			  Object o = c.getContents();
			  if (o == null) {
				  continue;  // skip empty cell
			  }
			  if ((o instanceof Bin) && !((Bin)o).isFinished()) {
				  return false;
			  }
			  
			  if ((o instanceof Parcel) && !((Parcel)o).isFinished()) {
				  return false;
			  }
			  }
			return true;  // nothing stops belt from moving
		}
	  
	/**
	 * Implemented from Tickable.  This is what will be used to move the belt.
	 */
	@Override
	public void tick(int count) {
		// TODO Auto-generated method stub
		
		if (newBin != null) {
		      if (!newBin.isFinished()) {
		    	  return; // belt cannot move
		      }
		      System.out.println("Belt is moving");

		      Cell c = F.getCell(F.getPicker());   
		      if (c.getContents()!=null) {
		    	  return;   // wait for cell to empty
		      }
		      c.setContents(newBin);
		      newBin = null;
			  }
					
			if (!isMovable()) {
				return;
			}
			Object prev = null;  // temporary variable used in copy forward
			
			for (Point p: beltArea) {
			  Cell c = F.getCell(p);
			  Object t = c.getContents(); 
			  c.setContents(prev);        
			  prev = t;
			  }
			
		if (prev != null) {
			//toBeShipped.add((Bin)c.getContents());
			System.out.println("Bin reached end of belt - ready to be shipped");
		}
		
	}
	

}
