package sorting;

public class CountingSort {

    public static void sort(int[] nums, int min, int max) {
        // allocate memory for the counter array O(k) memory complexity
        int[] counts = new int[max - min + 1];

        // O(n) consider all items in the original array
        for (int i : nums) {
            counts[i - min]++;
        }

        int z = 0;

        // count the occurrences in O(k) time
        for (int i = min; i <= max; i++) {
            while (counts[i - min] > 0) {
                nums[z] = i;
                z++;
                counts[i - min]--;
            }
        }
    }

}
