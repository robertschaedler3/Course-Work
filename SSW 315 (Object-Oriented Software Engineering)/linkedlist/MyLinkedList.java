package lab.linkedlist;

public class MyLinkedList { // Start of class MyLinkedList
    private Node head;
    private int size;

    // LinkList constructor
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    // Returns true if the linked list is empty
    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    // Inserts a new node at the first of the linked list
    public void addFirst(int d1, double d2) {
        Node newHead = new Node(d1, d2);
        newHead.next = head;
        head = newHead;
        size++;
    }

    // Deletes the node at the first of the linked list
    public Node deleteFirst() {
        Node deletedNode = head;
        head = head.next;
        size--;
        return deletedNode;
    }

    // Inserts a new node at the last position in the linked list
    public void addLast(int d1, double d2) {
        if (head == null) {
            this.addFirst(d1, d2);
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = new Node(d1, d2);
            size++;
        }
    }

    // Deletes the node at the last position of the linked list
    public Node deleteLast() {
        if (head == null) {
            throw new IllegalStateException();
        }

        if (head.next == null) {
            Node temp = head;
            head = null;
            size--;
            return temp;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        Node temp = current.next;
        current.next = null;
        size--;
        return temp;
    }

    // Searches for node in list
    public boolean search(Node n) {
        Node current = head;

        // TODO: deal with empty list
        // if (current == null) {
        // throw new IllegalStateException();
        // }

        while (current != null) {
            if (current.equals(n)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Add new node after node n
    public void addAfter(int d1, double d2, Node n) {
        // TODO: deal with empty list

        Node current = head;
        while (current != null) {
            if (current.data1 == n.data1 && current.data2 == n.data2) {
                Node newNode = new Node(d1, d2);
                Node next = current.next;
                current.next = newNode;
                newNode.next = next;
                size++;
                break;
            }
            current = current.next;
        }

        // TODO: how to handle Node n does not exist
    }

    // Prints the linked list data
    public void printList() {
        Node currentNode = head;
        System.out.print("List: ");
        while (currentNode != null) {
            currentNode.printNode();
            currentNode = currentNode.next;
        }
        System.out.println("");

    }
}