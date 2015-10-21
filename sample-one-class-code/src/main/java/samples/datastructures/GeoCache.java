package samples.datastructures;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 */
public class GeoCache {

    public static void main(String[] args) {
        String[] s = {"map", "pen", "marble", "keu"};
        Othello o = new Othello();
        Arrays.sort(s, o);
        for (String s2 : s) System.out.print(s2 + " ");
        System.out.println(Arrays.binarySearch(s, "map")); //Requires Comparator to be able to perform the binary search, otherwise
        // it will return -1
    }

    private static class Othello implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return b.compareTo(a);
        }
    }
}
