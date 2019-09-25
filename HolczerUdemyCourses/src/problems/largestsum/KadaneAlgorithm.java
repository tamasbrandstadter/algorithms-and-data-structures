package problems.largestsum;

public class KadaneAlgorithm {

    public int solve(int[] nums) {
        // initialize the algorithm with the first item in the array
        int globalMax = nums[0];
        int currentMax = nums[0];

        // it has an O(N) linear running time complexity
        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            if (currentMax > globalMax) {
                globalMax = currentMax;
            }
        }
        return globalMax;
    }
}
