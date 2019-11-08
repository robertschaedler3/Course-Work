package homework.housingqueue;

public class MyQueue<E> {
    // Node class for single linked list queue
    private static class Node<E> {
        // Instance variables for Node
        private E data;
        private Node<E> next;

        // Constructor for Node, to be completed by you
        private Node(E data) {
            this.data = data;
            next = null;
        }

        // Constructor for Node, to be completed by you
        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    // Instance variables for queue
    private Node<E> front;
    private Node<E> rear;
    private int size;

    // Constructor for queue, to be completed by you
    public MyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Adds a node to queue, to be completed by you
    public boolean offer(E item) {
        if (front == null) { // empty queue
            front = new Node<E>(item);
            rear = front;
        } else if (front == rear) { // singleton queue
            front.next = new Node<E>(item);
            rear = front.next;
        } else { // two or more items in the queue
            rear.next = new Node<E>(item);
            rear = rear.next;
        }
        size++;
        return true;
    }

    // Returns the node at front of queue, to be completed by you
    public E peek() {
        if (front == null) {
            throw new IllegalStateException();
        }
        return front.data;
    }

    // Returns and removes the node at front of queue, to be completed by you
    public E poll() {
        if (front == null) {
            throw new IllegalStateException();
        }

        if (front == rear) { // singleton queue
            E temp = front.data;
            front = null;
            rear = null;
            size--;
            return temp;
        }

        E temp = front.data;
        front = front.next;
        size--;
        return temp;
    }

    // Returns the data element at a specific index, to be completed by you
    public E get(int index) {
        if (size == 0 || index > size - 1 || index < 0) {
            throw new IllegalStateException();
        }
        int i = 0;
        Node<E> current = front;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.data;
    }

    // Returns the size of the queue, to be completed by you
    public int size() {
        return size;
    }
}
