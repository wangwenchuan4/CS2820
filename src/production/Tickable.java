
package production;

/**
 * 
 * @author wenchuan wang
 *interface that implemented by each component allowing the master
 *to tick each component
 */
public interface Tickable {
    public void tick(int count);
}
