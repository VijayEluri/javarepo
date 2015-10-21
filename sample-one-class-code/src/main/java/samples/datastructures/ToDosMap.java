package samples.datastructures;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Miguel Reyes
 *         Date: 10/20/15
 *         Time: 1:45 PM
 */
public class ToDosMap {

    public static void main(String[] args) {
        Map<ToDos, String> map = new HashMap<ToDos, String>();
        ToDos t1 = new ToDos("Monday");
        ToDos t2 = new ToDos("Monday");
        ToDos t3 = new ToDos("Tuesday");

        map.put(t1, "laundry");
        map.put(t2, "clean");
        map.put(t3, "walk-dog");

        //If you comment out the HashCode overridden method the size will be 3
        System.out.println("Size: " + map.size());

    }

}

class ToDos {
    String day;

    ToDos(String d) {
        day = d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDos toDos = (ToDos) o;

        if (day != null ? !day.equals(toDos.day) : toDos.day != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return day != null ? day.hashCode() : 0;
    }
}
