package t3.pocs.javase.concurrent;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Testing Multi-Threading
 *
 * @author <a href="trongtt@gmail.com">Trong Tran</a>
 * @version $Revision$
 */
public class TestThread extends TestCase {

    /**
     * Threads can be executed in parallel
     * 
     * @throws InterruptedException
     */
    public void testThread_1() throws InterruptedException {
        List<Thread> set = new ArrayList<Thread>();
        set.add(new Thread(new A("A1")));
        set.add(new Thread(new A("A2")));
        set.add(new Thread(new A("A3")));

        long start = System.currentTimeMillis();
        for (Thread a : set) {
            a.start();
        }

        for (Thread a : set) {
            a.join();
        }
        long end = System.currentTimeMillis();
        long duration = ((end -start) / 1000);
        System.out.printf("Duration time is %ss%n", duration);
        assertTrue(duration < 3);
        System.out.println("Finished the main process");
    }

    /**
     * When the main thread is finished,
     * it will terminate promptly its sub-threads even if they are not finished.
     *
     * @throws InterruptedException
     */
    public void testThread_2() throws InterruptedException {
        List<Thread> set = new ArrayList<Thread>();
        set.add(new Thread(new A("A1")));
        set.add(new Thread(new A("A2")));
        set.add(new Thread(new A("A3")));
        
        for (Thread a : set) {
          a.start();
        }
  
        System.out.println("Finished the main process");
    }

    class A implements Runnable {
        String id;
        public A(String id) {
            this.id = id;
        }
        
        @Override
        public void run() {
            System.out.println("Start thread #" + this.id);
            try {
                // Sleep for 1s
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finish thread #" + this.id);
        }
    }
}
