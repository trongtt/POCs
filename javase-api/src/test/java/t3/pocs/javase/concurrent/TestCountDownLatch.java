package t3.pocs.javase.concurrent;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Testing CountDownLatch
 *
 * @author <a href="trongtt@gmail.com">Trong Tran</a>
 * @version $Revision$
 */
public class TestCountDownLatch extends TestCase {

    public void testCountDown() throws InterruptedException {
        List<Thread> set = new ArrayList<Thread>();
        CountDownLatch countDown = new CountDownLatch(1);
        set.add(new Thread(new A("A1", countDown)));
        set.add(new Thread(new A("A2", countDown)));
        set.add(new Thread(new A("A3", countDown)));

        for (Thread a : set) {
            a.start();
        }

        Thread.sleep(2000);

        countDown.countDown(); // Trigger to unblock sub-threads

        for (Thread a : set) {
            a.join();
        }
        System.out.println("Finished the main thread");
    }

    class A implements Runnable {
        String id;
        CountDownLatch countDown;

        public A(String id, CountDownLatch countDown) {
            this.id = id;
            this.countDown = countDown;
        }
        
        @Override
        public void run() {
            try {
                countDown.await(); // Block the current thread.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Processing the thread #" + id);
        }
    }
}
