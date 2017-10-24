import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.StrictMath.sqrt;
import static java.util.stream.LongStream.range;
import static java.util.stream.LongStream.rangeClosed;

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
    for (int i = s.length() - 1; i >= 0; i--) {
      int temp = romanNumberMap.get(s.charAt(i));
      if (prev <= temp) {
        num += temp;
      } else {
        num -= temp;
      }
      prev = temp;
    }
    return num;
  }

  //Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
  //For example:
  //Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
  private static int addDigits(int num) {
    int sum = 0;
    while (num > 0) {
      sum = sum + num % 10;
      num = num / 10;
    }
    //recursive call
    sum = (sum < 10) ? sum : addDigits(sum);
    return sum;
  }

  //How do I find the sum of all odd digits of user input numeric string?
  private static int sumOfOddNumbers(String input) {
    return input.chars()
        .map(n -> n - '0')
        .filter(n -> n % 2 == 1)
        .sum();
  }

  //Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
  private static int getSum(int a, int b) {
    return IntStream.of(a, b).sum();

    //other solutions would be:
    //return Math.addExact(a, b);
    //return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
  }

  //Count the number of primes until the given number.
  private static long countPrimes(int max) {
    return range(1, max)
        .parallel()
        .filter(Main::isPrime)
        .count();
  }

  private static boolean isPrime(long n) {
    return n > 1 && rangeClosed(2, (long) sqrt(n)).noneMatch(divisor -> n % divisor == 0);
  }

  //The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
  //Given two integers x and y, calculate the Hamming distance.
  private static int hammingDistance(int x, int y) {
    String binaryString = String.format("%" + Integer.toString(31) + "s", Integer.toBinaryString(x)).replace(" ", "0");
    String binaryString2 = String.format("%" + Integer.toString(31) + "s", Integer.toBinaryString(y)).replace(" ", "0");
    int count = 0;

    for (int i = 0; i < binaryString.length(); i++) {
      if (binaryString.charAt(i) != binaryString2.charAt(i)) {
        count++;
      }
    }
    return count;
  }

  //Proper solution for previous exercise would be:
  private static int hammingDistance2(int x, int y) {
    return Integer.bitCount(x ^ y);
  }

  //Given an array and a value, remove all instances of that value in place and return the new length.
  //Do not allocate extra space for another array, you must do this in place with constant memory.
  //The order of elements can be changed. It doesn't matter what you leave beyond the new length.
  //Given input array nums = [3,2,2,3], val = 3
  //Your function should return length = 2, with the first two elements of nums being 2.
  private static int removeElement(int[] nums, int val) {
    int m = 0;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[m] = nums[i];
        m++;
      }
    }
    return m;
  }

  //solution of previous example might be this, if array elements didn't matter
  private static int removeElement2(int[] nums, int val) {
    return (int) IntStream.of(nums).filter(value -> value != val).count();
  }

  //Write a program that outputs the string representation of numbers from 1 to n.
  //But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”.
  //For numbers which are multiples of both three and five output “FizzBuzz”.
  private static List<String> fizzBuzz(int n) {
    List<String> fizzBuzzList = new ArrayList<>();

    for (int i = 1; i <= n; i++) {
      if (i % 15 == 0) {
        fizzBuzzList.add("FizzBuzz");
      } else if (i % 3 == 0) {
        fizzBuzzList.add("Fizz");
      } else if (i % 5 == 0) {
        fizzBuzzList.add("Buzz");
      } else {
        fizzBuzzList.add(String.valueOf(i));
      }
    }
    return fizzBuzzList;
  }

  //partial solution with stream
  private static List<String> fizzBuzz2(int n) {
    return rangeClosed(1, n)
        .filter(value -> value % 3 == 0).mapToObj(value -> "Fizz")
        .collect(Collectors.toList());
  }

  //Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
  //Do not allocate extra space for another array, you must do this in place with constant memory.
  //For example given input array nums = [1,1,2],
  //Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
  private static int removeDuplicates(int[] nums) {
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
      if (nums[j] != nums[i]) {
        i++;
        nums[i] = nums[j];
      }
    }
    return i + 1;
  }

  //Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle,
  //which means it moves back to the original place.
  //The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down).
  //The output should be true or false representing whether the robot makes a circle.
  private static boolean judgeCircle(String moves) {
    if (moves.length() % 2 != 0) {
      return false;
    }
    int x = 0;
    int y = 0;

    for (int i = 0; i < moves.length(); i++) {
      if (moves.charAt(i) == 'U') {
        y += -1;
      } else if (moves.charAt(i) == 'D') {
        y += 1;
      } else if (moves.charAt(i) == 'L') {
        x += -1;
      } else if (moves.charAt(i) == 'R') {
        x += 1;
      }
    }
    return x == 0 && y == 0;
  }

  //Invert a binary tree.
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  //solution:
  private static TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return root;
    }

    TreeNode temp = root.right;
    root.right = root.left;
    root.left = temp;

    //recursions
    invertTree(root.left);
    invertTree(root.right);

    return root;
  }

  //You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
  //Given n, find the total number of full staircase rows that can be formed.
  //n is a non-negative integer and fits within the range of a 32-bit signed integer.
  private static int arrangeCoins(int n) {
    return (int) ((Math.sqrt(1 + 8.0 * n) - 1) / 2);
  }

  //Given an array of integers, every element appears twice except for one. Find that single one.
  //Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
  private static int singleNumber(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }

    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 1; i += 2) {
      if (nums[i] != nums[i + 1]) {
        return nums[i];
      }
    }
    return nums[nums.length - 1];
  }

  //Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
  //For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
  private static int hammingWeight(int n) {
    return Integer.bitCount(n);
  }

  //The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
  //Now your job is to find the total Hamming distance between all pairs of the given numbers.
  private static int totalHammingDistance(int[] nums) {
    int count = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        count += Integer.bitCount(nums[i] ^ nums[j]);
      }
    }
    return count;
  }

  //Previous exercise O(n)time and O(1)space solution would be:
  private static int totalHammingDistance2(int[] nums) {
    int sum = 0;
    for (int i = 0; i < 32; i++) {
      int bitCount = 0;
      for (int j = 0; j < nums.length; j++) {
        bitCount += ((nums[i] >> j) & 1);
      }
      sum += bitCount * (nums.length - bitCount);
    }
    return sum;
  }

  //Write a program to find the node at which the intersection of two singly linked lists begins.
  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  //solution:
  private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    Set<ListNode> nodeSet = new HashSet<>();

    ListNode a = headA;
    while (a != null) {
      nodeSet.add(a);
      a = a.next;
    }

    ListNode b = headB;
    while (b != null) {
      if (nodeSet.contains(b)) {
        return b;
      }
      b = b.next;
    }

    return null;
  }

  //Given an integer n, return the number of trailing zeroes in n!.
  private static int trailingZeroes(int n) {
    int sum = 0;
    int remainder = 0;
    while (n > remainder) {
      sum += n / 5;
      remainder = n % 5;
      n = n / 5;
    }
    return sum;
  }

  //Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
  //You may assume no duplicates in the array.
  private static int searchInsert(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] >= target) {
        return i;
      } else if (nums[nums.length - 1] < target) {
        return nums.length;
      }
    }
    return -1;
  }

  //Better solution of previous exercise would be:
  public static int searchInsert2(int[] nums, int target) {
    int low = 0, high = nums.length;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] < target)
        low = mid + 1;
      else
        high = mid;
    }
    return low;
  }

  //Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
  private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }

    //recursive calls:
    if (l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }
  }

  //Reverse a singly linked list.
  private static ListNode reverseList(ListNode head) {
    return reverseListInt(head, null);
  }

  private static ListNode reverseListInt(ListNode head, ListNode newHead) {
    if (head == null) {
      return newHead;
    }

    ListNode next = head.next;
    head.next = newHead;

    return reverseListInt(next, head);
  }

  //Write a function that takes a string as input and returns the string reversed.
  private static String reverseString(String s) {
    return new StringBuilder(s).reverse().toString();
  }

  //Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
  //Find all the elements of [1, n] inclusive that do not appear in this array.
  //Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
  private static List<Integer> disappearedNumbers(int[] nums) {
    return IntStream.rangeClosed(1, nums.length).filter(i -> IntStream.of(nums).noneMatch(j -> i == j)).boxed().collect(Collectors.toList());
  }

}

