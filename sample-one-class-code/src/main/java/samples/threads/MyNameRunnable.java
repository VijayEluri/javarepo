package samples.threads;

/**
 * @author Miguel Reyes
 *         Date: 10/8/15
 *         Time: 3:52 PM
 */
public class MyNameRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Running MyNameRunnable");
        System.out.println("Run by " + Thread.currentThread().getName());

    }
}
