package substringsearch;

import java.util.HashMap;
import java.util.Map;

public class BoyerMoore {
    private String word;
    private String pattern;
    private Map<Character, Integer> mismatchShiftNumbers;

    public BoyerMoore(String word, String pattern) {
        this.word = word;
        this.pattern = pattern;
        this.mismatchShiftNumbers = new HashMap<>();
    }

    public void preComputeShifts() {
        int m = pattern.length();

        for (int i = 0; i < m; i++) {
            char c = pattern.charAt(i);
            int maxShift = Math.max(1, m - i - 1);
            mismatchShiftNumbers.put(c, maxShift);
        }
    }

    // running time complexity average O(n/m) ~ sub-linear
    // worst case O(n*m)
    public int search() {
        int n = word.length();
        int m = pattern.length();

        int skips;
        // increment the loop variable with pre-computed values
        for (int i = 0; i <= n - m; i += skips) {
            skips = 0;
            // iterate through the pattern in reverse order
            for (int j = m - 1; j >= 0; j--) {
                // mismatch: skips as either table value or pattern length
                if (pattern.charAt(j) != word.charAt(i + j)) {
                    if (mismatchShiftNumbers.containsKey(word.charAt(i + j))) {
                        skips = mismatchShiftNumbers.get(word.charAt(i + j));
                        break;
                    } else {
                        skips = m;
                        break;
                    }
                }
            }
            if (skips == 0) {
                return i;
            }
        }
        
        return -1;
    }

}
