package samples.datastructures;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Miguel Reyes
 *         Date: 10/21/15
 *         Time: 2:21 PM
 */
public class TreeSetSample {

    public static void main(String[] args) {
        TreeMap<String, String> myMap = new TreeMap<>();
        myMap.put("a", "apple");
        myMap.put("d", "date");
        myMap.put("f", "fig");
        myMap.put("p", "pear");
        myMap.put("m", "mango");

        System.out.println("1st >  fig: " + myMap.higherKey("f")); //">f" Prints m
        System.out.println("1st >= fig: " + myMap.ceilingKey("f")); //">=f" prints f
        System.out.println("1st <  fig: " + myMap.lowerKey("f")); //"<f" prints d
        System.out.println("1st <= fig: " + myMap.floorKey("f")); //"<=f" prints f

        SortedMap<String, String> sub;
        sub = myMap.tailMap("f"); //submap where key ">= f" (non-inclusive, no boolean "true")
        System.out.println("1st >= fig: " + sub.firstKey()); //Prints m
    }

}
