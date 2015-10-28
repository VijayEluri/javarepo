package samples.reactivex;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Miguel Reyes
 *         Date: 10/28/15
 *         Time: 1:26 PM
 */
public class RecursiveObservable {


    /**
     * First I create an observable and get the reference to the subscriber. Let’s call this the parent observable.
     * I make the initial call to the getIntegers method passing in the pageStart, pageSize, maxPageNumber, and the reference to the subscriber object.
     * The getIntegers method creates an observable for the range of integers from pageStart to pageSize and adds a subscribe method.
     * The subscribe method calls the onNext method for the parent subscriber so that the parent observable emits the integer.
     * I also added a doOnCompleted to the range observable so that when it’s completed the range, i.e. reached the pageSize,
     * it checks to see if we’ve reached our maximum. If we have, we call onCompleted on the parent observable subscriber
     * to indicate we’re finished. If we haven’t, we recursively call the getIntegers method with the starting integer of the next page.
     */
    @Test
    public void observableWithRecursion_returnsAllObjects() {
        final int maxPageNum = 15;
        Observable.create(subscriber -> getIntegers(1, 5, maxPageNum, subscriber)).subscribe(System.out::println);
    }

    private void getIntegers(final int pageStart, final int pageSize, int maxPageNum, Subscriber<? super Integer> subscriber) {
        Observable.range(pageStart, pageSize)
                .doOnCompleted(() -> {
                    int newPageStart = pageStart + pageSize;
                    if(newPageStart >= maxPageNum) {
                        subscriber.onCompleted();
                    } else {
                        getIntegers(newPageStart, pageSize, maxPageNum, subscriber);
                    }
                }).subscribe(subscriber::onNext);
    }


}
