import java.util.Arrays;

public class SearchingAlgorithm {

  public int linearSearch(int[] nums, int x) {
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == x) {
        return i;
      }
    }
    return -1;
  }

  public int binarySearch(int[] numbers, int x) {
    Arrays.sort(numbers);

    int start = 0;
    int end = numbers.length - 1;

    while (start <= end) {
      int mid = (start + end) / 2;

      if (numbers[mid] > x) {
        end = mid - 1;
      } else if (numbers[mid] < x) {
        start = mid + 1;
      } else {
        return mid;
      }
    }

    return -1;
  }

}
