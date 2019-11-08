package lab.bst;

public class BinaryNode<E> {

    E data;
    BinaryNode<E> left;
    BinaryNode<E> right;

    BinaryNode(E data) {
        this.data = data;
        left = right = null;
    }
}