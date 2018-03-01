import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] queue;
  private int n;

  public RandomizedQueue() {
    this.queue = (Item[]) new Object[1];
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public int size() {
    return n;
  }

  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }

    if (n == queue.length) {
      resize(2 * queue.length);
    }
    queue[n++] = item;
  }

  public Item dequeue() {
    if (n == 0) {
      throw new NoSuchElementException();
    }
    Item item = queue[--n];
    queue[n] = null;

    if (n > 0 && n == queue.length / 4) {
      resize(queue.length / 2);
    }

    return item;
  }

  public Item sample() {
    if (n == 0) {
      throw new NoSuchElementException();
    }

    return queue[StdRandom.uniform(n)];
  }

  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];

    for (int i = 0; i < n; i++) {
      copy[i] = queue[i];
    }
    queue = copy;
  }

  @Override
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private int index;
    private Item[] temp;

    public ListIterator() {
      this.temp = (Item[]) new Object[n];
      for (int i = 0; i < queue.length; i++) {
        if (queue[i] != null) {
          temp[i] = queue[i];
        }
      }
      StdRandom.shuffle(temp);
    }

    @Override
    public boolean hasNext() {
      return index != temp.length;
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return temp[index++];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

}
