package samples.threads;

/**
 * @author Miguel Reyes
 *         Date: 10/8/15
 *         Time: 3:48 PM
 */
public class NameThread {

    public static void main(String[] args) {
        MyNameRunnable r = new MyNameRunnable();
        Thread thread = new Thread(r);
        thread.setName("Miguel-Thread");
        thread.start();
    }

}
