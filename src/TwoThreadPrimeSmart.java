import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TwoThreadPrimeSmart implements PrimeSolver {
    private Queue<Integer> myInputs;
    private List<Integer> myPrimes;
    
    @Override
    public void setInput(List<Integer> inputs) {
        myInputs = new LinkedList<>(inputs);
    }
    
    
    @Override
    public List<Integer> getPrimes() {
        findPrimes();
        return myPrimes;
    }
    
    /**
     * This is where we parallelize
     */
    private void findPrimes () {
        myPrimes = new ArrayList<Integer> ();
        
        // Create two threads
        Thread t1, t2;
        t1 = new Thread () {
            @Override
            public void run() {
                while (!myInputs.isEmpty()) {
                    Integer input;
                    synchronized(myInputs) {
                        input = myInputs.poll();
                    }
                    if (input == null) {
                        return;
                    }
                    if (SimplePrime.checkPrime (input)) {
                        synchronized(myPrimes) {
                            myPrimes.add(input);
                        }
                    }
                }
                
            }
        };
        
        t2 = new Thread () {
            @Override
            public void run() {
                while (!myInputs.isEmpty()) {
                    Integer input;
                    synchronized(myInputs) {
                        input = myInputs.poll();
                    }
                    if (input == null) {
                        return;
                    }
                    if (SimplePrime.checkPrime (input)) {
                        synchronized(myPrimes) {
                            myPrimes.add(input);
                        }
                    }
                }
            }
        };
        
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("interrupt caught");
        }
    }

}
