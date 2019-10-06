package lab.doublylinkedlist;

public class DoubleNode {

    public Object data;
    public DoubleNode next;
    public DoubleNode prev;

    public DoubleNode(Object data, DoubleNode next, DoubleNode prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

}