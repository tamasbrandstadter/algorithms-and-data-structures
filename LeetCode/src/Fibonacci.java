import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

// Create a method for calculating the n-th Fibonacci-number.
public final class Fibonacci {
    // using memoization
    private static Map<Integer, BigInteger> cache = new HashMap<>();

    // base cases
    static {
        cache.put(0, BigInteger.ZERO);
        cache.put(1, BigInteger.ONE);
    }

    private Fibonacci() {
    }

    // O(n) solution
    public static BigInteger calculate(int n) {
        return cache.computeIfAbsent(n, x -> calculate(x - 2).add(calculate(x - 1)));
    }

}
