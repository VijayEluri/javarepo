import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

public class HelloWorldObservableCommand extends HystrixObservableCommand<String> {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldObservableCommand.class);
    private String name;

    public HelloWorldObservableCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.name = name;
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        logger.info("******* Called method resumeWithFallback");
        return Observable.just("Returning a Fallback");
    }

    @Override
    protected Observable<String> construct() {
        logger.info("******* construct method Invoked");
        return Observable.just("Hello" + this.name);
    }

}