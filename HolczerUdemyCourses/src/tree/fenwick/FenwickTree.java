package tree.fenwick;

// aka binary indexed tree
public class FenwickTree {
    private int[] tree;

    public FenwickTree(int[] nums) {
        this.tree = new int[nums.length + 1];
        construct(nums);
    }

    // the sum of the numbers in the interval [start:end]
    // O(log n) running time complexity
    public int rangeSum(int start, int end) {
        return sum(end) - sum(start - 1);
    }

    // update an existing item in the tree with the value
    public void update(int index, int num) {
        // we have to check all of the ranges and include the index
        while (index < tree.length) {
            tree[index] += num;
            // index of the items on the right
            index = next(index);
        }
    }

    // create the tree from the input array
    // O(n log n) running time complexity
    private void construct(int[] nums) {
        for (int index = 1; index <= nums.length; ++index) {
            update(index, nums[index - 1]);
        }
    }

    // the sum of the numbers in the range [0:index]
    // O(log n) running time complexity
    private int sum(int index) {
        // indices start with 0 but the theory/implementation starts with 1
        index++;

        int sum = 0;
        // we may consider the sum of multiple ranges
        while (index > 0) {
            // calculate sum
            sum += tree[index];
            // get the index of the parent
            index = parent(index);
        }

        return sum;
    }

    // index of the item on the left
    // O(1) running time complexity
    private int next(int index) {
        return index + (index & -index);
    }

    // index of the parent
    // O(1) running time complexity
    private int parent(int index) {
        return index - (index & -index);
    }

}
