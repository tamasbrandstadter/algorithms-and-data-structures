package problems.recursion;

public class BinarySearch {
    private int[] numbers;

    public BinarySearch(int[] numbers) {
        this.numbers = numbers;
    }

    public int linearSearch(int item) {
        for (int i = 0; i < numbers.length; i++) {
            if (item == numbers[i]) {
                return i;
            }
        }

        return -1;
    }

    public int binarySearch(int item) {
        return binarySearch(0, numbers.length - 1, item);
    }

    private int binarySearch(int low, int high, int item) {
        if (high < low) {
            System.out.println("No solution found..");
            return -1;
        }

        int mid = low + ((high - low) / 2);

        if (item == numbers[mid]) {
            return mid;
        } else if (item < numbers[mid]) {
            return binarySearch(low, mid - 1, item);
        } else {
            return binarySearch(mid + 1, high, item);
        }
    }

}
