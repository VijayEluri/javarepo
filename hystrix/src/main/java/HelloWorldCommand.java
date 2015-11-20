import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldCommand extends HystrixCommand<String> {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldCommand.class);
    private final String name;

    /**
     * This parameter specifies a Hystrix "Command group" Key, along with Command Key which by default is the simple name of the class,
     * it controls a lot of the bells and whistles of Hystrix behavior.
     * <p>
     * hystrix.command.HelloWorldCommand.metrics.rollingStats.timeInMilliseconds=10000
     * hystrix.command.HelloWorldCommand.execution.isolation.strategy=THREAD
     * hystrix.command.HelloWorldCommand.execution.isolation.thread.timeoutInMilliseconds=1000
     * hystrix.command.HelloWorldCommand.execution.isolation.semaphore.maxConcurrentRequests=10
     * hystrix.command.HelloWorldCommand.circuitBreaker.errorThresholdPercentage=50
     * hystrix.command.HelloWorldCommand.circuitBreaker.requestVolumeThreshold=20
     * hystrix.command.HelloWorldCommand.circuitBreaker.sleepWindowInMilliseconds=5000
     * hystrix.threadpool.default.coreSize=10
     * hystrix.threadpool.default.queueSizeRejectionThreshold=5
     *
     * @param name String name
     */
    public HelloWorldCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        logger.info("********* HelloWorld Command Invoked");
        return "Hello" + name;
    }

    /**
     * Another behavior we may want to control is the response in case the call to the dependent service fails
     *
     * @return message
     */
    @Override
    protected String getFallback() {
        logger.info("About to fallback");
        return "Falling back";
    }

}