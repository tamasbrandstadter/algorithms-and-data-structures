package sorting;

import java.util.Arrays;

public class RadixSort {

    // O(n) running time complexity
    public static void sort(int[] nums) {
        // linear search for the max item
        int max = Arrays.stream(nums).max().orElse(Integer.MIN_VALUE);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            count(nums, exp);
        }
    }

    private static void count(int[] nums, int exp) {
        int[] count = new int[10];
        int[] output = new int[nums.length];

        // store count of occurrences in count[]
        for (int i = 0; i < nums.length; i++) {
            count[(nums[i] / exp) % 10]++;
        }

        // change count[i] so that count[i] now contains actual position of this digit in output[]
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // build the output array
        for (int i = nums.length - 1; i >= 0; i--) {
            output[count[(nums[i] / exp) % 10] - 1] = nums[i];
            count[(nums[i] / exp) % 10]--;
        }

        // copy the output array to nums, so that it contains sorted numbers according to current digit
        Arrays.setAll(nums, i -> output[i]);
    }

}
