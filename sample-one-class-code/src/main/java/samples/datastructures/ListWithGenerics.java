package samples.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Reyes
 *         Date: 10/20/15
 *         Time: 1:22 PM
 */
public class ListWithGenerics {

    public static void main(String[] args) {

        List<List<Integer>> table = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                row.add(i * j);
            }
            table.add(row);
        }
        table.stream()
                .forEach(System.out::println);
    }

}
