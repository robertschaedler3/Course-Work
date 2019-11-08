package lab.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;

/**
 * This class implements a binary heap data structure by extending the ArrayList
 * class. It also implements the java.util.Queue interface so that it can be
 * used with the framework problem solver.
 * 
 * @author tcolburn
 * @param <E> the type of element stored on this binary heap
 */
public class BinaryHeap<E> extends ArrayList<E> implements Queue<E> {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an empty binary heap with a given capacity and comparator.
     * 
     * @param capacity The initial size of the underlying ArrayList object.
     * @param comp     A comparator object for comparing keys of binary heap
     *                 elements.
     */
    public BinaryHeap(int capacity, Comparator<E> comp) {
        super(capacity + 1);
        init();
        this.comp = comp;
    }

    /**
     * Getter for the comparator for this binary heap.
     * 
     * @return the comparator for this binary heap
     */
    public Comparator<E> getComp() {
        return comp;
    }

    /**
     * Initializes the underlying ArrayList object for use as a binary heap. A null
     * object is added to location 0, which is not used by the heap.
     */
    private void init() {
        add(0, null);
    }

    /**
     * Clears this binary heap by clearing and initializing the underlying ArrayList
     * object.
     */
    @Override
    public void clear() {
        super.clear();
        init();
    }

    /**
     * Returns the current size of this binary heap. Since the first location (index
     * 0) of the underlying ArrayList object is not used, the size of the binary
     * heap is one less than the size of the ArrayList object.
     * 
     * @return The binary heap's current size.
     */
    @Override
    public int size() {
        return super.size() - 1;
    }

    /**
     * Returns true if this binary heap is empty, that is, its size is zero.
     * 
     * @return Whether this binary heap is empty.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds a new element to this binary heap. At the end of the add, the heap has
     * one more element and the heap property is maintained.
     * 
     * @param element The element to add
     * @return true. In accordance with the Collection interface, returns true since
     *         duplicate elements are allowed.
     */
    @Override
    public boolean add(E element) {
        super.add(element);
        int child = super.size() - 1;
        int parent = child / 2;

        while (parent > 0 && comp.compare(super.get(parent), super.get(child)) > 0) {
            swap(parent, child);
            child = parent;
            parent = child / 2;
        }
        return true;
    }

    /**
     * Removes an element from the root of this binary heap. After removal, the heap
     * has one less element and the heap property is restored. This method does not
     * override any method in the ArrayList class (note absence of an index
     * parameter). However, it implements a method in the Queue interface.
     * 
     * @return The removed element
     */
    @Override
    public E remove() {
        if (isEmpty())
            throw new IllegalStateException();

        E temp = super.get(1);

        super.set(1, super.get(size()));

        int parent = 1;
        int left = 2 * parent;
        int right = (2 * parent) + 1;

        while (right <= super.size()) {
            int minChild = left;
            if (right != super.size() && comp.compare(super.get(left), super.get(right)) > 0)
                minChild = right;

            if (comp.compare(super.get(parent), super.get(minChild)) < 0)
                break;

            swap(parent, minChild);
            parent = minChild;
            left = 2 * parent;
            right = (2 * parent) + 1;
        }
        super.remove(size());
        return temp;
    }

    /**
     * Swaps two elements at given indices
     * 
     * @param i index 1
     * @param j index 2
     */
    private void swap(int i, int j) {
        E temp = super.get(i);
        super.set(i, super.get(j));
        super.set(j, temp);
    }

    /**
     * A Comparator object used to compare binary heap elements during its add and
     * remove operations.
     */
    private final Comparator<E> comp;

    /*
     * The following are required to complete the implementation of the Queue<E>
     * interface. They are not used in the test.
     */
    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E poll() {
        return !isEmpty() ? remove() : null;
    }

    @Override
    public E element() {
        if (isEmpty())
            throw new IllegalStateException();
        return super.get(super.size() - 1);
    }

    @Override
    public E peek() {
        return super.get(super.size() - 1);
    }
}