package problems.nqueens;

public class QueensProblem {
    private int[][] chessTable;
    private int numOfQueens;

    public QueensProblem(int numOfQueens) {
        this.chessTable = new int[numOfQueens][numOfQueens];
        this.numOfQueens = numOfQueens;
    }

    public void solve() {
        if (setQueens(0)) {
            printQueens();
        } else {
            System.out.println("There is no solution...");
        }
    }

    private boolean setQueens(int colIndex) {
        if (colIndex == numOfQueens) {
            return true;
        }

        for (int rowIndex = 0; rowIndex < numOfQueens; rowIndex++) {
            if (isValidPlace(rowIndex, colIndex)) {
                chessTable[rowIndex][colIndex] = 1;
                if (setQueens(colIndex + 1)) {
                    return true;
                }
                // backtracking
                chessTable[rowIndex][colIndex] = 0;
            }
        }

        return false;
    }

    private boolean isValidPlace(int rowIndex, int colIndex) {
        for (int i = 0; i < colIndex; i++) {
            if (chessTable[rowIndex][i] == 1) {
                return false;
            }
        }

        for (int i = rowIndex, j = colIndex; i >= 0 && j >= 0; i--, j--) {
            if (chessTable[i][j] == 1) {
                return false;
            }
        }

        for (int i = rowIndex, j = colIndex; i < chessTable.length && j >= 0; i++, j--) {
            if (chessTable[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    private void printQueens() {
        for (int[] ints : chessTable) {
            for (int j = 0; j < chessTable.length; j++) {
                if (ints[j] == 1) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" - ");
                }
            }

            System.out.println();
        }
    }
}
