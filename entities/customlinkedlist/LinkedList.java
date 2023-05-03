package customlinkedlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private static final Logger LOGGER = LogManager.getLogger(LinkedList.class);
    Node<T> head;
    Node<T> tail;
    int size = 0;

    public int getSize() {
        return size;
    }

    public void add(T element) {
        Node<T> node = new Node<T>(element);
        if (head == null) {
            head = node;
            tail = node;
            LOGGER.debug("first element" + element + " added to linked list");
            size++;
            return;
        }
        tail.setNodeNext(node);
        tail = node;
        size++;
        LOGGER.debug("element" + element + " added to linked list");
    }

    public void insert(int index, T element) {
        Node<T> current = head;
        Node<T> previous = null;
        int indexCounter = 0;
        Node<T> node = new Node<T>(element);
        if (index == 0) {
            node.setNodeNext(head);
            return;
        }
        while (current != null) {
            if (indexCounter == index) {
                node.setNodeNext(current);
                previous.setNodeNext(node);
                size++;
                return;
            }
            indexCounter++;
            previous = current;
            current = current.getNodeNext();
        }
        throw new IndexOutOfBoundsException("Index " + index + " is out of bound in the list of Size " + size);
    }

    public void remove(T element) {
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null) {
            if (current.getElement().equals(element)) {
                if (current.equals(head)) {
                    head = head.getNodeNext();
                } else {
                    previous.setNodeNext(current.getNodeNext());
                    if (current.equals(tail)) {
                        tail = previous;
                    }
                }
                size--;
                break;
            }
            previous = current;
            current = current.getNodeNext();
        }
    }

    public T get(int index) {
        Node<T> current = head;
        int indexCounter = 0;
        while (current != null) {
            if (indexCounter == index) {
                return current.getElement();
            }
            indexCounter++;
            current = current.getNodeNext();
        }
        return null;
    }

    @Override
    public String toString() {
        Node<T> current = head;
        StringBuffer listToString = new StringBuffer();
        while (current != null) {
            listToString.append(current.getElement().toString() + " ");
            current = current.getNodeNext();
        }
        return listToString.toString();
    }

    @Override
    public  NodeIterator<T> iterator() {
        return new NodeIterator<T>(head);
    }
}
