package academy.learnprogramming.challenge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class ChainedHashTable {
  private LinkedList<StoredEmployee>[] hashTable;

  public ChainedHashTable() {
    this.hashTable = new LinkedList[10];
    Arrays.setAll(hashTable, i -> new LinkedList<>());
  }

  public void put(String key, Employee employee) {
    int hashKey = hashKey(key);
    hashTable[hashKey].add(new StoredEmployee(key, employee));
  }

  public Employee get(String key) {
    int hashKey = hashKey(key);

    ListIterator<StoredEmployee> iterator = hashTable[hashKey].listIterator();

    StoredEmployee storedEmployee;
    while (iterator.hasNext()) {
      storedEmployee = iterator.next();

      if (storedEmployee.getKey().equals(key)) {
        return storedEmployee.getEmployee();
      }
    }

    return null;
  }

  public Employee remove(String key) {
    int hashKey = hashKey(key);

    LinkedList<StoredEmployee> storedEmployees = hashTable[hashKey];
    ListIterator<StoredEmployee> iterator = storedEmployees.listIterator();

    StoredEmployee storedEmployee;
    int index = -1;

    while (iterator.hasNext()) {
      storedEmployee = iterator.next();
      index++;

      if (storedEmployee.getKey().equals(key)) {
        hashTable[hashKey].remove(index);
        return storedEmployee.getEmployee();
      }
    }

    return null;
  }

  private int hashKey(String key) {
    return Math.abs(key.hashCode() % hashTable.length);
  }
  
}
