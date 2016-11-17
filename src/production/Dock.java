package production;

/**
 * 
 * @author Ted Herman
 * 
 * The Dock interface is implemented by the Inventory
 * component; when a Robot arrives to the Shipping Dock, it 
 * invokes Dock.notify() to tell the Inventory that the
 * requested Robot has arrived, carrying a Shelf.
 * 
 */
public interface Dock {
  void notify(Robot r, Shelf s);
  }
