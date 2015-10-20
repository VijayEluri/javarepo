package samples.innnerclasses;

/**
 * @author Miguel Reyes
 *         Date: 10/19/15
 *         Time: 1:28 PM
 */
public class Navel {

    private int size = 7;
    private static int length = 3;

    public static void main(String[] args) {
        new Navel().go();
    }

    void go() {
        int size = 5;
        System.out.println(new Gazer().adder());
    }

    class Gazer {
        int adder() {
            return size * length;
        }
    }
}
