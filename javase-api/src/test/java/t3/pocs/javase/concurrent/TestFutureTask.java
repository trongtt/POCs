package t3.pocs.javase.concurrent;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFutureTask extends TestCase {

    public static final LinkedList<String> events = new LinkedList<String>();

    @Override
    protected void tearDown() throws Exception {
      events.clear();
    }

    /**
     * Just testing environment and mock objects
     * 
     * @throws Exception
     */
    public void testSetup() throws Exception {
        CallableTask t = new CallableTask();
        t.call();
        t.call();
        assertEquals(2, t.counter);
    }
    
    public void testFutureTask() {
        FutureTask<Integer> future = new FutureTask<Integer>(new CallableTask());
        events.add("beforeRun");
        future.run();
        events.add("afterRun");

        try {
            Integer result = future.get();
            assertEquals(1, result.intValue());
        } catch (Exception e) {
            fail("It should NOT have exception");
        }

        assertEquals(Arrays.asList("beforeRun", "run", "afterRun"), events);
    }

    public void testFutureTask_2() {
        FutureTask<Integer> future = new FutureTask<Integer>(new CallableTask());

        // Cancelling code before run
        boolean b = future.cancel(true);
        assertTrue(b);

        events.add("beforeRun");
        future.run();
        events.add("afterRun");

        Integer result = null;
        try {
            result = future.get();
            fail("It shouldn't run into there");
        } catch (java.util.concurrent.CancellationException e) {
            assertNull(result);
        } catch (Exception e) {
            fail("It shouldn't run into there");
        }

        assertTrue(future.isDone());
        assertTrue(future.isCancelled());

        assertEquals(Arrays.asList("beforeRun", "afterRun"), events);
    }

    public void testFutureTask_3() {
        FutureTask<Integer> future = new FutureTask<Integer>(new CallableTask());

        events.add("beforeRun");
        future.run();
        events.add("afterRun");
        
        boolean b = future.cancel(true);
        assertFalse(b);

        Integer result = null;
        try {
            result = future.get();
            assertEquals(1, result.intValue());
        } catch (Exception e) {
            fail("It shouldn't run into there");
        }
        
        assertTrue(future.isDone());
        assertFalse(future.isCancelled());

        assertEquals(Arrays.asList("beforeRun", "run", "afterRun"), events);
    }

    /**
     * The computation from {@link Callable#call()} is only executed once,
     * even {@link FutureTask#run()} and {@link FutureTask#get()} are called twice.
     * 
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void testFutureTask_4() throws InterruptedException, ExecutionException {
        FutureTask<Integer> future = new FutureTask<Integer>(new CallableTask());

        events.add("beforeRun");
        // run() twice
        future.run();
        future.run();
        events.add("afterRun");

        Integer result = future.get();
        assertEquals(1, result.intValue());

        // The computation will NOT be performed again if it has completed.
        result = future.get();
        assertEquals(1, result.intValue());

        assertEquals(Arrays.asList("beforeRun", "run", "afterRun"), events);
    }

    public void testFutureTask_5() throws InterruptedException, ExecutionException {
      FutureTask<String> future = new FutureTask<String>(new HeavyWordloadTask(), "done");
      events.add("beforeRun");
      new Thread(future).start(); // Perform the task in other thread.
      events.add("afterRun");

      String result = future.get(); // It blocks the process until the computation is completed.
      events.add("gotResult");
      assertEquals("done", result);

      assertEquals(Arrays.asList("beforeRun", "afterRun", "run", "gotResult"), events);
    }
    
    class CallableTask implements Callable<Integer> {
        int counter = 0;
        
        public Integer call() throws Exception {
            events.add("run");
            return ++counter;
        }
        
    }

    class HeavyWordloadTask implements Runnable {
      @Override
      public void run() {
        try {
          // Sleeps 1000ms, like it is performing a heavy task.
          Thread.sleep(1000);
          events.add("run");
          
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
}