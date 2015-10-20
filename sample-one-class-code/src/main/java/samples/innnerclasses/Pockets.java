package samples.innnerclasses;


import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Miguel Reyes
 *         Date: 10/19/15
 *         Time: 1:34 PM
 */
public class Pockets {

    public static void main(String[] args) {
        String[] sa = {"nickel", "button", "key", "lint"};
        Sorter sorter = new Sorter();

        for (String s : sa) {
            System.out.print(s + " ");
        }

        Arrays.sort(sa, sorter);
        System.out.println("");

        for (String s : sa) {
            System.out.print(s + " ");
        }

    }

    static class Sorter implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return b.compareTo(a);
        }
    }

}
