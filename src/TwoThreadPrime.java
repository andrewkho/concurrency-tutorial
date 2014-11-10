import java.util.ArrayList;
import java.util.List;

public class TwoThreadPrime implements PrimeSolver {
    private List<Integer> myInputs;
    private List<Integer> myPrimes;
    
    @Override
    public void setInput(List<Integer> inputs) {
        myInputs = new ArrayList<>(inputs);
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
                for (int i=0; i<myInputs.size()/2; i++) {
                    if (SimplePrime.checkPrime (myInputs.get(i))) {
                        //synchronized(myPrimes) {
                            myPrimes.add(myInputs.get(i));
                        //}
                    }
                }
                
            }
        };
        
        t2 = new Thread () {
            @Override
            public void run() {
                for (int i = myInputs.size()/2; i<myInputs.size(); i++) {
                    if (SimplePrime.checkPrime (myInputs.get(i))) {
                        //synchronized(myPrimes) {
                            myPrimes.add(myInputs.get(i));
                        //}
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
