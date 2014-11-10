import java.util.ArrayList;
import java.util.List;


public class PrimeFinder {
    
    public static void main(String[] args) {
        List<Integer> inputs = new ArrayList<>();
        int numToCheck = 100;
//        int numToCheck = 300000;
        for (int i=0; i<numToCheck; i++) {
            inputs.add(i);
        }
        
//        runMultiThreadPrimeSmart (inputs, 4);
//        runTwoThreadPrimeSmart (inputs);
//        runTwoThreadPrime (inputs);
        runSimplePrime (inputs, true);
    }
    
    public static void runSimplePrime(List<Integer> inputs, boolean printPrimes) {
        List<Integer> primes;
        PrimeSolver primeSolver = new SimplePrime();
        
        primeSolver.setInput(inputs);
        
        long startTime = System.nanoTime();
        primes = primeSolver.getPrimes();
        long elapsedTime = System.nanoTime() - startTime;
        
        System.out.println("SimplePrime time: " + elapsedTime*1e-9);
        System.out.println("numPrimes: " + primes.size());
        
        if (printPrimes) {
            System.out.println("Primes:");
            for (Integer prime : primes) {
                System.out.print(prime + ", ");
            }
            System.out.println("");
        }
    }
    
    public static void runTwoThreadPrime (List<Integer> inputs) {
        List<Integer> primes;
        PrimeSolver primeSolver = new TwoThreadPrime ();
        primeSolver.setInput(inputs);
        
        long startTime = System.nanoTime();
        primes = primeSolver.getPrimes();
        long elapsedTime = System.nanoTime() - startTime;
        
        System.out.println("TwoThreadPrime time: " + elapsedTime*1e-9);
        System.out.println("numPrimes: " + primes.size());
    }
    
    public static void runTwoThreadPrimeSmart(List<Integer> inputs) {
        List<Integer> primes;
        PrimeSolver primeSolver = new TwoThreadPrimeSmart ();
        primeSolver.setInput(inputs);
        
        long startTime = System.nanoTime();
        primes = primeSolver.getPrimes();
        long elapsedTime = System.nanoTime() - startTime;
        
        System.out.println("TwoThreadPrimeSmart time: " + elapsedTime*1e-9);
        System.out.println("numPrimes: " + primes.size());
    }
    
    public static void runMultiThreadPrimeSmart(List<Integer> inputs, int numThreads) {
        List<Integer> primes;
        PrimeSolver primeSolver = new MultiThreadPrimeSmart (numThreads);
        primeSolver.setInput(inputs);
        
        long startTime = System.nanoTime();
        primes = primeSolver.getPrimes();
        long elapsedTime = System.nanoTime() - startTime;
        
        System.out.println("MultiThreadPrimeSmart("+numThreads+") time: " + elapsedTime*1e-9);
        System.out.println("numPrimes: " + primes.size());
    }

}
