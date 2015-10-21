package samples.datastructures;

import java.util.TreeSet;

/**
 * Without implementing Comparable main method will fail!
 * To check for duplicates we need to override compareTo() and return 0 if a duplicate is found.
 *
 * TreeSet implements NavigableSet that extends SortedSet that works with natural ordering instead of equals and hashCode
 * See: http://stackoverflow.com/questions/17608422/i-am-able-to-insert-duplicate-entries-in-treeset-how-to-overcome-this
 */
class Dog implements Comparable<Dog> {
    int size;

    Dog(int s) {
        size = s;
    }

    @Override
    public int compareTo(Dog o) {
        if (size == o.size)
            return 0;

        return size - o.size;
    }
}

public class FirstGrade {
    public static void main(String[] args) {
        TreeSet<Integer> i = new TreeSet<>();
        TreeSet<Dog> d = new TreeSet<>();
        d.add(new Dog(1));
        d.add(new Dog(2));
        d.add(new Dog(10));
        d.add(new Dog(8));
        d.add(new Dog(1));

        i.add(1);
        i.add(2);
        i.add(1);
        System.out.println(d.size() + " " + i.size());
    }
}
