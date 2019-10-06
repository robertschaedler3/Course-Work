package lab.doublylinkedlist;

public class DLL implements DLLInterface {

    private DoubleNode head;
    private DoubleNode tail;
    private int size;

    public DLL() {
        head = new DoubleNode(null, tail, null);
        tail = new DoubleNode(null, null, head);
        size = 0;
    }

    /**
     * Gets the size of the list.
     * 
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds a new node to the end of the list.
     * 
     * @param data for new node
     */
    public void insert(Object data) {
        if (lookup(data)) {
            throw new IllegalStateException();
        }

        if (size == 0) {
            head = new DoubleNode(data, tail, null);
        } else if (size == 1) {
            tail = new DoubleNode(data, null, head);
            head.next = tail;
        } else {
            tail.next = new DoubleNode(data, null, tail);
            tail = tail.next;
        }
        size++;
    }

    /**
     * Adds a new node after the given node (assumong you have a pointer to a node
     * in the list).
     * 
     * @param data
     * @param node
     */
    public void insert(Object data, DoubleNode node) {
        if (lookup(data)) {
            throw new IllegalStateException();
        }

        node.next = new DoubleNode(data, node.next, node.prev);

    }

    /**
     * Removes a given element from the list.
     * 
     * @param data the object to delete from the list
     */
    public void delete(Object data) {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        if (data == null) {
            throw new IllegalArgumentException();
        }

        DoubleNode current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                if (current.next == null) { // remove last element
                    current.prev.next = null;
                    tail = current.prev;
                } else if (current.prev == null) { // remove first element
                    current.next.prev = null;
                    head = current.next;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }

        // Data object was not found in the list
        System.out.println("not found");
        throw new IllegalStateException();
    }

    /**
     * Returns true if an object is in the list.
     * 
     * @param data object to look up
     */
    public boolean lookup(Object data) {
        if (isEmpty()) {
            return false;
        }
        DoubleNode current = head;
        while (current.next != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printList() {
        DoubleNode current = head;
        StringBuilder s = new StringBuilder("[ ");
        while (current != null) {
            s.append(current.data + ", ");
            current = current.next;
        }
        s.append("]");
        System.out.println(s);
    }

    public void printListRev() {
        DoubleNode current = tail;
        StringBuilder s = new StringBuilder("[ ");
        while (current != null) {
            s.append(current.data + ", ");
            current = current.prev;
        }
        s.append("]");
        System.out.println(s);
    }

}