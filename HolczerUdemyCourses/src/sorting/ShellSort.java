package sorting;

public class ShellSort {

    // O(n * n) running time complexity
    public void sort(int[] nums) {
        for (int gap = nums.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < nums.length; i++) {
                int num = nums[i];
                int j = i;
                while ((j >= gap) && (nums[j - gap] > num)) {
                    nums[j] = nums[j - gap];
                    j = j - gap;
                }
                nums[j] = num;
            }
        }
    }

}
