import java.util.ArrayList;
import java.util.List;


/**
 * Find a list of prime numbers from a list of integers. 
 * 
 * @author andrew
 *
 */
public class SimplePrime implements PrimeSolver {
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
    
    public static boolean checkPrime (int check) {
        for (int i=2; i<=check/2; i++) {
            if (check % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    private void findPrimes () {
        myPrimes = new ArrayList<Integer> ();
        
        for (Integer input : myInputs) {
            if (checkPrime (input)) {
                myPrimes.add(input);
            }
        }
    }

}
