package samples.innnerclasses;

/**
 * @author Miguel Reyes
 *         Date: 10/19/15
 *         Time: 12:36 PM
 */
public abstract class AbstractTest {

    public int getNum() {
        return 45;
    }

    public abstract class Bar {

        public int getNum() {
            return 38;
        }
    }


    public static void main(String[] args) {
        AbstractTest t = new AbstractTest() {
            @Override
            public int getNum() {
                return 22;
            }
        };

        AbstractTest.Bar f = t.new Bar() {
            @Override
            public int getNum() {
                return 57;
            }
        };


        System.out.println("f = " + f.getNum());
    }

}
