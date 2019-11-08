package lab.bst;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// void removeMin( )      --> Remove minimum item
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Exceptions are thrown by insert, remove, and removeMin if warranted

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 *
 * @author Mark Allen Weiss
 * @author Robert Schaedler III
 * @author
 */
public class BinarySearchTree<E extends Comparable<E>> {

    /** The tree root. */
    protected BinaryNode<E> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree.
     *
     * @param x the item to insert.
     * @throws DuplicateItemException if x is already present.
     */
    public void insert(E x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree..
     *
     * @param x the item to remove.
     * @throws ItemNotFoundException if x is not found.
     */
    public void remove(E x) {
        root = remove(x, root);
    }

    /**
     * Remove minimum item from the tree.
     *
     * @throws ItemNotFoundException if tree is empty.
     */
    public void removeMin() {
        root = removeMin(root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public E findMin() {
        return elementAt(findMin(root));
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item or null if empty.
     */
    public E findMax() {
        return elementAt(findMax(root));
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public E find(E x) {
        return elementAt(find(x, root));
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Internal method to get element field.
     *
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private E elementAt(BinaryNode<E> t) {
        return t == null ? null : t.data;
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws DuplicateItemException if x is already present.
     */
    protected BinaryNode<E> insert(E x, BinaryNode<E> t) {
        if (t == null)
            t = new BinaryNode<E>(x);
        else if (x.compareTo(t.data) < 0)
            t.left = insert(x, t.left);
        else if (x.compareTo(t.data) > 0)
            t.right = insert(x, t.right);
        else
            throw new DuplicateItemException(x.toString()); // Duplicate
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode<E> remove(E x, BinaryNode<E> t) {
        if (t == null)
            throw new ItemNotFoundException(x.toString());
        if (x.compareTo(t.data) < 0)
            t.left = remove(x, t.left);
        else if (x.compareTo(t.data) > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.data = findMin(t.right).data;
            t.right = removeMin(t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to remove minimum item from a subtree.
     *
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if t is empty.
     */
    protected BinaryNode<E> removeMin(BinaryNode<E> t) {
        if (t == null)
            throw new ItemNotFoundException();
        else if (t.left != null) {
            t.left = removeMin(t.left);
            return t;
        } else
            return t.right;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    protected BinaryNode<E> findMin(BinaryNode<E> t) {
        if (t != null)
            while (t.left != null)
                t = t.left;

        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode<E> findMax(BinaryNode<E> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BinaryNode<E> find(E x, BinaryNode<E> t) {
        while (t != null) {
            if (x.compareTo(t.data) < 0)
                t = t.left;
            else if (x.compareTo(t.data) > 0)
                t = t.right;
            else
                return t; // Match
        }

        return null; // Not found
    }

    /**
     * See if the tree contains a given item.
     *
     * @param target the item to search for
     * @return true if the item is in the tree
     */
    public boolean contains(E target) {
        return search(root, target);
    }

    /** Recursively search the tree for the target */
    private boolean search(BinaryNode<E> current, E target) {
        if (current == null) {
            return false;
        }
        int i = current.data.compareTo(target);
        if (i == 0) {
            return true;
        } else if (i < 0) {
            return search(current.left, target);
        } else {
            return search(current.right, target);
        }
    }

    /**
     * Show an ordered list of the items in the tree, one item per line.
     */
    public void list() {
        traverseInOrder(root);
    }

    /** Recursive in-order traversal of the tree, printing each node */
    private void traverseInOrder(BinaryNode<E> current) {
        if (current != null) {
            traverseInOrder(current.left);
            System.out.print(current.data);
            traverseInOrder(current.right);
        }
    }

    /**
     * Show an ordered list of the items in the tree, one item per line.
     */
    public void print() {
        traversePreOrder(root, 0);
    }

    /** Recursive in-order traversal of the tree, printing each node */
    private void traversePreOrder(BinaryNode<E> current, int indent) {
        StringBuilder indentString = new StringBuilder();
        for (int i = 0; i < indent; i++)
            indentString.append("  ");

        if (current == null) {
            System.out.println(indentString.toString() + "-");
        } else {
            System.out.println(indentString.toString() + current.data);
            traversePreOrder(current.left, indent + 1);
            traversePreOrder(current.right, indent + 1);
        }
    }

    // Test program
    public static void main(String[] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
        final int NUMS = 10;
        final int GAP = 37;

        // System.out.println("Checking... (no more output means success)");

        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
            t.insert(i);

        // t.print();
        t.list();
        for (int i = 1; i < NUMS; i += 2)
            t.remove(i);

        if (t.findMin() != 2 || t.findMax() != NUMS - 2)
            System.out.println("FindMin or FindMax error!");

        for (int i = 2; i < NUMS; i += 2)
            if (t.find(i) != i)
                System.out.println("Find error1!");

        for (int i = 1; i < NUMS; i += 2)
            if (t.find(i) != null)
                System.out.println("Find error2!");
    }
}