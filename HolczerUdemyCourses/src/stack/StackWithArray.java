package stack;

public class StackWithArray<Item> {
    private Item[] stack;
    private int numberOfItems;

    public StackWithArray() {
        this.stack = (Item[]) new Object[1];
    }

    public void push(Item item) {
        if (numberOfItems == stack.length) {
            resize(2 * stack.length);
        }

        this.stack[numberOfItems++] = item;
    }

    public Item pop() {
        Item itemToPop = stack[--numberOfItems];

        if (numberOfItems > 0 && numberOfItems == stack.length / 4) {
            resize(stack.length / 2);
        }

        return itemToPop;
    }

    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    public int size() {
        return numberOfItems;
    }

    // O(n)
    private void resize(int capacity) {
        Item[] stackCopy = (Item[]) new Object[capacity];
        if (numberOfItems >= 0) {
            System.arraycopy(stack, 0, stackCopy, 0, numberOfItems);
        }

        stack = stackCopy;
    }
}

