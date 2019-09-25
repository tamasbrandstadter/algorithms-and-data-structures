package problems.integral;

public class TrapezoidalRule {

    /**
     * h / 2 * [ f(x1) + 2 f(x2) ... + f(xN+1) ] = h * [ f(x1) / 2 + f(x2) ... + f(xN+1) / 2 ]
     */
    private static void trapezoidalIntegral(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0;
        sum += f(a) / 2;

        for (int index = 1; index < n; index++) {
            sum += f(a + index * h);
        }

        sum += f(b) / 2;

        double solution = sum * h;
        System.out.println("Integral solution: " + solution);
    }

    private static double f(double x) {
        return x * x;
    }

}
