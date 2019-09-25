package sorting;

public class SelectionSort {

    // O(n * n) running time complexity
    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[index]) {
                    index = j;
                }
            }
            if (index != i) {
                swap(nums, i, index);
            }
        }
    }

    private static void swap(int[] nums, int i, int index) {
        int temp = nums[i];
        nums[i] = nums[index];
        nums[index] = temp;
    }

}
