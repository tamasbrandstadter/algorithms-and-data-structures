package problems.quickselect;

import java.util.Random;

public class Quickselect {
    private int[] nums;

    public Quickselect(int[] nums) {
        this.nums = nums;
    }

    public int select(int k) {
        return select(0, nums.length - 1, k - 1);
    }

    private int partition(int first, int last) {
        int pivot = new Random().nextInt(last - first + 1) + first;

        swap(last, pivot);

        for (int i = first; i < last; i++) {
            if (nums[i] > nums[last]) {
                swap(i, first);
                first++;
            }
        }

        swap(first, last);

        return first;
    }

    private int select(int first, int last, int k) {
        int pivot = partition(first, last);

        if (pivot > k) {
            return select(first, pivot - 1, k);
        } else if (pivot < k) {
            return select(pivot + 1, last, k);
        }

        return nums[k];
    }

    private void swap(int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
