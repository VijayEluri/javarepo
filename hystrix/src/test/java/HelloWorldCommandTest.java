import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author Miguel Reyes
 *         Date: 11/19/15
 *         Time: 5:47 PM
 */
public class HelloWorldCommandTest {

    private Logger logger = LoggerFactory.getLogger(HelloWorldCommandTest.class);

    @Test
    public void testSynchronousCall() {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("World");
        assertEquals("HelloWorld", helloWorldCommand.execute());
    }

    @Test
    public void testReturnFuture() throws ExecutionException, InterruptedException {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("World");
        Future<String> future = helloWorldCommand.queue();
        assertEquals("HelloWorld", future.get());
    }

    @Test
    public void testReturnObservable() throws InterruptedException {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("World");
        CountDownLatch l = new CountDownLatch(1);
        Observable<String> obs = helloWorldCommand.observe();
        obs.subscribe(
                s -> logger.info(s),
                t -> logger.error(t.getMessage(), t),
                l::countDown
        );
        l.await(5, TimeUnit.SECONDS);
    }

}