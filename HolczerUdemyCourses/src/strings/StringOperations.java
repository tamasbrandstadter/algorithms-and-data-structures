package strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringOperations {

    // running time complexity using StringBuilder: O(n)
    // using a String would be O(n * n)
    public static String reverse(String s) {
        // O(1)
        int n = s.length();

        StringBuilder reversed = new StringBuilder();
        // O(n)
        for (int i = n - 1; i >= 0; i--) {
            // O(1)
            reversed.append(s.charAt(i));
        }

        return reversed.toString();
    }

    // running time complexity using a String: O(n)
    // using a StringBuilder would be O(n * n)
    public static List<String> suffixes(String s) {
        // O(1)
        int n = s.length();

        List<String> suffixes = new ArrayList<>();
        // O(n)
        for (int i = 0; i < n; i++) {
            // substring is O(1)
            suffixes.add(s.substring(i, n));
        }

        return suffixes;
    }

    // running time complexity: O(n)
    public static List<String> prefixes(String s) {
        // O(1)
        int n = s.length();

        List<String> prefixes = new ArrayList<>();
        // O(n)
        for (int i = 1; i < n + 1; i++) {
            // O(1)
            prefixes.add(s.substring(0, i));
        }

        return prefixes;
    }

    // running time complexity: O(m)
    public static String longestCommonPrefix(String s, String s2) {
        // O(1)
        int m = Math.min(s.length(), s2.length());

        // O(m)
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != s2.charAt(i)) {
                // O(1)
                return s.substring(0, i);
            }
        }

        // O(1)
        return s.substring(0, m);
    }

    // running time complexity: O(n log n)
    public static String longestRepeatedSubstring(String s) {
        // O(1)
        int n = s.length();

        // O(n)
        List<String> suffixes = suffixes(s);
        // O(n log n)
        Collections.sort(suffixes);

        String longestRepeatedSubstring = "";
        // O(n)
        for (int i = 0; i < n - 1; i++) {
            String temp = longestCommonPrefix(suffixes.get(i), suffixes.get(i + 1));
            if (temp.length() > longestRepeatedSubstring.length()) {
                longestRepeatedSubstring = temp;
            }
        }

        return longestRepeatedSubstring;
    }

}
