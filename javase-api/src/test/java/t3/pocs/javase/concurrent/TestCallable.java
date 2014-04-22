package t3.pocs.javase.concurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import junit.framework.TestCase;

/**
 * Using Callable instead of Runnable if you want to return a result OR throw a checked exception.
 * <p>
 * Because you can not pass a Callable into a Thread to execute, you instead use the ExecutorService
 * to execute the Callable object.
 * 
 * @author <a href="trongtt@gmail.com">Trong Tran</a>
 * @version $Revision$
 */
public class TestCallable extends TestCase {

    public void testCallable_1() throws InterruptedException, ExecutionException {

        // Create a Executor Service
        ExecutorService pool = Executors.newFixedThreadPool(3);

        Set<Future<Integer>> set = new HashSet<Future<Integer>>();
        
        String[] args = new String[] { "12345678", "12345", "1234567" };
        for (String word : args) {
            Callable<Integer> callable = new A(word);
            // The service accepts Callable object run by way of the submit() method.
            // That means the Callable.call() is started right away
            Future<Integer> future = pool.submit(callable);
            set.add(future);
        }
        int sum = 0;
        for (Future<Integer> future : set) {
            // The get() method of Future will then block until the task is completed
            sum += future.get();
        }

        System.out.printf("The sum of lengths is %s%n", sum);
        assertEquals(20, sum);
    }

    /**
     * A Callable implementation class that the call() method returns the length of a String passed in Constructor.
     * 
     * @author <a href="trongtt@gmail.com">Trong Tran</a>
     * @version $Revision$
     */
    class A implements Callable<Integer> {
        private String word;
        
        public A(String word) {
            this.word = word;
        }
        
        public Integer call() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Integer.valueOf(word.length());
        }
    }
}
