package problems.repeated;

public class RepeatedIntegersProblem {

    public void solve(int[] nums) {
        // O(N)
        for (int i = 0; i < nums.length; i++) {
            // if this value is positive we have to flip
            if (nums[Math.abs(nums[i])] > 0) {
                nums[Math.abs(nums[i])] = -nums[Math.abs(nums[i])];
                // otherwise it is negative: it means repetition
            } else {
                System.out.println(Math.abs(nums[i]) + " is a repetition!");
            }
        }
    }

}
