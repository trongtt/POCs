package t3.pocs.javase.concurrent;

import junit.framework.TestCase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFutureTask extends TestCase {

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
        future.run();

        try {
            Integer result = future.get();
            assertEquals(1, result.intValue());
        } catch (Exception e) {
            fail("It should NOT have exception");
        }
    }

    public void testFutureTask_2() {
        FutureTask<Integer> future = new FutureTask<Integer>(new CallableTask());

        // Cancelling code before run

        boolean b = future.cancel(true);
        assertTrue(b);

        future.run();

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
    }

    public void testFutureTask_3() {
        FutureTask<Integer> future = new FutureTask<Integer>(new CallableTask());
        
        future.run();
        
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
    }

    /**
     * The {@link Callable#call()} is only executed once,
     * even {@link FutureTask#run()} and {@link FutureTask#get()} are called twice.
     * 
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void testFutureTask_4() throws InterruptedException, ExecutionException {
        FutureTask<Integer> future = new FutureTask<Integer>(new CallableTask());

        // run() twice
        future.run();
        future.run();

        // Even get() twice 
        Integer result = future.get();
        assertEquals(1, result.intValue());

        result = future.get();
        assertEquals(1, result.intValue());
    }
    
    class CallableTask implements Callable<Integer> {
        int counter = 0;
        
        public Integer call() throws Exception {
            
            System.out.println("Executing call() !!!");
            
            /*
             * if(1==1)
             * 
             * throw new java.lang.Exception("Thrown from call()");
             */
            
            return ++counter;
            
        }
        
    }
}