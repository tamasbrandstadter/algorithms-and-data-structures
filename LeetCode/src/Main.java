import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
    }

    // Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    // You may assume that each input would have exactly one solution, and you may not use the same element twice.
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

    // Reverse digits of an integer.
    // The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.
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

    // Determine whether an integer is a palindrome.
    private static boolean isPalindrome(int x) {
        if (String.valueOf(x).startsWith("-")) {
            x *= -1;
        }
        return String.valueOf(x).equals(new StringBuilder(String.valueOf(x)).reverse().toString());
    }

    // Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
    private static int strStr(String haystack, String needle) {
        return haystack.contains(needle) ? haystack.indexOf(needle) : -1;
    }

    // Given a roman numeral, convert it to an integer.
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

    // Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
    // For example:
    // Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
    private static int addDigits(int num) {
        int sum = 0;

        while (num > 0) {
            sum = sum + num % 10;
            num = num / 10;
        }

        sum = (sum < 10) ? sum : addDigits(sum);
        return sum;
    }

    // How do I find the sum of all odd digits of user input numeric string?
    private static int sumOfOddNumbers(String input) {
        return input.chars()
            .map(n -> n - '0')
            .filter(n -> n % 2 == 1)
            .sum();
    }

    // Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
    private static int getSum(int a, int b) {
        return IntStream.of(a, b).sum();
    }

    // Other solution would be:
    private static int getSum2(int a, int b) {
        return Math.addExact(a, b);
    }

    // With bitwise operations:
    private static int getSum3(int a, int b) {
        return b == 0 ? a : getSum3(a ^ b, (a & b) << 1);
    }

    // Count the number of prime numbers less than a non-negative number, n.
    private static int countPrimes(int n) {
        return (int) LongStream.range(1, n)
            .parallel()
            .filter(Main::isPrime)
            .count();
    }

    private static boolean isPrime(long n) {
        return n > 1 && LongStream.rangeClosed(2, (long) StrictMath.sqrt(n)).noneMatch(divisor -> n % divisor == 0);
    }

    // The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n).
    private static int countPrimesEfficient(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }

    // The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
    // Given two integers x and y, calculate the Hamming distance.
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

    // Proper solution for previous exercise would be:
    private static int hammingDistance2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    // Given an array and a value, remove all instances of that value in place and return the new length.
    // Do not allocate extra space for another array, you must do this in place with constant memory.
    // The order of elements can be changed. It doesn't matter what you leave beyond the new length.
    // Given input array nums = [3,2,2,3], val = 3
    // Your function should return length = 2, with the first two elements of nums being 2.
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

    // solution of previous example might be this, if array elements didn't matter
    private static int removeElement2(int[] nums, int val) {
        return (int) IntStream.of(nums).filter(value -> value != val).count();
    }

    // Write a program that outputs the string representation of numbers from 1 to n.
    // But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”.
    // For numbers which are multiples of both three and five output “FizzBuzz”.
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

    // partial solution with stream
    private static List<String> fizzBuzz2(int n) {
        return LongStream.rangeClosed(1, n)
            .filter(value -> value % 3 == 0)
            .mapToObj(value -> "Fizz")
            .collect(Collectors.toList());
    }

    // Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
    // Do not allocate extra space for another array, you must do this in place with constant memory.
    // For example given input array nums = [1,1,2],
    // Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
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

    // Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle,
    // which means it moves back to the original place.
    // The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down).
    // The output should be true or false representing whether the robot makes a circle.
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

    // Given a binary tree, find its maximum depth.
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(root.getLeft());
            int rightDepth = maxDepth(root.getRight());

            return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
        }
    }

    // Given a binary tree, find its minimum depth.
    private int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        }
        if (root.getLeft() == null) {
            return minDepth(root.getRight()) + 1;
        }
        if (root.getRight() == null) {
            return minDepth(root.getLeft()) + 1;
        }

        return Math.min(minDepth(root.getLeft()), minDepth(root.getRight())) + 1;
    }

    // Invert a binary tree.
    private static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode temp = root.getRight();
        root.setRight(root.getLeft());
        root.setLeft(temp);

        invertTree(root.getLeft());
        invertTree(root.getRight());

        return root;
    }

    // Given a binary tree, return the inorder traversal of its nodes' values.
    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();

        visit(root, values);

        return values;
    }

    private static void visit(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }

        visit(root.getLeft(), values);
        values.add(root.getValue());
        visit(root.getRight(), values);
    }

    // Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> treeValues = new ArrayList<>();

        if (root == null) {
            return treeValues;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);

        while (!queue.isEmpty()) {
            List<Integer> levelValues = new LinkedList<>();
            int nodeCount = queue.size();

            for (int i = 0; i < nodeCount; i++) {
                TreeNode visitedNode = queue.poll();
                levelValues.add(visitedNode.getValue());

                if (visitedNode.getLeft() != null) {
                    queue.add(visitedNode.getLeft());
                }
                if (visitedNode.getRight() != null) {
                    queue.add(visitedNode.getRight());
                }
            }

            treeValues.add(levelValues);
        }

        return treeValues;
    }

    // Given two binary trees, write a function to check if they are the same or not.
    // Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
    private static boolean isSameTree(TreeNode p, TreeNode q) {
        return p == null && q == null || p != null && q != null && p.getValue() == q.getValue()
            && isSameTree(p.getLeft(), q.getLeft())
            && isSameTree(p.getRight(), q.getRight());
    }

    // You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
    // Given n, find the total number of full staircase rows that can be formed.
    // n is a non-negative integer and fits within the range of a 32-bit signed integer.
    private static int arrangeCoins(int n) {
        return (int) ((Math.sqrt(1 + 8.0 * n) - 1) / 2);
    }

    // Given an array of integers, every element appears twice except for one. Find that single one.
    // Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
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

    // Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
    // For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
    private static int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    // The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
    // Now your job is to find the total Hamming distance between all pairs of the given numbers.
    private static int totalHammingDistance(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                count += Integer.bitCount(nums[i] ^ nums[j]);
            }
        }
        return count;
    }

    // Previous exercise O(n)time and O(1)space solution would be:
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

    // Write a program to find the node at which the intersection of two singly linked lists begins.
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

    // Given an integer n, return the number of trailing zeroes in n!.
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

    // Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
    // You may assume no duplicates in the array.
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

    // Better solution of previous exercise would be:
    public static int searchInsert2(int[] nums, int target) {
        int low = 0;
        int high = nums.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // Reverse a singly linked list.
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

    // Remove all elements from a linked list of integers that have value val.
    private static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode pointer = head;

        while (pointer.next != null) {
            if (pointer.next.val == val) {
                pointer.next = pointer.next.next;
            } else {
                pointer = pointer.next;
            }
        }
        return head.val == val ? head.next : head;
    }

    // Write a function that takes a string as input and returns the string reversed.
    private static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
    // Find all the elements of [1, n] inclusive that do not appear in this array.
    // Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
    private static List<Integer> disappearedNumbers(int[] nums) {
        return IntStream.rangeClosed(1, nums.length).filter(i -> IntStream.of(nums).noneMatch(j -> i == j)).boxed().collect(Collectors.toList());
    }

    // O(n) runtime solution would be marking elements as negative. In this way all the numbers that we have seen will be marked as negative.
    // In the second iteration, if a value is not marked as negative, it implies we have never seen that index before, so just add it to the return list.
    private static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if (nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }
        return list;
    }

    // Given an array of integers, find if the array contains any duplicates.
    // Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
    private static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);

        for (int n : nums) {
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }

    // Given an integer, write a function to determine if it is a power of three.
    private static boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    // Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
    // A simple method is to take log of the given number on base 4, and if we get an integer then number is power of 4.
    private static boolean isPowerOfFour(int n) {
        return Math.log(n) / Math.log(4) % 1 == 0;
    }

    // Given two strings s and t which consist of only lowercase letters.
    // String t is generated by random shuffling string s and then add one more letter at a random position.
    // Find the letter that was added in t.
    private static char findTheDifference(String s, String t) {
        char res = 0;

        for (char c : s.toCharArray()) {
            res ^= c;
        }

        for (char d : t.toCharArray()) {
            res ^= d;
        }
        return res;
    }

    // other solution:
    private static char findTheDifference2(String s, String t) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        char[] chars2 = t.toCharArray();
        Arrays.sort(chars2);

        for (int i = 0; i <= chars.length - 1; i++) {
            if (chars[i] != chars2[i]) {
                return chars2[i];
            }
        }

        return chars2[chars2.length - 1];
    }

    // Write a function to find the longest common prefix string amongst an array of strings.
    private static String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();

        if (strs != null && strs.length > 0) {
            Arrays.sort(strs);

            char[] a = strs[0].toCharArray();
            char[] b = strs[strs.length - 1].toCharArray();

            for (int i = 0; i < a.length; i++) {
                if (a[i] == b[i] && i <= b.length) {
                    result.append(b[i]);
                } else {
                    return result.toString();
                }
            }
        }
        return result.toString();
    }

    // Given a sorted linked list, delete all duplicates such that each element appear only once.
    private static ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;

        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    // Given a positive integer num, write a function which returns True if num is a perfect square else False.
    private static boolean isPerfectSquare(int num) {
        int i = 1;

        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    // Compute and return the square root of x. x is guaranteed to be a non-negative integer.
    private static int mySqrt(int x) {
        long r = x;

        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

    // Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
    // The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    // Write a function that takes a string as input and reverse only the vowels of a string.
    private static String reverseVowels(String s) {
        String vowels = "aeiouAEIOU";
        List<Character> inputVowelsReversed = new ArrayList<>();
        char[] chars = s.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            if (vowels.contains(chars[i] + "")) {
                inputVowelsReversed.add(chars[i]);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0, j = 0; i < s.length(); i++) {
            if (!vowels.contains(s.charAt(i) + "")) {
                sb.append(s.charAt(i));
            } else {
                sb.append(inputVowelsReversed.get(j));
                j++;
            }
        }

        return sb.toString();
    }

    /* Given a word, you need to judge whether the usage of capitals in it is right or not.
    We define the usage of capitals in a word to be right when one of the following cases holds:
    All letters in this word are capitals, like "USA".
    All letters in this word are not capitals, like "leetcode".
    Only the first letter in this word is capital if it has more than one letter, like "Google".
    Otherwise, we define that this word doesn't use capitals in a right way. */
    private static boolean detectCapitalUse(String word) {
        return word.matches("^[A-Z][A-Z]*[A-Z]*$") || word.matches("^[A-Z][a-z]*[a-z]$") || word.matches("^[a-z][a-z]*[a-z]*$");
    }

    // Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true
    // if the ransom note can be constructed from the magazines; otherwise, it will return false.
    // Each letter in the magazine string can only be used once in your ransom note.
    private static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            map.put(c, count);
        }

        for (char c : ransomNote.toCharArray()) {
            int count = map.getOrDefault(c, 0) - 1;
            if (count < 0) {
                return false;
            }
            map.put(c, count);
        }

        return true;
    }

    // Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
    private static int findComplement(int num) {
        String binaryString = Integer.toBinaryString(num);
        String complement = binaryString.replace('1', '2').replace('0', '1').replace('2', '0');

        return Integer.parseInt(complement, 2);
    }

    // You are given a string representing an attendance record for a student. The record only contains the following three characters:
    // 'A' : Absent. 'L' : Late. 'P' : Present.
    // A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
    // You need to return whether the student could be rewarded according to his attendance record.
    private static boolean checkRecord(String s) {
        Map<Character, Integer> characterMap = new HashMap<>();

        for (char c : s.toCharArray()) {
            characterMap.put(c, characterMap.getOrDefault(c, 0) + 1);
        }

        return (!characterMap.containsKey('A') || characterMap.get('A') <= 1) && !s.contains("LLL");
    }

    // Given a linked list, determine if it has a cycle in it.
    private static boolean hasCycle(ListNode head) {
        Set<ListNode> nodes = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            if (nodes.contains(current)) {
                return true;
            } else {
                nodes.add(current);
            }
            current = current.next;
        }

        return false;
    }

    // Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
    private static ListNode detectCycle(ListNode head) {
        Set<ListNode> nodes = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            if (nodes.contains(current)) {
                return current;
            } else {
                nodes.add(current);
            }
            current = current.next;
        }

        return null;
    }

    // Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
    // Your algorithm should have a linear runtime complexity.
    private static int singleNumber2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        Arrays.sort(nums);

        if (nums.length == 4) {
            return nums[nums.length - 1];
        }

        for (int i = 0; i < nums.length - 1; i += 3) {
            if (nums[i] != nums[i + 1] || nums[i] != nums[i + 2]) {
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }

    /* Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
    You may assume the integer do not contain any leading zero, except the number 0 itself.
    The digits are stored such that the most significant digit is at the head of the list. */
    private static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] nums = new int[digits.length + 1];
        nums[0] = 1;

        return nums;
    }

    // Given an array of size n, find the majority element. The majority element is the element that appears more than n/2 times.
    private static int majorityElement(int[] nums) {
        int majority = nums.length / 2;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[i + majority]) {
                return nums[i];
            }
        }
        return -1;
    }

    // simpler solution would be:
    private static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /* Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
    The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
    Please note that your returned answers (both index1 and index2) are not zero-based.
    You may assume that each input would have exactly one solution and you may not use the same element twice. */
    private static int[] twoSum2(int[] numbers, int target) {
        Map<Integer, Integer> sumMap = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int distinction = target - numbers[i];
            if (sumMap.containsKey(distinction)) {
                return new int[] {sumMap.get(distinction) + 1, i + 1};
            }
            sumMap.put(numbers[i], i);
        }

        return new int[] {};
    }

    // Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
    private static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // Given a singly linked list, determine if it is a palindrome.
    private static boolean isPalindrome(ListNode head) {
        Stack<ListNode> nodes = new Stack<>();

        ListNode current = head;
        while (current != null) {
            nodes.push(current);
            current = current.next;
        }

        ListNode node = head;
        while (!nodes.empty() && node != null) {
            if (nodes.pop().val != node.val) {
                return false;
            }
            node = node.next;
        }

        return true;
    }

    // You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit.
    // Add the two numbers and return it as a linked list
    // You may assume the two numbers do not contain any leading zero, except the number 0 itself.
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1.val == 0 && l2.val == 0) {
            return new ListNode(0);
        }
        Deque<Integer> firstListStack = new LinkedList<>();
        Deque<Integer> secondListStack = new LinkedList<>();

        while (l1 != null) {
            firstListStack.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            secondListStack.push(l2.val);
            l2 = l2.next;
        }

        int power = 0;
        int sum = 0;
        while (!firstListStack.isEmpty() || !secondListStack.isEmpty()) {
            if (!firstListStack.isEmpty()) {
                sum += (int) (firstListStack.pop() * (Math.pow(10, power)));
            }
            if (!secondListStack.isEmpty()) {
                sum += (int) (secondListStack.pop() * (Math.pow(10, power)));
            }
            power++;
        }

        ListNode head = new ListNode(0);
        while (sum != 0) {
            ListNode next = new ListNode(sum % 10);
            next.next = head.next;
            head.next = next;
            sum /= 10;
        }

        return head.val == 0 ? head.next : head;
    }

    // Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
    private static boolean hasAlternatingBits(int n) {
        char[] binaryChars = Integer.toBinaryString(n).toCharArray();

        Stack<Character> bits = new Stack<>();

        for (char c : binaryChars) {
            bits.push(c);
        }

        while (bits.size() != 1) {
            if (bits.pop() == bits.peek()) {
                return false;
            }
        }
        return true;
    }

    // Given two arrays, write a function to compute their intersection.
    // solution using streams:
    private static int[] intersection(int[] nums1, int[] nums2) {
        return IntStream.of(nums1).filter(n -> IntStream.of(nums2).anyMatch(i -> i == n)).distinct().toArray();
    }

    // Given two arrays, write a function to compute their intersection.
    // solution using sets:
    private static int[] intersectionWithSet(int[] nums1, int[] nums2) {
        Set<Integer> integers = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();

        for (int i : nums1) {
            integers.add(i);
        }

        for (int j : nums2) {
            if (integers.contains(j)) {
                intersection.add(j);
            }
        }

        return intersection.stream().mapToInt(x -> x).toArray();
    }

    // Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
    // solution with binary search:
    private static int missingNumber(int[] nums) {
        Arrays.sort(nums);

        int start = 0;
        int end = nums.length - 1;
        int mid;

        while (start < end) {
            mid = (end - start) / 2;

            if (nums[mid] > mid) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    // solution with Gauss-formula:
    private static int missingNumberWithGaussFormula(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;

        for (int i : nums) {
            actualSum += i;
        }

        return expectedSum - actualSum;
    }

    // solution with streams:
    private static int missingNumberWithStream(int[] nums) {
        return IntStream.rangeClosed(0, nums.length).filter(i -> IntStream.of(nums).noneMatch(j -> i == j)).findFirst().orElse(-1);
    }

    // Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
    // If the last word does not exist, return 0.
    private static int lengthOfLastWord(String s) {
        String trimmed = s.trim();
        int count = 0;

        for (int i = trimmed.length() - 1; i >= 0; i--) {
            if (trimmed.charAt(i) != ' ') {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    // Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
    // solution: Kadane's Algorithm
    private static int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currentMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }

        return maxSoFar;
    }

    // Given a linked list, remove the nth node from the end of list and return its head.
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode(0);
        temp.next = head;

        ListNode current = head;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }

        current = temp;
        int stepsToMake = size - n;
        while (stepsToMake > 0) {
            stepsToMake--;
            current = current.next;
        }

        current.next = current.next.next;

        return temp.next;
    }

    /* Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
    Your algorithm's runtime complexity must be in the order of O(log n).
    If the target is not found in the array, return [-1, -1]. */
    private static int[] searchRange(int[] nums, int target) {
        int upper = findBound(nums, target, true);
        int lower = findBound(nums, target, false);

        return new int[] {lower, upper};
    }

    private static int findBound(int[] nums, int target, boolean upper) {
        int start = 0;
        int end = nums.length - 1;
        int res = -1;

        while (start <= end) {
            int mid = (end + start) / 2;

            if (nums[mid] == target) {
                res = mid;
                if (upper) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return res;
    }

    /* You are a product manager and currently leading a team to develop a new product.
    Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version,
    all the versions after a bad version are also bad. Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
    which causes all the following ones to be bad. You are given an API bool isBadVersion(version) which will return whether version is bad
    Implement a function to find the first bad version. You should minimize the number of calls to the API. */
    private static int firstBadVersionRecursively(int n) {
        if (isBadVersion(n)) {
            return n;
        } else {
            return firstBadVersionRecursively(n - 1);
        }
    }

    private static int findBadVersionBinary(int n) {
        int start = 1;
        int end = n;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private static boolean isBadVersion(int version) {
        return version == 1;
    }

    /* We are playing the Guess Game. The game is as follows:
    I pick a number from 1 to n. You have to guess which number I picked.
    Every time you guess wrong, I'll tell you whether the number is higher or lower. */
    private static int guessNumber(int n) {
        int left = 1;
        int right = n;

        SecureRandom random = new SecureRandom();
        int randomNumberToFind = random.nextInt(n);
        System.out.println("I thought a number...");

        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("My guess is " + mid + ".");

            int response = guess(randomNumberToFind, mid);

            if (response == 0) {
                System.out.println("You got it.");
                return mid;
            } else if (response == -1) {
                System.out.println("My number is lower.");
                right = mid - 1;
            } else {
                System.out.println("My number is higher.");
                left = mid + 1;
            }
        }

        return left;
    }

    private static int guess(int numberToGuess, int guess) {
        return Integer.compare(numberToGuess, guess);
    }

    // You are climbing a stair case. It takes n steps to reach to the top.
    // Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
    // recursive solution:
    private static int climbStairsRecursively(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else {
            return climbStairsRecursively(n - 2) + climbStairsRecursively(n - 1);
        }
    }

    // Fibonacci-number solution:
    private static int climbStairsWithFibonacciNumber(int n) {
        if (n == 1) {
            return 1;
        }

        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }

        return second;
    }

    // Fibonacci-formula solution:
    private static int climbStairsWithFibonacciFormula(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibonacciNumber = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) (fibonacciNumber / sqrt5);
    }

    // Determine whether a string is a palindrome. Strings may contain punctuation and spaces, they should be ignored, just like case.
    private static boolean checkForPalindrome(String s) {
        Deque<Character> stack = new LinkedList<>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (char c : s.toLowerCase().toCharArray()) {
            if (alphabet.contains(c + "")) {
                stack.push(c);
            }
        }

        while (stack.size() != 1 && !stack.isEmpty()) {
            if (stack.pop() != stack.removeLast()) {
                return false;
            }
        }

        return true;
    }

    /* You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones.
    The one who removes the last stone will be the winner. You will take the first turn to remove the stones.
    Both of you are very clever and have optimal strategies for the game.
    Write a function to determine whether you can win the game given the number of stones in the heap. For example, if there are 4 stones in the heap,
    then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend. */
    private static boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    // Given two strings s and t, write a function to determine if t is an anagram of s.
    private static boolean isAnagram(String s, String t) {
        char[] a = s.toCharArray();
        Arrays.sort(a);

        char[] a1 = t.toCharArray();
        Arrays.sort(a1);

        return Arrays.equals(a, a1);
    }

    /* In a given integer array nums, there is always exactly one largest element.
    Find whether the largest element in the array is at least twice as much as every other number in the array.
    If it is, return the index of the largest element, otherwise return -1. */
    private static int dominantIndex(int[] nums) {
        int maxIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != maxIndex && nums[maxIndex] < (nums[i] * 2)) {
                return -1;
            }
        }

        return maxIndex;
    }

    /* Given two lists A and B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order of the elements in A.
    We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at index j.
    These lists A and B may contain duplicates. If there are multiple answers, output any of them. */
    private static int[] anagramMappings(int[] a, int[] b) {
        List<Integer> aList = Arrays.stream(a).boxed().collect(Collectors.toList());
        List<Integer> bList = Arrays.stream(b).boxed().collect(Collectors.toList());

        int[] mappings = new int[a.length];

        for (int i = 0; i < aList.size(); i++) {
            if (bList.contains(aList.get(i))) {
                mappings[i] = bList.indexOf(aList.get(i));
            }
        }

        return mappings;
    }

    // Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
    // Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
    // The order of output does not matter.
    private static List<Integer> findAnagrams(String s, String p) {
        List<Integer> indices = new ArrayList<>();

        char[] chars = p.toCharArray();
        Arrays.sort(chars);

        for (int i = 0; i <= s.length() - p.length(); i++) {
            char[] temp = s.substring(i, i + p.length()).toCharArray();
            Arrays.sort(temp);

            if (Arrays.equals(chars, temp)) {
                indices.add(i);
            }
        }

        return indices;
    }

    // Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
    private static int firstUniqueChar(String s) {
        int[] frequency = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            frequency[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (frequency[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    // Given an input string, reverse the string word by word. Given s = "the sky is blue" return "blue is sky the".
    private static String reverseWords(String s) {
        String[] words = s.trim().split(" +");

        Collections.reverse(Arrays.asList(words));

        return String.join(" ", words);
    }

    /* You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
    Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels. The letters in J are guaranteed distinct,
    and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A". */
    private static int numJewelsInStones(String j, String s) {
        Map<Character, Integer> characters = new HashMap<>();

        for (char c : s.toCharArray()) {
            characters.put(c, characters.getOrDefault(c, 0) + 1);
        }

        int counter = 0;
        for (int i = 0; i < j.length(); i++) {
            char key = j.charAt(i);
            if (characters.containsKey(key)) {
                counter += characters.get(key);
            }
        }

        return counter;
    }

    // Given a string, sort it in decreasing order based on the frequency of characters.
    private static String frequencySort(String s) {
        Map<Character, Integer> characters = new HashMap<>();

        for (char c : s.toCharArray()) {
            characters.put(c, characters.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> sortedMapEntries =
            characters.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Character, Integer> sortedMapEntry : sortedMapEntries) {
            int times = sortedMapEntry.getValue();
            while (times != 0) {
                sb.append(sortedMapEntry.getKey());
                times--;
            }
        }

        return sb.toString();
    }

    // Given scores of N athletes, find their relative ranks and the people with the top three highest scores,
    // who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".
    private static String[] findRelativeRanks(int[] nums) {
        int[] scores = new int[nums.length];
        System.arraycopy(nums, 0, scores, 0, nums.length);
        Arrays.sort(scores);

        Map<Integer, String> scoreToMedal = new LinkedHashMap<>();

        for (int i = scores.length - 1, j = 3; i >= 0; i--) {
            if (i == scores.length - 1) {
                scoreToMedal.put(scores[i], "Gold Medal");
            } else if (i == scores.length - 2) {
                scoreToMedal.put(scores[i], "Silver Medal");
            } else if (i == scores.length - 3) {
                scoreToMedal.put(scores[i], "Bronze Medal");
            } else {
                scoreToMedal.put(scores[i], String.format("%d", ++j));
            }
        }

        String[] results = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            results[i] = scoreToMedal.get(nums[i]);
        }

        return results;
    }

    /* Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
    For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
    You must do this in-place without making a copy of the array. Minimize the total number of operations. */
    private static void moveZeroes(int[] nums) {
        int nonZeroElementCount = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroElementCount++] = nums[i];
            }
        }

        for (int i = nonZeroElementCount; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn)
    // which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
    private static int arrayPairSumStream(int[] nums) {
        Arrays.sort(nums);

        return IntStream.iterate(0, i -> i <= nums.length - 1, i -> i + 2)
            .map(i -> nums[i])
            .sum();
    }

    private static int arrayPairSumIterative(int[] nums) {
        Arrays.sort(nums);

        int sum = 0;
        for (int i = 0; i <= nums.length - 1; i += 2) {
            sum += nums[i];
        }

        return sum;
    }

    // Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
    // For example, with A = "abcd" and B = "cdabcdab".
    // Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
    private static int repeatedStringMatch(String a, String b) {
        if (a.equals(b) || a.contains(b)) {
            return 1;
        }

        StringBuilder sb = new StringBuilder(a);
        int count = 1;

        while (sb.length() < (a.length() + b.length())) {
            sb.append(a);
            count++;
            if (sb.toString().contains(b)) {
                return count;
            }
        }

        return -1;
    }

    // Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation
    // and return them as an array. Example: num = 5 you should return [0,1,1,2,1,2].
    private static int[] countBits(int num) {
        int[] bits = new int[num + 1];

        for (int i = 0; i <= num; i++) {
            bits[i] = Integer.bitCount(i);
        }

        return bits;
    }

    // Given a list of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
    private static String[] findWords(String[] words) {
        Map<Character, Integer> keyboard = new HashMap<>();
        String chars = "qwertyuiop|asdfghjkl|zxcvbnm";

        int line = 1;
        for (int i = 0; i < chars.length(); i++) {
            if (chars.charAt(i) == '|') {
                line++;
                continue;
            }
            keyboard.put(chars.charAt(i), line);
        }

        List<String> list = new ArrayList<>();

        for (String word : words) {
            int index = keyboard.get(word.toLowerCase().charAt(0));

            for (int i = 1; i < word.length(); i++) {
                if (keyboard.get(word.toLowerCase().charAt(i)) != index) {
                    index = -1;
                    break;
                }
            }
            if (index != -1) {
                list.add(word);
            }
        }

        return list.toArray(new String[] {});
    }

    // Given a binary array, find the maximum number of consecutive 1s in this array.
    private static int findMaxConsecutiveOnes(int[] nums) {
        int maxSoFar = 0;
        int currentMax = 0;

        for (int num : nums) {
            if (num == 1) {
                currentMax++;
                if (currentMax > maxSoFar) {
                    maxSoFar = currentMax;
                }
            } else {
                currentMax = 0;
            }
        }
        return maxSoFar;
    }

    // Given a list of words, each word can be written as a concatenation of the Morse code of each letter.
    // For example, "cab" can be written as "-.-.-....-", (which is the concatenation "-.-." + "-..." + ".-").
    // We'll call such a concatenation, the transformation of a word.
    // Return the number of different transformations among all words we have.
    private static int uniqueMorseRepresentations(String[] words) {
        if (words.length == 0) {
            return 0;
        }

        if (words.length == 1) {
            return 1;
        }

        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String[] codedAlphabet = new String[] {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        Map<Character, String> mappings = new HashMap<>();
        for (int i = 0; i < codedAlphabet.length; i++) {
            mappings.put(alphabet[i], codedAlphabet[i]);
        }

        String join = String.join(",", words);

        Set<String> transformations = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < join.length(); i++) {
            if (join.charAt(i) == ',') {
                transformations.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(mappings.get(join.charAt(i)));
            }
        }

        transformations.add(sb.toString());

        return transformations.size();
    }

    /* Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
    To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
    To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0 */
    private static int[][] flipAndInvertImage(int[][] A) {
        int[][] flipped = new int[A.length][A.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] == 1) {
                    flipped[i][A.length - 1 - j] = 0;
                } else {
                    flipped[i][A.length - 1 - j] = 1;
                }
            }
        }

        return flipped;
    }

    // Given two integers, find their greatest common divisor.
    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    // Given a number n, write a function that returns true when it is a perfect number and false when it is not.
    private static boolean isPerfectNumber(long number) {
        List<Long> factors = findFactors(number);
        long sum = 0;

        for (Long factor : factors) {
            sum += factor;
        }

        return sum == number;
    }

    private static List<Long> findFactors(long number) {
        List<Long> factors = new LinkedList<>();
        factors.add(1L);

        for (long i = 2; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                factors.add(i);
                factors.add(number / i);
            }
        }

        return factors;
    }

    // Given a integer n, write a function that returns the n-th Fibonacci-number.
    private static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return fibonacci(n - 2) + fibonacci(n - 1);
    }

}
