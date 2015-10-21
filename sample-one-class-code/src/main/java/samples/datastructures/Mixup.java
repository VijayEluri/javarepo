package samples.datastructures;

import java.util.LinkedHashSet;

/**
 * @author Miguel Reyes
 *         Date: 10/21/15
 *         Time: 12:45 PM
 */
public class Mixup {
    public static void main(String[] args) {
        Object o = new Object();
//        Set s = new HashSet<>(); //OK
//        TreeSet s = new TreeSet(); // runs with error!
        LinkedHashSet s = new LinkedHashSet(); //OK
        s.add("o");
        s.add(o);
    }

}
