package samples.datastructures;

import java.util.ArrayList;

/**
 * @author Miguel Reyes
 *         Date: 10/21/15
 *         Time: 2:43 PM
 */
class Business{}
class Hotel extends Business {}
class Inn extends Hotel{}
public class Travel {
	ArrayList<Hotel> go() {
	    return new ArrayList<Hotel>();
//	    return new ArrayList<Inn>(); // Not valid
//	    return new ArrayList<Object>(); // Not valid
	}
}
