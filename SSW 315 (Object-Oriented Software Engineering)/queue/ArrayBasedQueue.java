package lab.queue;

public class ArrayBasedQueue<E> implements QueueADT<E> {

    private int size;
    private E[] data;
    private int front;
    private int rear;

    public ArrayBasedQueue() {
        size = 0;
        data = (E[]) new Object[10];
        front = 0;
        rear = 0;
    }

    public void enqueue(E element) {
        if (size == 0) {
            data[front] = element;
        } else if (size == 1) {

        }
    }

    public E dequeue() {
        return null;
    }

    public E first() {
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public String toString() {
        return null;
    }

}