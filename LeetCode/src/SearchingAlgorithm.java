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

  public int recursiveLinearSearch(int[] numbers, int i, int x) {
    if (i > numbers.length - 1) {
      return -1;
    } else if (numbers[i] == x) {
      return i;
    } else {
      return recursiveLinearSearch(numbers, i + 1, x);
    }
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

  public int recursiveBinarySearch(int[] numbers, int start, int end, int x) {
    if (start > end) {
      return -1;
    } else {
      int mid = (start + end) / 2;
      if (numbers[mid] == x) {
        return mid;
      } else if (numbers[mid] > x) {
        return recursiveBinarySearch(numbers, start, mid - 1, x);
      } else {
        return recursiveBinarySearch(numbers, mid + 1, end, x);
      }
    }
  }

}
