package samples.innnerclasses;

/**
 * @author Miguel Reyes
 *         Date: 10/19/15
 *         Time: 12:54 PM
 */
public class Tour {

    public static void main(String[] args) {
        Cathedral c = new Cathedral();

        Cathedral.Sanctum s = c.new Sanctum();
        s.go();

    }

}

class Cathedral {

    class Sanctum {
        void go() {
            System.out.println("Hello!");
        }
    }

}
