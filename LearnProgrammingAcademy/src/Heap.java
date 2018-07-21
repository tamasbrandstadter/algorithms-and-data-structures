public class Heap {
    private int[] heap;
    private int size;

    // constructs a max heap with initial capacity
    public Heap(int capacity) {
        this.heap = new int[capacity];
    }

    public void insert(int value) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Heap is full");
        }
        heap[size] = value;
        heapifyAbove(size);
        size++;
    }

    public int delete(int index) {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Heap is empty");
        }

        int deletedValue = heap[index];
        int parent = getParent(index);

        heap[index] = heap[size - 1];

        if (index == 0 || heap[index] < heap[parent]) {
            heapifyBelow(index, size - 1);
        } else {
            heapifyAbove(index);
        }

        size--;
        return deletedValue;
    }

    public void sort() {
        int lastHeapIndex = size - 1;

        for (int i = 0; i < lastHeapIndex; i++) {
            int temp = heap[0];
            heap[0] = heap[lastHeapIndex - i];
            heap[lastHeapIndex - i] = temp;
            heapifyBelow(0, lastHeapIndex - i - 1);
        }
    }

    private void heapifyAbove(int index) {
        int newValue = heap[index];

        // shift parents down, assign value once
        while (index > 0 && newValue > heap[getParent(index)]) {
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }

        heap[index] = newValue;
    }

    private void heapifyBelow(int index, int lastHeapIndex) {
        int childToSwap;

        while (index <= lastHeapIndex) {
            // get index of children
            int leftChild = getChild(index, true);
            int rightChild = getChild(index, false);

            // child index is inside the heap, else break
            if (leftChild <= lastHeapIndex) {
                // if this condition applies then there is no right child, we swap left child
                // else we have to decide which is bigger of the two children
                if (rightChild > lastHeapIndex) {
                    childToSwap = leftChild;
                } else {
                    childToSwap = heap[leftChild] > heap[rightChild] ? leftChild : rightChild;
                }

                // then swap or break out of loop in case no more swapping is necessary
                if (heap[index] < heap[childToSwap]) {
                    int temp = heap[index];
                    heap[index] = heap[childToSwap];
                    heap[childToSwap] = temp;
                } else {
                    break;
                }

                // update index for loop
                index = childToSwap;
            } else {
                break;
            }
        }
    }

    private boolean isFull() {
        return size == heap.length;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private int getChild(int index, boolean left) {
        return index * 2 + (left ? 1 : 2);
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }

}
