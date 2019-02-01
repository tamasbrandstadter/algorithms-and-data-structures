package cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private static final int CAPACITY = 5;
    private int actualSize;
    private Map<Integer, Node> map;
    private DoublyLinkedList linkedList;

    public LRUCache() {
        map = new HashMap<>();
        linkedList = new DoublyLinkedList();
    }

    public void put(int id, String data) {
        // update the node if already exists
        if (map.containsKey(id)) {
            Node node = map.get(id);
            node.setData(data);
            // update the node to be the head (cache feature)
            update(node);
            return;
        }
        // the data is not present in the cache so insert
        Node node = new Node(id, data);
        // we have to insert into the cache + set it to be the head node
        if (actualSize < CAPACITY) {
            actualSize++;
            add(node);
        } else {
            // or the cache is full: we have to remove the last item + insert the new one
            removeTail();
            add(node);
        }
    }

    // add node to the head
    private void add(Node node) {
        // the node will be the new head: so update accordingly
        node.setNext(linkedList.getHead());
        // it is the first node: no previousNode
        node.setPrevious(null);
        // we have to update the previous head: point to the new head (which is the node)
        if (linkedList.getHead() != null) {
            linkedList.getHead().setPrevious(node);
        }
        // update the head node
        linkedList.setHead(node);
        // if there is 1 node in the list: it is the head as well as the tail
        if (linkedList.getTail() == null) {
            linkedList.setTail(node);
        }
        // we have to update the map with the node
        map.put(node.getId(), node);
    }

    // remove last item (least frequently used)
    private void removeTail() {
        // get node from the map
        Node lastNode = map.get(linkedList.getTail().getId());
        // new tail node is the previous node (because we remove the actual one)
        linkedList.setTail(linkedList.getTail().getPrevious());
        // set the next node to be a NULL: because it is the right-most item
        if (linkedList.getTail() != null) {
            linkedList.getTail().setNext(null);
        }
        // avoid obsolete references
        lastNode = null;
    }

    // get the item with id + move to the head because we use this item
    public Node get(int id) {
        // the map does not contain the item [O(1) running time]
        if (!map.containsKey(id)) {
            return null;
        }
        // the map contains the item
        Node node = map.get(id);
        // move to the head (frequently visited items must be close to the head)
        update(node);
        return node;
    }

    // move the given node to the front (head)
    private void update(Node node) {
        // doubly linked list: we can get the previous node and the next node
        Node previousNode = node.getPrevious();
        Node nextNode = node.getNext();
        // so it is a middle node in the list
        if (previousNode != null) {
            previousNode.setNext(nextNode);
        } else {
            // we know that this is the head (first node)
            linkedList.setHead(nextNode);
        }
        // so not the last node
        if (nextNode != null) {
            nextNode.setPrevious(previousNode);
        } else {
            // we know it is the last node
            linkedList.setTail(previousNode);
        }
        // we have to move the node to the front
        add(node);
    }

    public void show() {
        Node actualNode = linkedList.getHead();
        // consider all the nodes in the linked list
        while (actualNode != null) {
            System.out.print(actualNode + "<->");
            actualNode = actualNode.getNext();
        }
    }

}
