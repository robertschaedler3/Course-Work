package lab.doublylinkedlist;

public interface DLLInterface {
    public void insert(Object x);

    public void delete(Object x);

    public boolean lookup(Object x);

    public boolean isEmpty();

    public void printList();

    public void printListRev();
}