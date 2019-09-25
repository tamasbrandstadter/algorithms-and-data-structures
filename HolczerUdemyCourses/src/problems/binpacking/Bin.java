package problems.binpacking;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Bin {
    private int id;
    private int capacity;
    private int currentSize;
    private List<Integer> items;

    Bin(int capacity, int id) {
        this.capacity = capacity;
        this.id = id;
        this.items = new ArrayList<>();
    }

    boolean put(Integer item) {
        if (currentSize + item > capacity) {
            return false;
        }

        items.add(item);
        currentSize += item;

        return true;
    }

    @Override
    public String toString() {
        return items.stream()
            .map(item -> item + " ")
            .collect(joining("", "Items in bin #" + id + ": ", ""));
    }
}
