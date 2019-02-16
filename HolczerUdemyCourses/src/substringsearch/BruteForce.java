package substringsearch;

public class BruteForce {

    // O(n*m) running time complexity
    public static int search(String word, String pattern) {
        int n = word.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (word.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }

        return -1;
    }

}
