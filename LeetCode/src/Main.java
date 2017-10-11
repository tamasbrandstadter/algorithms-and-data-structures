import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) {

  }

  //Given an array of integers, return indices of the two numbers such that they add up to a specific target.
  //You may assume that each input would have exactly one solution, and you may not use the same element twice.
  private static int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 1; j >= i; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[] {i, j};
        }
      }
    }
    return null;
  }

  //Reverse digits of an integer.
  //The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.
  private static int reverseInteger(int x) {
    long reverse = 0;
    while (x != 0) {
      reverse = reverse * 10 + x % 10;
      x = x / 10;
    }
    if (reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE) {
      return 0;
    } else {
      return (int) reverse;
    }
  }

  //Determine whether an integer is a palindrome.
  private static boolean isPalindrome(int x) {
    if (String.valueOf(x).startsWith("-")) {
      x -= x + x;
    }
    return String.valueOf(x).equals(new StringBuilder(String.valueOf(x)).reverse().toString());
  }

  //Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
  private static int strStr(String haystack, String needle) {
    return haystack.contains(needle) ? haystack.indexOf(needle) : -1;
  }

  //Given a roman numeral, convert it to an integer.
  private static int romanToInt(String s) {
    Map<Character, Integer> romanNumberMap = new HashMap<>();
    romanNumberMap.put('I', 1);
    romanNumberMap.put('V', 5);
    romanNumberMap.put('X', 10);
    romanNumberMap.put('L', 50);
    romanNumberMap.put('C', 100);
    romanNumberMap.put('D', 500);
    romanNumberMap.put('M', 1000);

    int num = 0;
    int prev = 0;
    for (int i = s.length()-1; i >= 0; i--) {
      int temp = romanNumberMap.get(s.charAt(i));
      if (prev <= temp) {
        num += temp;
        prev = temp;
      } else {
        num -= temp;
        prev = temp;
      }
    }
    return num;
  }
}
