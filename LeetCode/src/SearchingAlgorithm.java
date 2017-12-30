import java.util.Arrays;

public class SearchingAlgorithm {

  public int linearSearch(int[] nums, int target) {
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == target) {
        return i;
      }
    }
    return -1;
  }

  public int recursiveLinearSearch(int[] numbers, int i, int target) {
    if (i > numbers.length - 1) {
      return -1;
    } else if (numbers[i] == target) {
      return i;
    } else {
      return recursiveLinearSearch(numbers, i + 1, target);
    }
  }

  public int binarySearch(int[] numbers, int target) {
    Arrays.sort(numbers);

    int start = 0;
    int end = numbers.length - 1;

    while (start <= end) {
      int mid = (start + end) / 2;

      if (numbers[mid] > target) {
        end = mid - 1;
      } else if (numbers[mid] < target) {
        start = mid + 1;
      } else {
        return mid;
      }
    }

    return -1;
  }

  public int recursiveBinarySearch(int[] numbers, int start, int end, int target) {
    if (start > end) {
      return -1;
    } else {
      int mid = (start + end) / 2;
      if (numbers[mid] == target) {
        return mid;
      } else if (numbers[mid] > target) {
        return recursiveBinarySearch(numbers, start, mid - 1, target);
      } else {
        return recursiveBinarySearch(numbers, mid + 1, end, target);
      }
    }
  }

}
