package heap;

public class Heap {
    private Integer[] heap;
    private int currentPosition = -1;

    public Heap(int size) {
        heap = new Integer[size];
    }

    public void insert(int item) {
        if (isFull()) {
            throw new RuntimeException("Heap is full...");
        }
        heap[++currentPosition] = item;
        fixUp(currentPosition);
    }

    private void fixUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (parentIndex >= 0 && heap[parentIndex] < heap[index]) {
            int temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    public int getMax() {
        int result = heap[0];
        heap[0] = heap[currentPosition--];
        heap[currentPosition + 1] = null;
        fixDown(0, -1);
        return result;
    }

    public void heapsort() {
        for (int i = 0; i <= currentPosition; i++) {
            int temp = heap[0];
            this.heap[0] = heap[currentPosition - i];
            this.heap[currentPosition - i] = temp;
            fixDown(0, currentPosition - i - 1);
        }
    }

    private void fixDown(int index, int upTo) {
        if (upTo < 0) {
            upTo = currentPosition;
        }
        while (index <= upTo) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            if (leftChild <= upTo) {
                int childToSwap;
                if (rightChild > upTo) {
                    childToSwap = leftChild;
                } else {
                    childToSwap = (heap[leftChild] > heap[rightChild]) ? leftChild : rightChild;
                }
                if (heap[index] < heap[childToSwap]) {
                    int temp = heap[index];
                    heap[index] = heap[childToSwap];
                    heap[childToSwap] = temp;
                } else {
                    break;
                }
                index = childToSwap;
            } else {
                break;
            }
        }
    }

    private boolean isFull() {
        return currentPosition == heap.length;
    }

}

