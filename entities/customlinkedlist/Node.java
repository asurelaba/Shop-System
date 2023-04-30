package customlinkedlist;

public class Node<T> {
    private T element;
    private Node nodeNext;

    public Node(T element) {
        this.element = element;
        this.nodeNext = null;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node getNodeNext() {
        return nodeNext;
    }

    public void setNodeNext(Node nodeNext) {
        this.nodeNext = nodeNext;
    }
}
