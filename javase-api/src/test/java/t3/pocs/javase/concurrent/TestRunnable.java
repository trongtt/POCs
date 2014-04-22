package t3.pocs.javase.concurrent;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="trongtt@gmail.com">Trong Tran</a>
 * @version $Revision$
 */
public class TestRunnable extends TestCase {

    /**
     * {@link Runnable#run()} doesn't execute in separated threads
     */
    public void testRunnable_1() {
        List<A> set = new ArrayList<A>();
        set.add(new A("A1"));
        set.add(new A("A2"));
        set.add(new A("A3"));

        long start = System.currentTimeMillis();
        for (A a : set) {
            a.run();
        }
        long end = System.currentTimeMillis();
        int duration = (int) ((end - start) / 1000);
        System.out.printf("Duration time is %ss%n", duration);
        assertTrue(duration >= 3);
    }

    class A implements Runnable {
        String id;
        public A(String id) {
            this.id = id;
        }
        
        @Override
        public void run() {
            System.out.println("Running " + this.id);
            try {
                // Sleep for 1s
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
