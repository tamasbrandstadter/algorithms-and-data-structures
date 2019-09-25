package problems.coinchange;

public class CoinChange {

    // recursive coin change exponential running time O(2^n)
    public int naiveCoinChange(int m, int[] v, int index) {
        if (m < 0) {
            return 0;
        }
        if (m == 0) {
            return 1;
        }
        if (v.length == index) {
            return 0;
        }

        return naiveCoinChange(m - v[index], v, index) + naiveCoinChange(m, v, index + 1);
    }

    // O(n) running time
    public void dynamicProgrammingCoinChange(int[] v, int m) {
        int[][] dpTable = new int[v.length + 1][m + 1];

        for (int i = 0; i <= v.length; i++) {
            dpTable[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            dpTable[0][i] = 0;
        }

        // O(v*m)
        for (int i = 1; i <= v.length; i++) {
            for (int j = 1; j <= m; j++) {
                if (v[i - 1] <= j) {
                    dpTable[i][j] = dpTable[i - 1][j] + dpTable[i][j - v[i - 1]];
                } else {
                    dpTable[i][j] = dpTable[i - 1][j];
                }
            }
        }

        System.out.println("Solution: " + dpTable[v.length][m]);
    }
}
