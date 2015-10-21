package samples.datastructures;

import java.util.PriorityQueue;

/**
 * @author Miguel Reyes
 *         Date: 10/21/15
 *         Time: 11:58 AM
 */
public class PriorityQueueSample {

    public static void main(String[] args) {
        PriorityQueue<String> pq = new PriorityQueue<String>();
        pq.add("2");
        pq.add("4");
        System.out.print(pq.peek() + " ");
        pq.offer("1");
        pq.add("3");
        pq.remove("1");
        System.out.print(pq.poll() + " ");
        if (pq.remove("2")) System.out.print(pq.poll() + " ");
        System.out.print(pq.poll() + " " + pq.peek());
    }
}
