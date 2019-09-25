package problems.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciAlgorithm {
    private Map<Integer, Integer> memoizeTable;

    public FibonacciAlgorithm() {
        this.memoizeTable = new HashMap<>();
        this.memoizeTable.put(0, 0);
        this.memoizeTable.put(1, 1);
    }

    // O(n) running time
    public int fibonacciMemoize(int n) {
        if (this.memoizeTable.containsKey(n)) {
            return this.memoizeTable.get(n);
        }

        this.memoizeTable.put(n - 1, fibonacciMemoize(n - 1));
        this.memoizeTable.put(n - 2, fibonacciMemoize(n - 2));

        int calculatedNumber = memoizeTable.get(n - 1) + memoizeTable.get(n - 2);
        memoizeTable.put(n, calculatedNumber);

        return calculatedNumber;

    }

    // O(2^n) running time
    public int naiveFibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return (naiveFibonacci(n - 1) + naiveFibonacci(n - 2));
    }
}
