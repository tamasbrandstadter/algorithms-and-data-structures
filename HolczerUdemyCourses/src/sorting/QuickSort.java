package sorting;

public class QuickSort {

    // O(n log n) running time complexity
    public static void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivot = partition(nums, low, high);
        sort(nums, low, pivot - 1);
        sort(nums, pivot + 1, high);
    }

    private static int partition(int[] nums, int low, int high) {
        // random index maybe better
        int pivot = (low + high) / 2;

        // set the pivot to the end of list
        swap(nums, pivot, high);

        int i = low;
        for (int j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                swap(nums, i, j);
                i++;
            }
        }

        // swap back the pivot
        swap(nums, i, high);

        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
