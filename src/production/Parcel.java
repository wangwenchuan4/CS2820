package production;

public class Parcel {
  /**
   * 
   * @author Ted Herman
   * 
   * A Parcel has an address and a list of Items
   *
   */
  String address;
  Item[] items;
  boolean finished;
  public Parcel(String address, Item[] items) { 
	this.address = address;
	this.items = items;
	finished = false;
    }
  public boolean isFinished() { return finished; }
  public void setFinished() { finished = true; }
  public String toString() { return "Parcel"; }
}