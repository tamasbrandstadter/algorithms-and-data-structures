public class SortingAlgorithm {

  public int[] bubbleSort(int[] nums) {
    for (int lastUnsortedIndex = nums.length - 1; lastUnsortedIndex >= 0; lastUnsortedIndex--) {
      for (int i = 0; i < lastUnsortedIndex; i++) {
        if (nums[i] > nums[i + 1]) {
          swap(nums, i, i + 1);
        }
      }
    }

    return nums;
  }

  public int[] selectionSort(int[] nums) {
    for (int lastUnsortedIndex = nums.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
      int largest = 0;

      for (int i = 1; i <= lastUnsortedIndex; i++) {
        if (nums[i] > nums[largest]) {
          largest = i;
        }
      }
      swap(nums, largest, lastUnsortedIndex);
    }
    return nums;
  }

  public int[] insertionSort(int[] nums) {
    for (int firstUnsortedIndex = 1; firstUnsortedIndex < nums.length; firstUnsortedIndex++) {
      int newElement = nums[firstUnsortedIndex];
      int i;

      for (i = firstUnsortedIndex; i > 0 && nums[i - 1] > newElement; i--) {
        nums[i] = nums[i - 1];
      }
      nums[i] = newElement;
    }
    return nums;
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }

    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public void mergeSort(int[] input, int start, int end) {
    if (end - start < 2) {
      return;
    }

    int mid = (start + end) / 2;

    mergeSort(input, start, mid);
    mergeSort(input, mid, end);
    merge(input, start, mid, end);
  }

  private void merge(int[] input, int start, int mid, int end) {
    if (input[mid - 1] <= input[mid]) {
      return;
    }

    int i = start;
    int j = mid;
    int tempIndex = 0;

    int[] temp = new int[end - start];

    while (i < mid && j < end) {
      temp[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++];
    }

    System.arraycopy(input, i, input, start + tempIndex, mid - i);
    System.arraycopy(temp, 0, input, start, tempIndex);
  }

}

