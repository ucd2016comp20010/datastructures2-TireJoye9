package project20280.tree;

import project20280.interfaces.Position;
import project20280.stacksqueues.LinkedQueue;

import java.util.ArrayList;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */


public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    //creates object called rnd from random class.
    static java.util.Random rnd = new java.util.Random();
    private boolean goLeftNext = true;  // class-level variable
    /**
     * The root of the binary tree
     */
    //Root is a pointer
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            int treeSize = last - first + 1;
            int leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());
        System.out.println(bt.height());

        // Question (g)
        LinkedBinaryTree<Integer> bt1 = new LinkedBinaryTree<>();
        Integer[] arr1 = {1,2,3, 4,5,6,7, 8,9,10,11,12,13,14,15, 16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,null,null,null,35};
        bt1.createLevelOrder(arr1);
        System.out.println(bt1.toBinaryTreeString());
        //Question (h)
        System.out.println(bt1.height());

        //Question i
        Integer [] arr3 = new Integer[] {1,
                2,3, 4,5,6,7,8,9,10,11,12, 13, 14, 15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,null,null,null,35};
        LinkedBinaryTree<Integer> bt2 = new LinkedBinaryTree<>();
        bt2.createLevelOrder(arr3);

        //Question 2
        /*
        function countExternalNodes(node):
            if node has no children:
                return 1
            else:
                total = 0
                for each child in children(node):
            total += countExternalNodes(child)
        return total
         */

        //Question 3
        /*
        function countLeftExternalNodes(node, isLeftChild):
        if node has no children:                     // leaf
            if isLeftChild is true:
                return 1
            else:
                return 0

        total = 0
        if node has left child:
            total += countLeftExternalNodes(left child, true)
        if node has right child:
            total += countLeftExternalNodes(right child, false)

            return total
         */

        //Question 4 on ipad

        //Question 5
        /*
        function countDescendants(node):
        total = 0
        for each child in children(node):
            total = total + 1                       // count this child
            total = total + countDescendants(child) // add its descendants
        return total
         */



    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    //Diameter function
    //Dont understand how to implement that
    //My idea was get height of tree, calculate how many nodes that level would have and - 2 for outer node and innter node
    //Only counting nodes inbetween
    //However this only works for completely full trees with all internal nodes having 2 children
    protected int diameter(Position<E> p)
    {
        return 0;
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        //Acc means create new node that is going to be root lol
        //so the very first node.
        if (!isEmpty())throw new IllegalStateException("This tree is not empty");
        Node<E> newNode = new Node<>(e,null,null,null);
        root = newNode;
        size =1;
        return root;
    }

    public void insert(E e) {
        //if root is empty add root and finish
        if (root() == null)
        {
            addRoot(e);
            return;
        }
        //node we want to add
        Node<E> newNode = createNode(e,null,null,null);
        //create queue FIFO
        LinkedQueue<Node<E>> queue = new LinkedQueue<>();
        //add root to queue
        queue.enqueue(root);
        //while queue is not empty
        while(!queue.isEmpty())
        {
            //current gets value of head element in queue and that element is removed from the queue
            Node<E> current = queue.dequeue();
            //if current node left child is empty we set left to newnode, and set that nodes parents to current
            if(current.getLeft() == null)
            {
                current.setLeft(newNode);
                newNode.setParent(current);
                size++;
                return;
            }
            //else we add the left child to the queue
            else
            {
                queue.enqueue(current.getLeft());
            }
            //same as left
            if(current.getRight() == null)
            {
                current.setRight(newNode);
                newNode.setParent(current);
                size++;
                return;
            }
            //else we add right child to queue
            else
            {
                queue.enqueue(current.getRight());
            }
        }
    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // Try left first
        if (p.getLeft() == null) {
            Node<E> newNode = createNode(e, p, null, null);
            size++;
            return newNode;
        }

        // Then try right
        if (p.getRight() == null) {
            Node<E> newNode = createNode(e, p, null, null);
            size++;
            return newNode;
        }

        // Both children exist — use toggle to decide
        if (goLeftNext) {
            goLeftNext = false;
            return addRecursive(p.getLeft(), e);
        } else {
            goLeftNext = true;
            return addRecursive(p.getRight(), e);
        }
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (node.getLeft() != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        Node<E> newNode = createNode(e, node, null, null);

        node.setLeft(newNode);
        size++;
        return newNode;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (node.getRight() != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        Node<E> newNode = createNode(e, node, null, null);

        node.setRight(newNode);
        size++;
        return newNode;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        E replaceEleme = p.getElement();
        Node<E> node = validate(p);
        node.setElement(e);
        return replaceEleme;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
         if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
         size += t1.size() + t2.size();
         if (!t1.isEmpty()) {
             t1.root.setParent(node);
             node.setLeft(t1.root);
             t1.root = null;
             t1.size = 0;

        }
         if (!t2.isEmpty()) {
             t2.root.setParent(node);
             node.setRight(t2.root);
             t2.root = null;
             t2.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
            Node<E> node = validate(p);
            if (numChildren(p) == 2)
                throw new IllegalArgumentException("p has two children");
            //has option to either be lft node or rgt
            Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
            if (child != null)
                child.setParent(node.getParent()); // child's grandparent becomes its parent
            if (node == root)
                root = child; // child becomes root
            else {
                Node<E> parent = node.getParent();
                if (node == parent.getLeft())
                    parent.setLeft(child);
                else
                    parent.setRight(child);
            }
            size--;
            E temp = node.getElement();
            node.setElement(null); // help garbage collection
            node.setLeft(null);
            node.setRight(null);
            node.setParent(node); // our convention for defunct node
            return temp;
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        root = createLevelOrderHelper(l, root, 0);
    }

    //overloaded if user uses array list or an array
    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        //if index is greater than the length of the array
        if (i >= l.size()) return null;
        //create node
        Node<E> node = createNode(l.get(i), null, null,null);

        node.setLeft(createLevelOrderHelper(l,p ,2*i + 1));
        node.setRight(createLevelOrderHelper(l,p, 2*i + 2));

        if (node.left != null) node.getLeft().setParent(node);
        if (node.right != null) node.getRight().setParent(node);

        size++;
        return node;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, int i) {
        //if index is greater than the length of the array
        if (i >= arr.length) return null;
        //create node
        Node<E> node = createNode(arr[i], null, null,null);

        node.setLeft(createLevelOrderHelper(arr, 2*i + 1));
        node.setRight(createLevelOrderHelper(arr, 2*i + 2));

        if (node.left != null) node.getLeft().setParent(node);
        if (node.right != null) node.getRight().setParent(node);

        size++;
        return node;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
}
