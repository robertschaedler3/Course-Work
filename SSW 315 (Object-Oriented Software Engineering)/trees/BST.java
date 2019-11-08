package homework.trees;

import java.util.LinkedList;

public class BST<E extends Comparable<E>> {

    private static class Node<F> {

        private LinkedList<F> data;
        private Node<F> left, right;

        public Node(F data, Node<F> left, Node<F> right) {
            this.data = new LinkedList<F>();
            this.data.add(data);
            this.left = left;
            this.right = right;
        }

        public Node(F data) {
            this.data = new LinkedList<F>();
            this.data.add(data);
            this.left = null;
            this.right = null;
        }

    }

    private Node<E> root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public BST(E item) {
        root = new Node<E>(item);
        size = 1;
    }

    public BST(E item, BST<E> left, BST<E> right) {
        root = new Node<E>(item, left.root, right.root);
        size = 1 + left.size + right.size;
    }

    private Node<E> add(E key, Node<E> current) {
        if (current == null) {
            size++;
            return new Node<E>(key);
        }

        int i = current.data.getFirst().compareTo(key);
        if (i == 0) { // duplicate case
            current.data.addFirst(key);
            size++;
        } else if (i < 0) {
            current.right = add(key, current.right);
        } else {
            current.left = add(key, current.left);
        }
        return current;
    }

    public void add(E item) {
        root = add(item, root);
    }

    /**
     * Recursively removes and returns the largest element of a given sub-tree
     * assuming that there is a right child of the initial node;
     * 
     * @param current
     * @return the LinkedList of the largest element in the sub-tree
     */
    private LinkedList<E> join(Node<E> current) {
        if (current.right.right == null) {
            LinkedList<E> max = current.right.data;
            current.right = current.right.left;
            return max;
        } else {
            return join(current.right);
        }
    }

    /**
     * Recursively removes a value from a given subtree
     * 
     * @param current the root of the current sub-tree
     * @param key     the value to be deleted
     * @return
     */
    private Node<E> remove(Node<E> current, E key) {
        if (current == null) {
            throw new IllegalArgumentException();
        }

        int i = current.data.getFirst().compareTo(key);
        if (i == 0 && current.data.size() == 1) { // remove entire node
            if (current.left == null) { // no left child
                current = current.right;
            } else if (current.right == null) { // no right child
                current = current.left;
            } else { // node has both children
                if (current.left.right == null) {
                    current.data = current.left.data;
                    current.left = current.left.left;
                } else {
                    current.data = join(current.left);
                }
            }
            size--;
        } else if (i == 0 && current.data.size() > 1) { // node contains duplicates
            current.data.removeFirst();
            size--;
        } else {
            if (i < 0) {
                current.right = remove(current.right, key);
            } else {
                current.left = remove(current.left, key);
            }
        }
        return current;
    }

    /**
     * Removes a key from a BST
     * 
     * @param key the key to search for
     */
    public void remove(E key) {
        root = remove(root, key);
    }

    private StringBuilder toString(Node<E> current, int n) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < n; i++) {
            b.append("-");
        }
        if (current == null) {
            return b.append("null\n");
        } else {
            b.append(current.data + "\n");
            b.append(toString(current.left, n + 1));
            b.append(toString(current.right, n + 1));
        }
        return b;
    }

    public String toString() {
        return toString(root, 0).toString();
    }

    public static void main(String[] args) {
        BST<Integer> t0 = new BST<>(15);
        BST<Integer> t10 = new BST<>(74, new BST<Integer>(), new BST<>(80));
        BST<Integer> t1 = new BST<>(89, t10, new BST<>(102));
        BST<Integer> t = new BST<>(27, t0, t1);

        t.add(76);
        System.out.println(t);
        t.remove(89);
        System.out.println(t);

    }

}