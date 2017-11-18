import java.io.Serializable;

/**
 * 
 */

/**
 * @author corentinboget
 *
 */
public class Message implements Serializable {
	public String type;
	public String source;
	public String data;

	@Override
	public String toString() {
		return source + " : " + data;
	}
}
