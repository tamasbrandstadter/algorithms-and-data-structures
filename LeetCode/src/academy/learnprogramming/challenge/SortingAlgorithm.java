package academy.learnprogramming.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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

  public int[] selectionSort2(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
      int minimum = i;

      for (int j = i + 1; j < nums.length; j++) {
        if (nums[j] < nums[minimum]) {
          minimum = j;
        }
      }

      swap(nums, i, minimum);
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

  public int[] insertionSort2(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      int number = nums[i];
      int j = i - 1;

      while (j >= 0 && nums[j] > number) {
        nums[j + 1] = nums[j];
        j--;
      }

      nums[j + 1] = number;
    }
    return nums;
  }

  public void recursiveInsertionSort(int[] nums, int numberOfElements) {
    if (numberOfElements < 2) {
      return;
    }

    recursiveInsertionSort(nums, numberOfElements - 1);

    int newElement = nums[numberOfElements - 1];
    int i;

    for (i = numberOfElements - 1; i > 0 && nums[i - 1] > newElement; i--) {
      nums[i] = nums[i - 1];
    }

    nums[i] = newElement;
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

  public void quickSort(int[] nums, int start, int end) {
    if (start < end) {
      int partition = getPartition(nums, start, end);

      quickSort(nums, start, partition - 1);
      quickSort(nums, partition + 1, end);
    }
  }

  private int getPartition(int[] nums, int start, int end) {
    int x = nums[end];
    int i = start - 1;

    for (int j = start; j <= end - 1; j++) {
      if (nums[j] <= x) {
        i++;
        swap(nums, i, j);
      }
    }

    nums[end] = nums[i + 1];
    nums[i + 1] = x;

    return i + 1;
  }

  public void quickSort2(int[] input, int start, int end) {
    if (end - start < 2) {
      return;
    }

    int pivotIndex = getPartition2(input, start, end);
    quickSort2(input, start, pivotIndex);
    quickSort2(input, pivotIndex + 1, end);
  }

  private int getPartition2(int[] input, int start, int end) {
    int pivot = input[start];
    int i = start;
    int j = end;

    while (i < j) {

      while (i < j && input[--j] >= pivot);
      if (i < j) {
        input[i] = input[j];
      }

      while (i < j && input[++i] <= pivot);
      if (i < j) {
        input[j] = input[i];
      }
    }

    input[j] = pivot;
    return j;
  }

  public void countingSort(int[] input, int min, int max) {
    int[] countArray = new int[(max - min) + 1];

    for (int i : input) {
      countArray[i - min]++;
    }

    int j = 0;

    for (int i = min; i <= max; i++) {
      while (countArray[i - min] > 0) {
        input[j++] = i;
        countArray[i - min]--;
      }
    }
  }

  public void bucketSort(int[] nums) {
    List<Integer>[] buckets = new ArrayList[10];

    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new ArrayList<>();
    }

    for (int num : nums) {
      buckets[hash(num)].add(num);
    }

    for (List<Integer> bucket : buckets) {
      Collections.sort(bucket);
    }

    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int value : buckets[i]) {
        nums[j++] = value;
      }
    }
  }

  public void bucketSortWithStream(int[] nums) {
    List<Integer>[] buckets = new ArrayList[10];

    Arrays.setAll(buckets, i -> new ArrayList<>());

    IntStream.range(0, nums.length)
        .forEachOrdered(i -> buckets[hash(i)].add(nums[i]));

    Arrays.stream(buckets)
        .forEachOrdered(Collections::sort);

    Arrays.stream(buckets)
        .forEachOrdered(bucket -> IntStream.range(0, bucket.size())
            .forEachOrdered(j -> nums[j] = bucket.get(j)));
  }

  private int hash(int i) {
    return i / 10 % 10;
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }

    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

}

