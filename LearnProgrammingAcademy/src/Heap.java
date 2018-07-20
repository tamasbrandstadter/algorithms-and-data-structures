public class Heap {
    private int[] heap;
    private int size;

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

    private void heapifyAbove(int index) {
        int newValue = heap[index];

        while (index > 0 && newValue > heap[getParent(index)]) {
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }

        heap[index] = newValue;
    }

    private boolean isFull() {
        return size == heap.length;
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }
}
