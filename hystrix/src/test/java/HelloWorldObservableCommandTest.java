import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Miguel Reyes
 *         Date: 11/20/15
 *         Time: 10:43 AM
 */
public class HelloWorldObservableCommandTest {

    private Logger logger = LoggerFactory.getLogger(HelloWorldObservableCommandTest.class);

    @Test
    public void testCallingConstructExplicitly() {
        HelloWorldObservableCommand command = new HelloWorldObservableCommand("Miguel");
        command.construct().subscribe(e -> {
            logger.info(e);
            assert e.equals("HelloMiguel");
        });
    }

    /**
     * ".observe()" method returns a Hot Observable which starts executing the "construct" method immediately
     *
     * @throws InterruptedException
     */
    @Test
    public void testUsingObserveMethod() throws InterruptedException {
        HelloWorldObservableCommand helloWorldCommand = new HelloWorldObservableCommand("World");
        CountDownLatch l = new CountDownLatch(1);
        Observable<String> obs = helloWorldCommand.observe();
        obs.subscribe(
                s -> logger.info("Received : " + s),
                t -> logger.error(t.getMessage(), t),
                () -> l.countDown()
        );
        l.await(5, TimeUnit.SECONDS);
    }

    /**
     * ".toObservable" variation returns a Cold Observable and would not call "construct" method unless it is subscribed to
     */
    @Test
    public void testUsingToObservableMethod() throws InterruptedException {
        HelloWorldObservableCommand helloWorldCommand = new HelloWorldObservableCommand("World");
        Observable<String> obs = helloWorldCommand.toObservable();
        CountDownLatch l = new CountDownLatch(1);
        obs.subscribe(logger::info, t -> l.countDown(), () -> l.countDown());
        l.await();
    }

}