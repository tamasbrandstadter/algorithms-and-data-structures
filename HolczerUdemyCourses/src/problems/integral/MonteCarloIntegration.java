package problems.integral;

import java.util.Random;

public class MonteCarloIntegration {
    private static Random random = new Random();
    private static double R = 1;
    private static double INTERVAL_START = -1;
    private static double INTERVAL_END = 1;

    private static void monteCarlo(int numOfIteration) {
        double insideCounter = 0;

        for (int i = 0; i < numOfIteration; i++) {
            double randomX = getRandom();
            double randomY = getRandom();
            if (isInside(randomX, randomY)) {
                insideCounter++;
            }
        }

        double approximatedArea = (insideCounter / numOfIteration) * getMaxArea();
        System.out.println("Integrated area is approximately: " + approximatedArea);
    }

    private static double getMaxArea() {
        return ((INTERVAL_END - INTERVAL_START) * (INTERVAL_END - INTERVAL_START));
    }

    private static double getRandom() {
        return INTERVAL_START + (INTERVAL_END - INTERVAL_START) * random.nextDouble();
    }

    private static boolean isInside(double x, double y) {
        return (x * x + y * y) < R * R;
    }

}
