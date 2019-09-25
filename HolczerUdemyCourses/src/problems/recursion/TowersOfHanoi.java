package problems.recursion;

public class TowersOfHanoi {

    public void solveHanoiProblem(int n, char from, char mid, char to) {
        if (n == 1) {
            System.out.println("Plate 1 from " + from + " to " + to);
            return;
        }

        solveHanoiProblem(n - 1, from, to, mid);
        System.out.println("Plate " + n + " from " + from + " to " + to);
        solveHanoiProblem(n - 1, mid, from, to);
    }

}
