package samples.datastructures;

import java.util.LinkedHashSet;

/**
 * @author Miguel Reyes
 *         Date: 10/21/15
 *         Time: 1:02 PM
 */
class Turtle {
    int size;

    public Turtle(int s) {
        size = s;
    }

    public boolean equals(Object o) {
        return (this.size == ((Turtle) o).size);
    }

    public int hashCode() {
        return size / 5;
    } //If commented out the number of items inserted is 3
}

public class TurtleTest {
    public static void main(String[] args) {
        LinkedHashSet<Turtle> t = new LinkedHashSet<>();
        t.add(new Turtle(1));
        t.add(new Turtle(2));
        t.add(new Turtle(1));
        System.out.println(t.size());
    }
}