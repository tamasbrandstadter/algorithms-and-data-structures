package problems.recursion;

public class Factorial {

    public int fact(int accumulator, int n) {
        if (n == 1) {
            return accumulator;
        }

        return fact(accumulator * n, n - 1);
    }

    public int factorial(int n) {
        return fact(1, n);
    }

}
