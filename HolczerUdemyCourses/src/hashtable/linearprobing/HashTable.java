package hashtable.linearprobing;

public class HashTable<Key, Value> {
    private static final int TABLE_SIZE = 10;
    private Key[] keys;
    private Value[] values;
    private int numOfItems;
    private int capacity;

    public HashTable() {
        keys = (Key[]) new Object[TABLE_SIZE];
        values = (Value[]) new Object[TABLE_SIZE];
        capacity = TABLE_SIZE;
        numOfItems = 0;
    }

    // have to define a different constructor because when we resize the table we use it
    public HashTable(int capacity) {
        this.capacity = capacity;
        this.keys = (Key[]) new Object[capacity];
        this.values = (Value[]) new Object[capacity];
        this.numOfItems = 0;
    }

    public int size() {
        return numOfItems;
    }

    public boolean isEmpty() {
        return numOfItems == 0;
    }

    public Value get(Key key) {
        if (key == null) {
            return null;
        }

        // we have to calculate an array index from the key
        int index = hash(key);
        // we have to consider the items right after each other because the item could have been shifted down
        // because of the linear probing
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            // have to check the next slot / array bucket
            index = (index + 1) % capacity;
        }
        // search miss: no value with the given key
        return null;
    }

    public int hash(Key key) {
        return key.hashCode() % capacity;
    }

    public void put(Key key, Value value) {
        if (key == null || value == null) {
            return;
        }
        // load factor is 0.75: so when the table is 75% full we resize it --> double its size
        // why? when it is nearly empty --> we waste a lot of memory for no reason
        // when it is nearly full --> there will be lots of collisions --> O(1) will reduce to O(n)
        if (numOfItems >= capacity * 0.75) {
            resize(2 * capacity);
        }

        int index = hash(key);
        // maybe there is a collision and there is already an item inserted to that given index, so we have to
        // find an empty slot --> hence the condition != null
        while (keys[index] != null) {
            // update section: we update the value when the keys are equal
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
            index = (index + 1) % capacity;
        }
        // we have managed to found the array index where we can insert the value --> so update accordingly
        keys[index] = key;
        values[index] = value;
        numOfItems++;
    }

    public void remove(Key key) {
        if (key == null) {
            return;
        }

        int index = hash(key);
        // first we have to find the item we want to get rid of
        while (!keys[index].equals(key)) {
            index = (index + 1) % capacity;
        }
        // we delete the item: the key + the value
        keys[index] = null;
        values[index] = null;
        // we have to reconstruct the table starting from the item deleted: there is a "hole" in the table,
        // the get() method will not work properly otherwise
        index = (index + 1) % capacity;
        while (keys[index] != null) {
            Key tempKey = keys[index];
            Value tempValue = values[index];
            keys[index] = null;
            values[index] = null;
            // we have to decrement the size, because with the put() method it will be increased again
            numOfItems--;
            put(tempKey, tempValue);
            index = (index + 1) % capacity;
        }
        numOfItems--;
        if (numOfItems <= capacity / 3) {
            resize(capacity / 2);
        }
    }

    private void resize(int newCapacity) {
        System.out.println("Resize table with new capacity: " + newCapacity);
        HashTable<Key, Value> newTable = new HashTable<>(newCapacity);
        // have to rehash the table entries because the hash function relies heavily on the
        // size: ~ key.hashCode() % sizeOfTable
        // so it is a O(n) operation --> we should make as few resize operation as possible
        for (int i = 0; i < capacity; ++i) {
            if (keys[i] != null) {
                newTable.put(keys[i], values[i]);
            }
        }

        keys = newTable.getKeys();
        values = newTable.getValues();
        capacity = newTable.getCapacity();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Key[] getKeys() {
        return keys;
    }

    public void setKeys(Key[] keys) {
        this.keys = keys;
    }

    public Value[] getValues() {
        return values;
    }

    public void setValues(Value[] values) {
        this.values = values;
    }
}

