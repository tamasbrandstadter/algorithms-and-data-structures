public class Main {
  public static void main(String[] args) {

  }

  //Given an array of integers, return indices of the two numbers such that they add up to a specific target.
  //You may assume that each input would have exactly one solution, and you may not use the same element twice.
  public static int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = 1; j >= i; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[]{i, j};
        }
      }
    }
    return null;
  }

  //Reverse digits of an integer.
  //The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.
  public static int reverseInteger(int x) {
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
  public static boolean isPalindrome(int x) {
    if (String.valueOf(x).startsWith("-")) {
      x -= x + x;
    }
    return String.valueOf(x).equals(new StringBuilder(String.valueOf(x)).reverse().toString());
  }
}
