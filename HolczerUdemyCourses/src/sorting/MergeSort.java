package sorting;

public class MergeSort {
    private int[] nums;
    private int[] temp;

    public MergeSort(int[] nums) {
        this.nums = nums;
        temp = new int[nums.length];
    }

    // O(n log n) running time complexity
    public void sort(int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;

        sort(low, mid);
        sort(mid + 1, high);
        merge(low, mid, high);
    }

    private void merge(int low, int middle, int high) {
        // copy nums[i] -> temp[i]
        if (high + 1 - low >= 0) {
            System.arraycopy(nums, low, temp, low, high + 1 - low);
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        // copy the smallest values from either the left or the right side back to the original array
        while ((i <= middle) && (j <= high)) {
            if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
            }

            k++;
        }

        // copy the rest of the left side of the array into the target array
        while (i <= middle) {
            nums[k] = temp[i];
            k++;
            i++;
        }
    }

}
