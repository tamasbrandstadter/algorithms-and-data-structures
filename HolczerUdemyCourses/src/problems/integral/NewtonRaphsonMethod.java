package problems.integral;

public class NewtonRaphsonMethod {
    private static final double ERROR = 0.001;

    private static void newtonMethod() {
        double x = 5;
        while (Math.abs(f(x)) > ERROR) {
            x = x - (f(x) / df(x));
        }
        System.out.println("Approximated root: " + x);
    }

    private static double f(double x) {
        return x * x - 10;
    }

    private static double df(double x) {
        return 2 * x;
    }

}
