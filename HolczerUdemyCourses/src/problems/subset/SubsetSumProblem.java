package problems.subset;

public class SubsetSumProblem {
    private boolean[][] dpTable;
    private int[] numbers;
    private int sum;

    public SubsetSumProblem(int[] numbers, int sum) {
        this.numbers = numbers;
        this.sum = sum;
        this.dpTable = new boolean[numbers.length + 1][sum + 1];
    }

    public void solveProblem() {
        for (int i = 0; i <= numbers.length; i++) { // if sum is 0 the we can make the empty subset to make sum 0
            dpTable[i][0] = true;
        }

        for (int rowIndex = 1; rowIndex <= numbers.length; rowIndex++) {
            for (int columnIndex = 1; columnIndex <= sum; columnIndex++) {
                if (columnIndex < numbers[rowIndex - 1]) {
                    dpTable[rowIndex][columnIndex] = dpTable[rowIndex - 1][columnIndex];
                } else {
                    if (dpTable[rowIndex - 1][columnIndex]) {
                        dpTable[rowIndex][columnIndex] = true;
                    } else {
                        dpTable[rowIndex][columnIndex] = dpTable[rowIndex - 1][columnIndex - numbers[rowIndex - 1]];
                    }
                }
            }
        }
    }

    public void hasSolution() {
        for (int i = 0; i < numbers.length + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                System.out.print(dpTable[i][j] + " ");
            }
            System.out.println();
        }

        if (dpTable[numbers.length][sum]) {
            System.out.println("There is a solution for the problem...");
        } else {
            System.out.println("No feasible solution for the problem...");
        }
    }

    public void showSums() {
        int columnIndex = sum;
        int rowIndex = numbers.length;

        while (columnIndex > 0 || rowIndex > 0) {
            if (this.dpTable[rowIndex][columnIndex] == dpTable[rowIndex - 1][columnIndex]) {
                rowIndex = rowIndex - 1;
            } else {
                System.out.println("We take item: " + numbers[rowIndex - 1]);
                columnIndex = columnIndex - numbers[rowIndex - 1];
                rowIndex = rowIndex - 1;
            }
        }
    }
}
