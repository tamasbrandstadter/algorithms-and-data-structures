package academy.learnprogramming.challenge;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SimpleHashTable {
  private StoredEmployee[] hashTable;

  public SimpleHashTable() {
    this.hashTable = new StoredEmployee[10];
  }

  public void put(String key, Employee employee) {
    int hashKey = hashKey(key);

    //linear probing
    if (isOccupied(hashKey)) {
      int stopIndex = hashKey;

      if (hashKey == hashTable.length - 1) {
        hashKey = 0;
      } else {
        hashKey++;
      }

      while (isOccupied(hashKey) && hashKey != stopIndex) {
        hashKey = (hashKey + 1) % hashTable.length;
      }
    }

    if (isOccupied(hashKey)) {
      System.out.println("Index is already taken by employee.");
    } else {
      hashTable[hashKey] = new StoredEmployee(key, employee);
    }
  }

  public Employee get(String key) {
    int hashKey = findKey(key);

    if (hashKey == -1) {
      return null;
    }

    return hashTable[hashKey].getEmployee();
  }

  public Employee remove(String key) {
    int hashKey = findKey(key);
    if (hashKey == -1) {
      return null;
    }

    Employee employee = hashTable[hashKey].getEmployee();
    hashTable[hashKey] = null;

    //rehashing
    StoredEmployee[] oldHashTable = hashTable;
    hashTable = new StoredEmployee[oldHashTable.length];

    IntStream.range(0, oldHashTable.length)
        .filter(i -> oldHashTable[i] != null)
        .forEachOrdered(i -> put(oldHashTable[i].getKey(), oldHashTable[i].getEmployee()));

    return employee;
  }

  public void print() {
    Arrays.stream(hashTable).forEach(System.out::println);
  }

  private int findKey(String key) {
    int hashKey = hashKey(key);
    if (hashTable[hashKey].getKey().equals(key)) {
      return hashKey;
    }

    int stopIndex = hashKey;
    if (hashKey == hashTable.length - 1) {
      hashKey = 0;
    } else {
      hashKey++;
    }

    while (!hashTable[hashKey].getKey().equals(key) && hashTable[hashKey] != null && hashKey != stopIndex) {
      hashKey = (hashKey + 1) % hashTable.length;
    }

    if (hashTable[hashKey] != null && hashTable[hashKey].getKey().equals(key)) {
      return hashKey;
    } else {
      System.out.println("Could not find key.");
      return -1;
    }
  }

  private boolean isOccupied(int index) {
    return hashTable[index] != null;
  }

  private int hashKey(String key) {
    return key.length() % hashTable.length;
  }
}
