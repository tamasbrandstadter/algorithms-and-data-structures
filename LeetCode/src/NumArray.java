/*Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
Your NumArray object will be instantiated and called as such:
NumArray obj = new NumArray(nums);
int param_1 = obj.sumRange(i,j)*/

public class NumArray {
  private int[] sum;

  private NumArray(int[] nums) {
    sum = new int[nums.length + 1];

    for (int i = 0; i < nums.length; i++) {
      sum[i + 1] = sum[i] + nums[i];
    }
  }

  private int sumRange(int i, int j) {
    return sum[j + 1] - sum[i];
  }
}
