import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MultiThreadPrimeSmart implements PrimeSolver {
    private Queue<Integer> myInputs;
    private List<Integer> myPrimes;
    
    private final int myNumThreads;
    
    public MultiThreadPrimeSmart (int numThreads) {
        myNumThreads = numThreads;
    }
    
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
        
        // Create many threads
        Thread[] threads = new Thread[myNumThreads];
        for (int i=0; i<myNumThreads; i++) {
            threads[i] = new Thread () {
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
        }
        
        for (int i=0; i<myNumThreads; i++) {
            threads[i].start();
        }
        try {
            for (int i=0; i<myNumThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("interrupt caught");
        }
    }

}
