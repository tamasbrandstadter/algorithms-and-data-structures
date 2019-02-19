package sorting;

// aka permutation sort or shotgun sort
public class BogoSort {

    /* O((n+1)!) running time complexity on "normal" computers
     * Although O(1) running time complexity on quantum computers, because of the quantum entanglement we can
     * search for all of the permutations simultaneously
     */
    public static void sort(int[] nums) {
        while (!isSorted(nums)) {
            shuffle(nums);
        }
    }

    private static boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                return false;
            }
        }

        return true;
    }

    /* Fisher-Yates algorithm in order to shuffle the array
     * The algorithm produces an unbiased permutation: every permutation is equally likely
     * O(n) running time proportional to the number of items we want to shuffle
     * In-place algorithm
     */
    private static void shuffle(int[] nums) {
        for (int i = nums.length - 1; i >= 0; --i) {
            int j = (int) (Math.random() * i);
            swap(nums, i, j);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
