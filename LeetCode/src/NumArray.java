/*Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
Your NumArray object will be instantiated and called as such:
NumArray obj = new NumArray(nums);
int param_1 = obj.sumRange(i,j)*/

public class NumArray {
  private int[] nums;

  private NumArray(int[] nums) {
    this.nums = nums;
  }

  private int sumRange(int i, int j) {
    int sum = 0;
    for (int k = i; k <= j; k++) {
      sum += nums[k];
    }
    return sum;
  }
}
