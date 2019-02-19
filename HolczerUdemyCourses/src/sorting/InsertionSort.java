package sorting;

public class InsertionSort {

    // O(n*n) running time complexity
    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int j = i;
            while ((j > 0) && (nums[j - 1] > nums[j])) {
                swap(nums, j, j - 1);
                --j;
            }
        }
    }

    private static void swap(int[] nums, int j, int i) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
