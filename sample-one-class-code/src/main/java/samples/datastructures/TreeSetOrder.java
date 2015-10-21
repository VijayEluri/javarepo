package samples.datastructures;

import java.util.TreeSet;

/**
 * @author Miguel Reyes
 *         Date: 10/20/15
 *         Time: 1:35 PM
 */
public class TreeSetOrder {

    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(5);
        treeSet.add(1);
        treeSet.add(10);
        treeSet.add(2);

        treeSet.stream()
                .forEach(item -> System.out.print(item + " "));

    }
}
