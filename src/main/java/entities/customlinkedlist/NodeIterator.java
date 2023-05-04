package entities.customlinkedlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class NodeIterator<T> implements Iterator<T> {
    private static final Logger LOGGER = LogManager.getLogger(NodeIterator.class);
    Node<T> current;
    Node<T> nextNode;

    public NodeIterator(Node<T> head) {
        this.nextNode = head;
        this.current = head;
    }

    @Override
    public boolean hasNext() {
        LOGGER.debug("has next " + current);
        current = nextNode;
        return nextNode == null ? false : true;
    }

    @Override
    public T next() {
        nextNode = nextNode.getNodeNext();
        return current.getElement();
    }
}
