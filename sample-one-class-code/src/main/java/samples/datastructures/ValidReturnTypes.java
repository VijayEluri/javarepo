package samples.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Reyes
 *         Date: 10/21/15
 *         Time: 11:44 AM
 */
public class ValidReturnTypes {

    public static <E extends Number> List<E> process(List<E> nums) {

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> output = process(numbers);

        List<Number> numbers2 = new ArrayList<>();
        List<Number> output2 = process(numbers2);

        ArrayList<Number> numbers3 = new ArrayList<>();
        List<Number> output3 = process(numbers3);
    }

}
